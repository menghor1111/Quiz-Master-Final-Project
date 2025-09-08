package model.service.quizType;

import model.dto.quizType.QuizTypeResponse;
import model.entity.QuizType;
import model.mapper.quizType.QuizTypeMapper;
import model.repository.quizType.QuizTypeRepository;

import java.util.ArrayList;
import java.util.List;

public class QuizTypeServiceImpl implements QuizTypeService{

    private final QuizTypeRepository quizTypeRepository;
    private final QuizTypeMapper quizTypeMapper;

    public QuizTypeServiceImpl(QuizTypeRepository quizTypeRepository, QuizTypeMapper quizTypeMapper) {
        this.quizTypeRepository = quizTypeRepository;
        this.quizTypeMapper = quizTypeMapper;
    }

    @Override
    public List<QuizTypeResponse> getAllQuizTypes() {

           List<QuizType> quizTypes = quizTypeRepository.getAllQuizType();
           List<QuizTypeResponse> quizTypeResponses = new ArrayList<>();
           quizTypes.forEach(q ->{
               quizTypeResponses.add(quizTypeMapper.toQuizTypeResponse(q));
           });
           return quizTypeResponses;
    }
}
