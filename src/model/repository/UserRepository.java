package model.repository;

import model.User;

public interface UserRepository {
       void saveUser(User user);
       User findByEmail(String email);

}
