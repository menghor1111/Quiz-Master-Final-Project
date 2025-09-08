package model.repository.user;

import model.entity.User;

import java.util.List;

public interface UserRepository {
       User saveUser(User user);
       User findByEmail(String email);
       User findByEmailAndPassword(String email,String password);
       User findUserById(int userId,int roleId);
       boolean deleteUserById(int userId);
       User findByUsername(String userName);
       boolean updateUser(User user);
       List<User> getAllUsers(int roleId);


}
