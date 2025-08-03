package Backend.QuizLab.services.quiz;

import Backend.QuizLab.models.commun.QuestionType;
import Backend.QuizLab.models.quiz.QuizAnswerRecord;
import Backend.QuizLab.models.quiz.QuizAttempt;
import Backend.QuizLab.models.quiz.QuizOption;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizGradingService {
    private QuizAnswerRecord gradeAnswerRecord(QuizAnswerRecord record) {
        QuestionType type = record.getQuestion().getQuestionType();
        if (type == QuestionType.CHECKBOX) {
            gradeCheckboxQuestion(record);
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

    private void gradeCheckboxQuestion(QuizAnswerRecord record) {
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
        var totalgrade = 0;
        //sum all the answers points and assign it to the attempt
        for (QuizAnswerRecord record : quizAttempt.getAnswerRecords()){
            if (record.isGraded()) totalgrade += record.getPointsEarned();
        }
        quizAttempt.setTotalScore(totalgrade);
        return quizAttempt;
    }
}
