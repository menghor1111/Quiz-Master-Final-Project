package model.repository.user;

import model.entity.User;

public interface UserRepository {
       User saveUser(User user);
       User findByEmail(String email);

}
