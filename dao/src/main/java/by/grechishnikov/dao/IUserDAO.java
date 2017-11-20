package by.grechishnikov.dao;

import by.grechishnikov.entity.User;

public interface IUserDAO extends IBaseDAO<User> {
    User get(String login);

    boolean isLoginFree(String login);
}