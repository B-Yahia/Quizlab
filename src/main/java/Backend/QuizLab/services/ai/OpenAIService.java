package Backend.QuizLab.services.ai;


import Backend.QuizLab.dtos.llm.quiz.Quiz;
import Backend.QuizLab.dtos.llm.survey.Survey;
import com.openai.client.OpenAIClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.chat.completions.StructuredChatCompletion;
import com.openai.models.chat.completions.StructuredChatCompletionCreateParams;
import com.openai.models.responses.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OpenAIService {

    private final OpenAIClient openAIClient;

    public OpenAIService(OpenAIClient openAIClient) {
        this.openAIClient = openAIClient;
    }

    public Quiz createQuiz (String prompt, int numberOfQuestions){
        StructuredChatCompletionCreateParams<Quiz> params = ChatCompletionCreateParams.builder()
                .model(ChatModel.GPT_5_NANO_2025_08_07)
                .addUserMessage(prompt)
                .addDeveloperMessage(devInstructions(numberOfQuestions))
                .responseFormat(Quiz.class)
                .build();

        return toSingleQuiz(openAIClient.chat().completions().create(params));
    }

    public Quiz toSingleQuiz(StructuredChatCompletion<Quiz> response) {
        return response.choices().stream()
                .map(choice -> choice.message().content())
                .flatMap(Optional::stream)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No quiz content returned by the model"));
    }

    private String devInstructions(int numberOfQuestions) {
        return """
            - You are knowledgeable quiz generator, Produce a quiz that has as metadata title and description. And that contains list of questions.
            - Number of questions in the list need to equal %d.
            - The property "correct_options" in the question class can't have less than one option. and the list can contain two or more options.
            - The property "other_options" in the question class should have other options that contains wrong answers to the question, list of the wrong answers can have zero option or more.
            - Keep explanations brief and factual, and try to just give a hint not to answer the question directly.
            """.formatted(numberOfQuestions);
    }

    public Survey createSurvey (String prompt, int numberOfQuestions){
        StructuredResponseCreateParams<Survey> params = ResponseCreateParams.builder()
                .model(ChatModel.GPT_5_NANO_2025_08_07)
                .input(buildSurveyInstruction(prompt,numberOfQuestions))
                .text(Survey.class)
                .build();
        StructuredResponse<Survey> response = openAIClient.responses().create(params);
        return toSingleSurvey(response);
    }

    public Survey toSingleSurvey(StructuredResponse<Survey> response) {
        return response.output().stream()
                .map(StructuredResponseOutputItem::message)
                .flatMap(Optional::stream)
                .flatMap((StructuredResponseOutputMessage<Survey> msg) -> msg.content().stream())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No survey content returned by the model")).asOutputText();
    }

    private String buildSurveyInstruction(String userPrompt , Integer numberOfQuestions){
        String instructions;
        if (numberOfQuestions== null){
            instructions = """
            You are a careful survey generator. Produce a survey that exactly matches the user's request.
            - Make sure the survey has enough question to capture the person answering the survey opinion or feeling.
            - Each Question of the survey need to have more than one option to allow person taking the survey to choose.
            - You can specify if the question type if SINGLE_CHOICE or MULTIPLE_CHOICE, user will later be able to change if needed.
            - Field explanation is used to help user understand the question
            """;
        }else {
            instructions = """
            You are a careful survey generator. Produce a survey that exactly matches the user's request.
            - Number of questions: %d
            - Make sure the survey the survey questions capture the person answering the survey opinion or feeling
            - Each Question of the survey need to have more than one option to allow person taking the survey to choose.
            - You can specify if the question type if SINGLE_CHOICE or MULTIPLE_CHOICE, user will later be able to change if needed.
            - Field explanation is used to help user understand the question
            """.formatted(numberOfQuestions);
        }
        String userInstruction = """
            Create a the survey. Requirements:
            %s
            """.formatted(userPrompt);

        return instructions+"\n\n"+userInstruction;
    }
}
