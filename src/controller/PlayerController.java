package controller;

import model.dto.play.ResultSummary;
import model.dto.quiz.QuizResponse;
import model.service.category.CategoryService;
import model.service.play.PlayerQuizEngine;
import model.service.quiz.QuizService;
import model.service.quizType.QuizTypeService;
import util.Singleton;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerController {

    private final CategoryService categoryService;
    private final QuizTypeService quizTypeService;
    private final QuizService quizService;
    private final PlayerQuizEngine engine;

    public PlayerController(CategoryService categoryService,
                            QuizTypeService quizTypeService,
                            QuizService quizService,
                            PlayerQuizEngine engine) {
        this.categoryService = categoryService;
        this.quizTypeService = quizTypeService;
        this.quizService = quizService;
        this.engine = engine;
    }

    public void startDashboard(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("===== Player Dashboard =====");
            System.out.println("1) Start Quiz");
            System.out.println("0) Back");
            System.out.print("==⇒ ");
            String line = scanner.nextLine().trim();
            if (line.equals("0")) return;
            if (line.equals("1")) startQuiz(scanner);
        }
    }

    private void startQuiz(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("===== Start Quiz =====");
            System.out.println("1) Take Specific Quiz");
            System.out.println("2) Take Mix Quiz");
            System.out.println("0) Back");
            System.out.print("==⇒ ");
            String c = scanner.nextLine().trim();
            if (c.equals("0")) return;
            switch (c) {
                case "1" -> takeSpecific(scanner);
                case "2" -> takeMix(scanner);
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void takeSpecific(Scanner scanner) {
        try {
            System.out.println();
            System.out.println("===== Take Specific Quiz =====");
            var categories = categoryService.getAllCategories();
            if (categories.isEmpty()) {
                System.out.println("No categories found.");
                return;
            }
            System.out.println("==⇒ Show All Categories");
            categories.forEach(c -> System.out.printf("%d) %s%n", c.categoryId(), c.categoryName()));
            System.out.print("==⇒ Select Category Id : ");
            int categoryId = Integer.parseInt(scanner.nextLine().trim());

            Integer quizTypeId = selectQuizType(scanner);
            if (quizTypeId == null) return;

            int numberOfQuestions = selectNumberOfQuestions(scanner);
            if (numberOfQuestions == 0) return;

            // Retrieve all quizzes (question+answers) then filter by chosen category & type
            List<QuizResponse> all = quizService.getAllQuizzes(); // we will filter
            List<QuizResponse> filtered = all.stream()
                    .filter(q -> q.categoryName() != null)
                    .filter(q -> q.quizTypeName() != null)
                    .filter(q -> matchesCategory(categories, q.categoryName(), categoryId))
                    .filter(q -> matchesQuizType(q.quizTypeName(), quizTypeId))
                    .collect(Collectors.toList());

            engine.play(filtered, numberOfQuestions, scanner);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void takeMix(Scanner scanner) {
        try {
            System.out.println();
            System.out.println("===== Take Mix Quiz =====");
            Integer quizTypeId = selectQuizType(scanner);
            if (quizTypeId == null) return;

            int numberOfQuestions = selectNumberOfQuestions(scanner);
            if (numberOfQuestions == 0) return;

            List<QuizResponse> all = quizService.getAllQuizzes();
            // filter only by quiz type and keep all categories (mix)
            List<QuizResponse> filtered = all.stream()
                    .filter(q -> matchesQuizType(q.quizTypeName(), quizTypeId))
                    .collect(Collectors.toList());

            engine.play(filtered, numberOfQuestions, scanner);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private Integer selectQuizType(Scanner scanner) {
        try {
            var types = quizTypeService.getAllQuizTypes();
            if (types.isEmpty()) {
                System.out.println("No quiz types found.");
                return null;
            }
            System.out.println("=== Select Quiz Type ===");
            types.forEach(t -> System.out.printf("%d) %s%n", t.quizTypeId(), t.quizTypeName()));
            System.out.print("==⇒ ");
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    private int selectNumberOfQuestions(Scanner scanner) {
        System.out.println("=== Number Of Question ===");
        System.out.println("1) 10");
        System.out.println("2) 15");
        System.out.println("3) 20");
        System.out.print("==⇒ ");
        String c = scanner.nextLine().trim();
        return switch (c) {
            case "1" -> 10;
            case "2" -> 15;
            case "3" -> 20;
            default -> 0;
        };
    }

    private boolean matchesCategory(java.util.List<model.dto.category.CategoryResponse> categories, String categoryName, int selectedId) {
        var opt = categories.stream().filter(c -> c.categoryId() == selectedId).findFirst();
        return opt.filter(categoryResponse -> categoryResponse.categoryName().equalsIgnoreCase(categoryName)).isPresent();
    }

    private boolean matchesQuizType(String quizTypeName, int selectedTypeId) {
        var types = quizTypeService.getAllQuizTypes();
        var opt = types.stream().filter(t -> t.quizTypeId() == selectedTypeId).findFirst();
        return opt.filter(t -> t.quizTypeName().equalsIgnoreCase(quizTypeName)).isPresent();
    }
}