package view;

import controller.PlayerController;
import model.service.category.CategoryService;
import model.service.play.PlayerQuizEngine;
import model.service.play.PlayerQuizEngineImpl;
import model.service.quiz.QuizService;
import model.service.quizType.QuizTypeService;
import util.Singleton;

import java.util.Scanner;

public class PlayerDashboardView {

    private final PlayerController controller;

    public PlayerDashboardView(CategoryService categoryService,
                               QuizTypeService quizTypeService,
                               QuizService quizService) {
        PlayerQuizEngine engine = new PlayerQuizEngineImpl();
        this.controller = new PlayerController(categoryService, quizTypeService, quizService, engine);
    }

    public void open() {
        Scanner scanner = new Scanner(System.in);
        controller.startDashboard(scanner);
    }

    public static void main(String[] args) {
        // Standalone runner for quick testing
        PlayerDashboardView view = new PlayerDashboardView(
                Singleton.getCategoryServiceInstance(),
                Singleton.getQuizTypeServiceInstance(),
                Singleton.getQuizServiceInstance()
        );
        view.open();
    }
}