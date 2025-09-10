package model.service.play;

import model.dto.play.ResultSummary;
import model.dto.quiz.AnswerResponse;
import model.dto.quiz.QuizResponse;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PlayerQuizEngineImpl implements PlayerQuizEngine {

    @Override
    public ResultSummary play(List<QuizResponse> all, int numberOfQuestions, Scanner scanner) {
        if(all == null || all.isEmpty()) {
            System.out.println("No questions available for the chosen options.");
            return new ResultSummary(0,0,0,0,0);
        }

        // Shuffle and take N
        List<QuizResponse> questions = new ArrayList<>(all);
        Collections.shuffle(questions);
        if (numberOfQuestions < questions.size()) {
            questions = questions.subList(0, numberOfQuestions);
        }

        int correct = 0;
        int score = 0;
        long start = System.nanoTime();

        int index = 1;
        for (QuizResponse q : questions) {
            System.out.println();
            System.out.println("Q" + index + ": " + q.questionText());
            // Shuffle answers to avoid position bias
            List<AnswerResponse> options = new ArrayList<>(q.answers());
            Collections.shuffle(options);
            Map<String, AnswerResponse> labelMap = new LinkedHashMap<>();
            char label = 'A';
            for (AnswerResponse a : options) {
                String key = String.valueOf(label);
                labelMap.put(key, a);
                System.out.printf("  %s) %s%n", key, a.answerText());
                label++;
            }
            String userChoice;
            while (true) {
                System.out.print("Your answer (A/B/C/D...): ");
                userChoice = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
                if (labelMap.containsKey(userChoice)) break;
                System.out.println("Invalid option. Try again.");
            }
            boolean isCorrect = labelMap.get(userChoice).isCorrect();
            if (isCorrect) {
                correct++;
                score++; // simple scoring: 1 per correct
                System.out.println("✅ Correct!");
            } else {
                // show correct one(s)
                String correctKeys = labelMap.entrySet().stream()
                        .filter(e -> e.getValue().isCorrect())
                        .map(Map.Entry::getKey)
                        .collect(Collectors.joining(", "));
                System.out.println("❌ Incorrect. Correct answer: " + correctKeys);
            }
            index++;
        }

        long elapsed = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - start);
        int total = questions.size();
        double avg = total == 0 ? 0 : (double) score / total;

        System.out.println();
        System.out.println("===== Result =====");
        System.out.println("Correct Answers : " + correct + " / " + total);
        System.out.println("Total Score     : " + score);
        System.out.printf("Average         : %.2f%n", avg);
        System.out.println("Time (seconds)  : " + elapsed);

        return new ResultSummary(total, correct, score, avg, elapsed);
    }
}