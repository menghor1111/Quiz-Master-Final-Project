package model.repository.quiz;

import exception.QuizException;
import model.db.DbConnection;
import model.dto.quiz.AnswerResponse;
import model.dto.quiz.QuizResponse;
import model.entity.Answer;
import model.entity.Question;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QuizRepositoryImpl implements  QuizRepository{
    @Override
    public int insertQuestion(Question question) {

        if(existsByQuestionText(question.getQuestionText()))
        {
            throw new QuizException("This question already exists");
        }

        try(Connection conn = DbConnection.getInstance())
        {

            String sql = """
                    INSERT INTO questions(category_id, quiz_type_id, creator_id,question_text)
                    VALUES(?, ?, ?, ?)
                    """;
            try(PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setInt(1,question.getCategoryId());
                ps.setInt(2,question.getQuizTypeId());
                ps.setInt(3,question.getCreatorId());
                ps.setString(4,question.getQuestionText());

                int rowsAffect = ps.executeUpdate();
                if(rowsAffect == 0)
                {
                    throw new SQLException("Error insert question");
                }

                return findByQuestionText(question.getQuestionText()).getQuestionId();


            }

        }catch (SQLException e)
        {

            throw new QuizException("Database error : " + e.getMessage());

        }
    }

    @Override
    public boolean insertAnswer(Answer answer) {

        if(existsAnswer(answer))
        {
            throw new QuizException("This answer already exists");
        }

        try(Connection conn = DbConnection.getInstance())
        {
            String sql = """
                    INSERT INTO answers(question_id, option_key, answer_text, is_correct)
                    VALUES (?, ?, ?, ?)
                    """;

            try(PreparedStatement ps = conn.prepareStatement(sql))
            {

                ps.setInt(1,answer.getQuestionId());
                ps.setString(2,answer.getOptionKey());
                ps.setString(3,answer.getAnswerText());
                ps.setBoolean(4,answer.isCorrect());

                int rowsAffect = ps.executeUpdate();
                if(rowsAffect == 0)
                {
                    throw new SQLException("Error insert answer");
                }

                return rowsAffect > 0;

            }


        }catch (SQLException e)
        {
            throw new QuizException("Database error : " + e.getMessage());
        }


    }

    @Override
    public boolean existsByQuestionText(String questionText) {
        try(Connection conn = DbConnection.getInstance())
        {
            String sql = """
                    SELECT 1 FROM questions
                    WHERE question_text = ?
                    """;

            try(PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setString(1,questionText);

                try(ResultSet rs = ps.executeQuery())
                {
                   return rs.next();

                }
            }
        }catch (SQLException e)
        {
            throw new QuizException("Database error : " + e.getMessage());
        }
    }

    @Override
    public boolean existsAnswer(Answer answer) {
        try(Connection conn = DbConnection.getInstance())
        {
            String sql = """
                    SELECT 1 FROM answers
                    WHERE question_id  = ? AND answer_text = ?
                    """;

            try(PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setInt(1,answer.getQuestionId());
                ps.setString(2,answer.getAnswerText());

                try(ResultSet rs = ps.executeQuery())
                {
                    return rs.next();
                }
            }

        }catch (SQLException e)
        {
            throw new QuizException("Database error : " + e.getMessage());
        }
    }

    @Override
    public Question findByQuestionText(String questionText) {
        try(Connection conn = DbConnection.getInstance())
        {
            String sql = """
                    SELECT * FROM questions
                    WHERE question_text = ?
                    """;

            try(PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setString(1,questionText);

                try(ResultSet rs = ps.executeQuery())
                {

                    if(rs.next()) {
                        return new Question(
                                rs.getInt("question_id"),
                                rs.getInt("category_id"),
                                rs.getInt("quiz_type_id"),
                                rs.getInt("creator_id"),
                                rs.getString("question_text"),
                                String.valueOf(rs.getTimestamp("created_at")),
                                String.valueOf(rs.getTimestamp("updated_at"))
                        );
                    }
                    else
                    {
                        return null;
                    }

                }
            }
        }catch (SQLException e)
        {
            throw new QuizException("Database error : " + e.getMessage());
        }
    }









    @Override

    public List<QuizResponse> findAllQuizzes(Integer categoryId, Integer quizTypeId) {
        try (Connection conn = DbConnection.getInstance()) {

            StringBuilder sql = new StringBuilder("""
                SELECT q.question_id, q.question_text,
                       c.category_name, qt.type_name, u.username AS creator_name,
                       a.answer_id, a.option_key, a.answer_text, a.is_correct
                FROM questions q
                JOIN categories c ON q.category_id = c.category_id
                JOIN quiz_types qt ON q.quiz_type_id = qt.quiz_type_id
                JOIN users u ON q.creator_id = u.user_id
                JOIN answers a ON q.question_id = a.question_id
                """);

            List<Integer> params = new ArrayList<>();
            List<String> conditions = new ArrayList<>();

            if (categoryId != null) {
                params.add(categoryId);
                conditions.add("q.category_id = ?");
            }
            if (quizTypeId != null) {
                params.add(quizTypeId);
                conditions.add("q.quiz_type_id = ?");
            }

            if (!conditions.isEmpty()) {
                sql.append(" WHERE ").append(String.join(" AND ", conditions));
            }

            sql.append(" ORDER BY q.question_id, a.option_key");

            Map<Integer, QuizResponse> quizMap = new LinkedHashMap<>();

            try (PreparedStatement ps = conn.prepareStatement(sql.toString())) {

                for (int i = 0; i < params.size(); i++) {
                    ps.setObject(i + 1, params.get(i), Types.INTEGER);
                }

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        int questionId = rs.getInt("question_id");

                        // Create question only once
                        quizMap.putIfAbsent(questionId, new QuizResponse(
                                questionId,
                                rs.getString("category_name"),
                                rs.getString("type_name"),
                                rs.getString("creator_name"),
                                rs.getString("question_text"),
                                new ArrayList<>()
                        ));

                        // Add answer to the corresponding question
                        QuizResponse quizResponse = quizMap.get(questionId);
                        quizResponse.answers().add(new AnswerResponse(
                                rs.getInt("answer_id"),
                                rs.getString("option_key"),
                                rs.getString("answer_text"),
                                rs.getBoolean("is_correct")
                        ));
                    }
                }
            }

            return new ArrayList<>(quizMap.values());

        } catch (SQLException e) {
            throw new QuizException("Database error: " + e.getMessage());
        }
    }

















}
