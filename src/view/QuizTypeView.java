package view;

import model.dto.quizType.QuizTypeResponse;

import java.util.List;
import java.util.Scanner;

public class QuizTypeView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public int showQuizType(List<QuizTypeResponse> quizTypeResponses)
    {
        System.out.println("==== Quiz Type ====");
        quizTypeResponses.forEach(q->{
            System.out.printf("%d. %s\n",q.quizTypeId(),q.quizTypeName());
        });
        System.out.print("Enter quiz type id : ");
        return Integer.parseInt(SCANNER.nextLine());
    }

}
