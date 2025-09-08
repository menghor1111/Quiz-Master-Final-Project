package controller;

import model.dto.quizType.QuizTypeResponse;
import model.service.quizType.QuizTypeService;
import view.QuizTypeView;

import java.util.List;

public class QuizTypeController {

    private final QuizTypeService quizTypeService;
    private final QuizTypeView quizTypeView;

    public QuizTypeController(QuizTypeService quizTypeService, QuizTypeView quizTypeView) {
        this.quizTypeService = quizTypeService;
        this.quizTypeView = quizTypeView;
    }

    public int getQuizTypeId(){

        try{
            List<QuizTypeResponse> quizTypeResponses = quizTypeService.getAllQuizTypes();
            if(quizTypeResponses == null)
            {
                System.out.println("There no quiz type yet.");
                return 0;
            }

            return quizTypeView.showQuizType(quizTypeResponses);

        }catch (RuntimeException e)
        {
            System.out.println("Show quiz type error : " + e.getMessage());
        }
        return 0;
    }
}
