package controller;

import exception.QuizException;
import model.dto.quiz.AnswerRequest;
import model.dto.quiz.QuizRequest;
import model.entity.Answer;
import model.service.quiz.QuizService;
import util.ConstantsUtil;
import util.Session;
import util.Singleton;
import view.QuizView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuizController {
    private final QuizService quizService;
    private final QuizView quizView;
    private final CategoryController categoryController;
    private final QuizTypeController quizTypeController;

    public QuizController(QuizService quizService, QuizView quizView, CategoryController categoryController, QuizTypeController quizTypeController) {
        this.quizService = quizService;
        this.quizView = quizView;
        this.categoryController = categoryController;
        this.quizTypeController = quizTypeController;
    }

    public void createQuiz(){
        while (true) {
            try {
                int categoryId = categoryController.getCategoryId();
                int quizTypId = quizTypeController.getQuizTypeId();
                String questionText = quizView.showQuestionText();
                String correctAnswer;
                List<AnswerRequest> answerRequests = new ArrayList<>();

                if (quizTypId == ConstantsUtil.TRUE_FALSE_ID) {
                    correctAnswer = quizView.showCorrectAnswerForTrueFalse();
                    answerRequests.add(new AnswerRequest(null,correctAnswer,true));
                } else if (quizTypId == ConstantsUtil.FILL_IN_THE_BLANK_ID) {
                    correctAnswer = quizView.showCorrectAnswer();
                    answerRequests.add(new AnswerRequest(null,correctAnswer,true));
                } else if (quizTypId == ConstantsUtil.MULTIPLE_CHOICE_ID) {
                    List<String> optionKeys = new ArrayList<>(List.of("A", "B", "C", "D"));
                    answerRequests = optionKeys.stream()
                            .map(option ->
                            {
                                String answerText = quizView.showOption(option);
                                return new AnswerRequest(option, answerText, false);
                            })
                            .toList();


                    correctAnswer = quizView.showCorrectAnswer();
                    answerRequests = answerRequests.stream()
                            .map(a -> new AnswerRequest(
                                    a.optionKey(), a.answerText(), a.answerText().equals(correctAnswer))).toList();
                }
                int creatorId = Session.getSessionInstance().getCurrenUser().getUserId();
                QuizRequest quizRequest = new QuizRequest(categoryId,quizTypId,creatorId,questionText,answerRequests);
                if(quizService.createQuiz(quizRequest))
                {
                    System.out.println("Quiz created successfully.");
                    break;
                }
                else
                {
                    System.out.println("false to create quiz");
                }

            }catch (QuizException e)
            {
                System.out.println("Error creating quiz : " + e.getMessage());
            }
        }
    }

    public void viewAllQuizzes(){
        quizView.printQuizzes(quizService.getAllQuizzes());
    }


}
