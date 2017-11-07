package by.grechishnikov.service;

import by.grechishnikov.entity.User;

public interface IUserService extends IBaseService<User> {
    boolean createNewUser(User user);

    boolean login(User user);

    User get(String login);
}
