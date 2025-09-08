package util;

import model.entity.Category;

import java.util.regex.Pattern;

public class ValidationUtil {


        public static String isValidEmail(String email) {
            if (email == null || email.isBlank()) return "Email is required";
            String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

            if (!Pattern.matches(regex, email)) return "Invalid email format";
            return null;
        }

        public static String isValidPassword(String password) {
            if (password == null || password.isBlank()) return "Password is required";
            if (password.length() < 6) return "Password must be at least 6 characters";
            return null;
        }

        public static String isValidUsername(String username) {
            if (username == null || username.isBlank()) return "Username is required";
            return null;
        }

        public static String isValidQuestionText(String question) {
            if (question == null || question.isBlank()) return "Question text is required";
            return null;
        }

        public static String isValidAnswerText(String answer) {
            if (answer == null || answer.isBlank()) return "Answer text is required";
            return null;
        }

        public static String isValidScore(Integer score, Integer totalQuestions) {
            if (score == null || totalQuestions == null) return "Score and total questions are required";
            if (score < 0 || score > totalQuestions) return "Invalid score value";
            return null;
        }


        public static String isValidCategoryName(String name)
        {
            if(name == null || name.isBlank()) return "Category name is required";
            return null;
        }



}
