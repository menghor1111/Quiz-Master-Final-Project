package model.service.play;

import model.dto.play.ResultSummary;
import model.dto.quiz.QuizResponse;

import java.util.List;
import java.util.Scanner;

public interface PlayerQuizEngine {
    ResultSummary play(List<QuizResponse> questions, int numberOfQuestions, Scanner scanner);
}