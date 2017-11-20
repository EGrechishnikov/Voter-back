package by.grechishnikov.service;

import by.grechishnikov.dto.UserDTO;
import by.grechishnikov.entity.User;

public interface IUserService extends IBaseService<User> {
    boolean createNewUser(User user);

    UserDTO login(User user);

    User get(String login);
}
