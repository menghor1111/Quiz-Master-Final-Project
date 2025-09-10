package model.repository.user;

import model.entity.User;
import model.db.DbConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public User saveUser(User user) {

        String hashedPassword = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt());
        user.setPassword(hashedPassword);
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
                ps.setInt(4,3);

                int rowAffected = ps.executeUpdate();
                if(rowAffected == 0)
                {
                    throw new SQLException("Error saving user");
                }

                conn.commit();
                return findByEmail(user.getEmail());


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


        return null;
    }


    @Override
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






    @Override
    public User findByEmailAndPassword(String email, String password) {
           User user = findByEmail(email);
           if(user == null) return null;

           if(BCrypt.checkpw(password,user.getPassword()))
            {
                return user;
            }

           return null;
    }

    @Override
    public User findUserById(int userId,int roleId) {
        String sql = """
            SELECT u.user_id, u.username, u.email, u.password, u.role_id, r.role_type
            FROM users u
            JOIN roles r ON u.role_id = r.role_id
            WHERE u.user_id = ? AND u.role_id = ?;
            """;

        try (Connection conn = DbConnection.getInstance();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2,roleId);
            try(ResultSet rs = ps.executeQuery()){

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
                return null; // user not found
            }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteUserById(int userId) {
        String sql = """
            DELETE FROM users
            WHERE user_id = ?;
            """;

        try (Connection conn = DbConnection.getInstance();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User findByUsername(String userName) {


        String sql = """
            SELECT u.user_id, u.username, u.email, u.password, u.role_id, r.role_type
            FROM users u
            JOIN roles r ON u.role_id = r.role_id
            WHERE u.username = ? ;
            """;

        try (Connection conn = DbConnection.getInstance();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userName);
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

    @Override
    public boolean updateUser(User user) {

        if(user.getPassword() != null && !user.getPassword().isEmpty())
        {
            String hasPassword = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt());
            user.setPassword(hasPassword);
        }

        String sql = """
                UPDATE users
                SET username = COALESCE(?,username),
                    email = COALESCE(?,email),
                    password = COALESCE(?,password)
                WHERE user_id = ?;
                """;

        try(Connection conn = DbConnection.getInstance();
            PreparedStatement ps = conn.prepareStatement(sql))
        {

            ps.setString(1,user.getUsername());
            ps.setString(2,user.getEmail());
            ps.setString(3,user.getPassword());
            ps.setInt(4,user.getUserId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        }catch (SQLException e)
        {
            throw new RuntimeException("Database error : " + e.getMessage(),e);
        }

    }

    @Override
    public List<User> getAllUsers(int roleId)  {


       String sql = """
               SELECT u.user_id, u.username, u.email, u.password, u.created_at, u.updated_at
               FROM users u
               WHERE u.role_id = ?
               """;


       try(Connection conn = DbConnection.getInstance();
           PreparedStatement ps = conn.prepareStatement(sql))
       {
           ps.setInt(1,roleId);

         try(ResultSet rs = ps.executeQuery()) {

             List<User> users = new ArrayList<>();
             while (rs.next()) {
                 users.add(new User(
                         rs.getInt("user_id"),
                         rs.getString("username"),
                         rs.getString("email"),
                         rs.getString("password"),
                         String.valueOf(rs.getTimestamp("created_at")),
                         String.valueOf(rs.getTimestamp("updated_at"))

                 ));

             }

             return users;

         }

       }catch (SQLException e)
       {
           throw new RuntimeException("Database error : " + e.getMessage());
       }


    }










}
