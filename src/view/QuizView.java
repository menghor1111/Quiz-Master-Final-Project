package view;

import model.dto.quiz.AnswerResponse;
import model.dto.quiz.QuizResponse;

import java.util.List;
import java.util.Scanner;

public class QuizView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public String showQuestionText(){
        System.out.print("Enter question text : ");
        return SCANNER.nextLine();
    }

    public String showOption(String optionKey){
        System.out.printf("Enter Option %s : ",optionKey);
        return SCANNER.nextLine();

    }

    public String showCorrectAnswer(){
        System.out.print("Enter correct answer : ");
        return SCANNER.nextLine();
    }

    public String showCorrectAnswerForTrueFalse(){
        System.out.print("Enter correct answer(True or False) : ");
        return SCANNER.nextLine();
    }

    public int showCategoryId(){
        System.out.println("Enter category id : ");
        return Integer.parseInt(SCANNER.nextLine());
    }

    public int showQuizType(){
        System.out.println("Enter quiz type :");
        return Integer.parseInt(SCANNER.nextLine());
    }

    public void showAllQuizzes(List<QuizResponse> quizResponses)
    {
        quizResponses.forEach(q->{
            System.out.println(q.categoryName());
            System.out.println(q.quizTypeName());
            System.out.println(q.answers());
            q.answers().forEach(System.out::println);
        });
    }


    public void printQuizzes(List<QuizResponse> quizzes) {
        for (QuizResponse quiz : quizzes) {
            System.out.println("Question ID: " + quiz.questionId());
            System.out.println("Category: " + quiz.categoryName());
            System.out.println("Type: " + quiz.quizTypeName());
            System.out.println("Creator: " + quiz.creatorName());
            System.out.println("Question: " + quiz.questionText());

            List<AnswerResponse> answers = quiz.answers();
            if (answers.isEmpty()) {
                System.out.println("No answers available.");
            } else {
                System.out.println("Answers:");
                for (AnswerResponse a : answers) {
                    // Handle Multiple Choice vs True/False / Fill in the Blank
                    String key = a.optionKey() != null ? a.optionKey() : "";
                    System.out.printf("  %s %s [%s]\n", key, a.answerText(), a.isCorrect());
                }
            }

            System.out.println("--------------------------------------------------");
        }
    }


}
