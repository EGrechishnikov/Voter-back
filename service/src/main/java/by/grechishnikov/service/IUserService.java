package by.grechishnikov.service;

import by.grechishnikov.entity.User;

public interface IUserService extends IBaseService<User> {
    User createNewUser(User user);

    User get(String login);
}
