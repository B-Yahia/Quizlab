package Backend.QuizLab.services.quiz;

import Backend.QuizLab.models.commun.QuestionType;
import Backend.QuizLab.models.quiz.QuizAnswerRecord;
import Backend.QuizLab.models.quiz.QuizAttempt;
import Backend.QuizLab.models.quiz.QuizOption;
import Backend.QuizLab.models.quiz.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizGradingService {

    private QuizAnswerRecord gradeAnswerRecord(QuizAnswerRecord record) {
        //this line to retrieve the question if only the id was provided
        QuestionType type = record.getQuestion().getQuestionType();
        if (type == QuestionType.SINGLE_CHOICE) {
            gradeSingleChoiceQuestion(record);
            record.setGraded(true);
        } else if (type == QuestionType.MULTIPLE_CHOICE) {
            gradeMultipleChoiceQuestion(record);
            record.setGraded(true);
        }  else if (type == QuestionType.TEXT){
            //we set the graded property false so that the frontend will pop this to the creator so give the grade
            record.setGraded(false);
        }

        return record;
    }

    private void gradeSingleChoiceQuestion(QuizAnswerRecord record) {
        boolean correct = record.getSelectedOptions().get(0).isCorrect();
        record.setCorrect(correct);
        record.setPointsEarned(correct ? record.getQuestion().getBasePoints() : 0);
    }

    private void gradeMultipleChoiceQuestion(QuizAnswerRecord record) {
        long correctAnswersCount = record.getQuestion().getOptions()
                .stream()
                .filter(QuizOption::isCorrect)
                .count();
        boolean allCorrect = record.getSelectedOptions().size() == correctAnswersCount &&
                record.getSelectedOptions().stream().allMatch(QuizOption::isCorrect);

        record.setCorrect(allCorrect);
        record.setPointsEarned(allCorrect ? record.getQuestion().getBasePoints() : 0);
    }

    public QuizAttempt gradeQuizAttempt (QuizAttempt quizAttempt){
        List<QuizAnswerRecord> records = new ArrayList<>();
        for (QuizAnswerRecord record : quizAttempt.getAnswerRecords()){
            records.add(gradeAnswerRecord(record));
        }

        // Make sure that all the questions are graded
        quizAttempt.setGraded(quizAttempt.getAnswerRecords().stream().allMatch(QuizAnswerRecord::isGraded));
        double totalgrade = 0;
        double total = 0;
        //sum all the answers points and assign it to the attempt
        for (QuizAnswerRecord record : quizAttempt.getAnswerRecords()){
            if (record.isGraded()) totalgrade += record.getPointsEarned();
        }
        for (QuizQuestion q : quizAttempt.getQuiz().getQuestions()){
            total += q.getBasePoints();
        }
        quizAttempt.setPercentageScore(totalgrade/total);
        quizAttempt.setTotalScore(totalgrade);
        return quizAttempt;
    }
}
