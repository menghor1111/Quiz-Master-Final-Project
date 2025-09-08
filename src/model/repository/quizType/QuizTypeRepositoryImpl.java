package model.repository.quizType;

import model.db.DbConnection;
import model.entity.QuizType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizTypeRepositoryImpl implements QuizTypeRepository {


    @Override
    public List<QuizType> getAllQuizType() {
        try(Connection conn = DbConnection.getInstance())
        {
            String sql = """
                    SELECT * FROM quiz_types
                    """;

            try(PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery())
            {
                List<QuizType> quizTypes = new ArrayList<>();
                while (rs.next())
                {
                    quizTypes.add(new QuizType(
                            rs.getInt("quiz_type_id"),
                            rs.getString("type_name")
                    ));
                }
                return quizTypes;
            }

        }catch (SQLException e)
        {
            throw new RuntimeException("Database error : " + e.getMessage());
        }
    }
}
