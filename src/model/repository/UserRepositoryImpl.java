package model.repository;

import model.Result;
import model.User;
import model.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository{
    @Override
    public void saveUser(User user) {
        try(Connection conn = DbConnection.getInstance()) {
            conn.setAutoCommit(false);


                String sql = """
                        insert into users(username, email, password, role_id)
                    values (?, ?, ?, ?)
   

                   """;

            try(PreparedStatement ps = conn.prepareStatement( sql)){
                ps.setString(1,user.getUsername());
                ps.setString(2,user.getEmail());
                ps.setString(3,user.getPassword());
                ps.setInt(4,user.getRoleId());

                int rowAffected = ps.executeUpdate();
                if(rowAffected == 0)
                {
                    throw new SQLException("Error saving user");
                }

                conn.commit();


            }catch (SQLException e)
            {
                conn.rollback();
                e.printStackTrace();
            }
            finally {
                conn.setAutoCommit(true);
            }
            }catch (SQLException e)
            {
             e.printStackTrace();
            }

    }


    public User findByEmail(String email) {
        String sql = """
                    SELECT u.user_id, u.username, u.email, u.password, u.role_id, r.role_type
                    FROM users u
                    JOIN roles r ON u.role_id = r.role_id
                    WHERE u.email = ?
                """;

        try (Connection conn = DbConnection.getInstance();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRoleId(rs.getInt("role_id"));
                user.setRoleType(rs.getString("role_type"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    }


}
