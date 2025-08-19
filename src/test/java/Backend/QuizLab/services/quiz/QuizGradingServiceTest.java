//package Backend.QuizLab.services.quiz;
//
//
//import Backend.QuizLab.models.commun.ProgressionStatus;
//import Backend.QuizLab.models.commun.QuestionType;
//import Backend.QuizLab.models.quiz.*;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class QuizGradingServiceTest {
//
//    private final QuizGradingService quizGradingService = new QuizGradingService();
//
//    @Test
//    public void QuizGradingService_GradeMultipleChoice_RightChoicesSelected(){
//        // Arrange
//        var option1 = new QuizOption("Parnu",true,null);
//        var option2 = new QuizOption("Tartu",true,null);
//        var option3 = new QuizOption("Tallinn",true,null);
//        var option4 = new QuizOption("Paris",false,null);
//        var options1 = List.of(option4,option3,option1,option2);
//        var question1 = new QuizQuestion("Which of the following are Estonian cities :",true,null,options1);
//        var option5 = new QuizOption("2+2",true,null);
//        var option6 = new QuizOption("2-2",false,null);
//        var option7 = new QuizOption("2*2",true,null);
//        var options2 = List.of(option7,option5,option6);
//        var question2 = new QuizQuestion("Which of the following math operations equal to 4 :",true,null,options2);
//        var quiz = new Quiz("General knowledge","General knowledge",0,null,null,null,true);
//        quiz.setQuestions(List.of(question2,question1));
//        var record1 = new QuizAnswerRecord(question1,List.of(option1,option2,option3),null);
//        var record2 = new QuizAnswerRecord(question2,List.of(option5,option7),null);
//        var attempt = new QuizAttempt(null,quiz, ProgressionStatus.COMPLETED);
//        attempt.setAnswerRecords(List.of(record1,record2));
//
//        // Act
//        var returned = this.quizGradingService.gradeQuizAttempt(attempt);
//
//        // Assert
//        assert returned.isGraded();
//        assert returned.getTotalScore() == 2d;
//        assert returned.getAnswerRecords().get(0).isGraded();
//        assert returned.getAnswerRecords().get(1).isGraded();
//        assert returned.getAnswerRecords().get(0).getPointsEarned() == 1;
//        assert returned.getAnswerRecords().get(1).getPointsEarned() == 1;
//        assert returned.getAnswerRecords().get(0).isCorrect();
//        assert returned.getAnswerRecords().get(1).isCorrect();
//        assert returned.getAnswerRecords().get(0).getQuestion().getQuestionType() == QuestionType.MULTIPLE_CHOICE;
//        assert returned.getAnswerRecords().get(1).getQuestion().getQuestionType() == QuestionType.MULTIPLE_CHOICE;
//    }
//
//    @Test
//    public void QuizGradingService_GradeMultipleChoice_WrongChoicesSelected(){
//        // Arrange
//        var option1 = new QuizOption("Parnu",true,null);
//        var option2 = new QuizOption("Tartu",true,null);
//        var option3 = new QuizOption("Tallinn",true,null);
//        var option4 = new QuizOption("Paris",false,null);
//        var options1 = List.of(option4,option3,option1,option2);
//        var question1 = new QuizQuestion("Which of the following are Estonian cities :",true,null,options1);
//        var option5 = new QuizOption("2+2",true,null);
//        var option6 = new QuizOption("2-2",false,null);
//        var option7 = new QuizOption("2*2",true,null);
//        var options2 = List.of(option7,option5,option6);
//        var question2 = new QuizQuestion("Which of the following math operations equal to 4 :",true,null,options2);
//        var quiz = new Quiz("General knowledge","General knowledge",0,null,null,null,true);
//        quiz.setQuestions(List.of(question2,question1));
//        var record1 = new QuizAnswerRecord(question1,List.of(option1,option4,option3),null);
//        var record2 = new QuizAnswerRecord(question2,List.of(option6,option7),null);
//        var attempt = new QuizAttempt(null,quiz, ProgressionStatus.COMPLETED);
//        attempt.setAnswerRecords(List.of(record1,record2));
//
//        // Act
//        var returned = this.quizGradingService.gradeQuizAttempt(attempt);
//
//        // Assert
//        assert returned.isGraded();
//        assert returned.getTotalScore() == 0d;
//        assert returned.getAnswerRecords().get(0).isGraded();
//        assert returned.getAnswerRecords().get(1).isGraded();
//        assert returned.getAnswerRecords().get(0).getPointsEarned() == 0;
//        assert returned.getAnswerRecords().get(1).getPointsEarned() == 0;
//        assert !returned.getAnswerRecords().get(0).isCorrect();
//        assert !returned.getAnswerRecords().get(1).isCorrect();
//        assert returned.getAnswerRecords().get(0).getQuestion().getQuestionType() == QuestionType.MULTIPLE_CHOICE;
//        assert returned.getAnswerRecords().get(1).getQuestion().getQuestionType() == QuestionType.MULTIPLE_CHOICE;
//    }
//
//    @Test
//    public void QuizGradingService_GradeSingleChoice_RightChoicesSelected(){
//        // Arrange
//        var option1 = new QuizOption("Oslo",false,null);
//        var option2 = new QuizOption("Tartu",false,null);
//        var option3 = new QuizOption("Tallinn",true,null);
//        var option4 = new QuizOption("Paris",false,null);
//        var options1 = List.of(option4,option3,option1,option2);
//        var question1 = new QuizQuestion("Which of the following cities is the capital of Estonian:",true,null,options1);
//        var option5 = new QuizOption("2+2",true,null);
//        var option6 = new QuizOption("2-2",false,null);
//        var option7 = new QuizOption("2/2",false,null);
//        var options2 = List.of(option7,option5,option6);
//        var question2 = new QuizQuestion("Which of the following math operations equal to 4 :",true,null,options2);
//        var quiz = new Quiz("General knowledge","General knowledge",0,null,null,null,true);
//        quiz.setQuestions(List.of(question2,question1));
//        var record1 = new QuizAnswerRecord(question1,List.of(option3),null);
//        var record2 = new QuizAnswerRecord(question2,List.of(option5),null);
//        var attempt = new QuizAttempt(null,quiz, ProgressionStatus.COMPLETED);
//        attempt.setAnswerRecords(List.of(record1,record2));
//
//        // Act
//        var returned = this.quizGradingService.gradeQuizAttempt(attempt);
//
//        // Assert
//        assert returned.isGraded();
//        assert returned.getTotalScore() == 2d;
//        assert returned.getAnswerRecords().get(0).isGraded();
//        assert returned.getAnswerRecords().get(1).isGraded();
//        assert returned.getAnswerRecords().get(0).getPointsEarned() == 1;
//        assert returned.getAnswerRecords().get(1).getPointsEarned() == 1;
//        assert returned.getAnswerRecords().get(0).isCorrect();
//        assert returned.getAnswerRecords().get(1).isCorrect();
//        assert returned.getAnswerRecords().get(0).getQuestion().getQuestionType() == QuestionType.CHECKBOX;
//        assert returned.getAnswerRecords().get(1).getQuestion().getQuestionType() == QuestionType.CHECKBOX;
//    }
//
//    @Test
//    public void QuizGradingService_GradeSingleChoice_WrongChoicesSelected(){
//        // Arrange
//        var option1 = new QuizOption("Oslo",false,null);
//        var option2 = new QuizOption("Tartu",false,null);
//        var option3 = new QuizOption("Tallinn",true,null);
//        var option4 = new QuizOption("Paris",false,null);
//        var options1 = List.of(option4,option3,option1,option2);
//        var question1 = new QuizQuestion("Which of the following cities is the capital of Estonian:",true,null,options1);
//        var option5 = new QuizOption("2+2",true,null);
//        var option6 = new QuizOption("2-2",false,null);
//        var option7 = new QuizOption("2/2",false,null);
//        var options2 = List.of(option7,option5,option6);
//        var question2 = new QuizQuestion("Which of the following math operations equal to 4 :",true,null,options2);
//        var quiz = new Quiz("General knowledge","General knowledge",0,null,null,null,true);
//        quiz.setQuestions(List.of(question2,question1));
//        var record1 = new QuizAnswerRecord(question1,List.of(option4),null);
//        var record2 = new QuizAnswerRecord(question2,List.of(option6),null);
//        var attempt = new QuizAttempt(null,quiz, ProgressionStatus.COMPLETED);
//        attempt.setAnswerRecords(List.of(record1,record2));
//
//        // Act
//        var returned = this.quizGradingService.gradeQuizAttempt(attempt);
//
//        // Assert
//        assert returned.isGraded();
//        assert returned.getTotalScore() == 0d;
//        assert returned.getAnswerRecords().get(0).isGraded();
//        assert returned.getAnswerRecords().get(1).isGraded();
//        assert returned.getAnswerRecords().get(0).getPointsEarned() == 0;
//        assert returned.getAnswerRecords().get(1).getPointsEarned() == 0;
//        assert !returned.getAnswerRecords().get(0).isCorrect();
//        assert !returned.getAnswerRecords().get(1).isCorrect();
//        assert returned.getAnswerRecords().get(0).getQuestion().getQuestionType() == QuestionType.CHECKBOX;
//        assert returned.getAnswerRecords().get(1).getQuestion().getQuestionType() == QuestionType.CHECKBOX;
//    }
//
//    @Test
//    public void QuizGradingService_GradeTextType(){
//        // Arrange
//        var question1 = new QuizQuestion("What is the capital of Estonian:",true,null,new ArrayList<>());
//        var question2 = new QuizQuestion("Which of the following math operations equal to 4 :",true,null,new ArrayList<>());
//        var quiz = new Quiz("General knowledge","General knowledge",0,null,null,null,true);
//        quiz.setQuestions(List.of(question2,question1));
//        var record1 = new QuizAnswerRecord(question1,new ArrayList<>(),null);
//        var record2 = new QuizAnswerRecord(question2,new ArrayList<>(),null);
//        var attempt = new QuizAttempt(null,quiz, ProgressionStatus.COMPLETED);
//        attempt.setAnswerRecords(List.of(record1,record2));
//
//        // Act
//        var returned = this.quizGradingService.gradeQuizAttempt(attempt);
//
//        // Assert
//        assert !returned.isGraded();
//        assert returned.getTotalScore() == 0d;
//        assert !returned.getAnswerRecords().get(0).isGraded();
//        assert !returned.getAnswerRecords().get(1).isGraded();
//        assert returned.getAnswerRecords().get(0).getPointsEarned() == 0;
//        assert returned.getAnswerRecords().get(1).getPointsEarned() == 0;
//        assert returned.getAnswerRecords().get(0).getQuestion().getQuestionType() == QuestionType.TEXT;
//        assert returned.getAnswerRecords().get(1).getQuestion().getQuestionType() == QuestionType.TEXT;
//    }
//
//}
