package model.service.quiz;

import exception.QuizException;
import model.dto.quiz.QuizRequest;
import model.dto.quiz.QuizResponse;
import model.entity.Answer;
import model.entity.Question;
import model.mapper.quiz.QuizMapper;
import model.repository.quiz.QuizRepository;
import util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class QuizServiceImpl implements QuizService{

    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;

    public QuizServiceImpl(QuizRepository quizRepository, QuizMapper quizMapper) {
        this.quizRepository = quizRepository;
        this.quizMapper = quizMapper;
    }

    @Override
    public boolean createQuiz(QuizRequest request) throws QuizException {
            validationQuizRequest(request);


//            Question question = quizMapper.toQuestionEntity(request);
            List<Answer> answers = new ArrayList<>();
            final boolean[] existsAnswer = {false};
            request.answers().forEach(a->{
                answers.add(quizMapper.toAnswerEntity(a));
            });


           int questionId = quizRepository.insertQuestion(quizMapper.toQuestionEntity(request));

               answers.forEach(a->{
                   a.setQuestionId(questionId);
               });


               answers.forEach(a->{
                   existsAnswer[0] = quizRepository.insertAnswer(a);
               });

           return existsAnswer[0] && questionId > 0;

    }

    @Override
    public List<QuizResponse> getAllQuizzes() throws QuizException {

          return quizRepository.findAllQuizzes(null,null);
    }



    private void validationQuizRequest(QuizRequest request)
    {
        String questionTextError = ValidationUtil.isValidQuestionText(request.questionText());
        if(questionTextError != null) throw new QuizException(questionTextError);

        List<String> answersError = new ArrayList<>();
        request.answers().forEach(a->{
            answersError.add(ValidationUtil.isValidAnswerText(a.answerText()));
        });

        answersError.forEach(a->{
            if(a != null) throw new QuizException(a);
        });
    }





}
