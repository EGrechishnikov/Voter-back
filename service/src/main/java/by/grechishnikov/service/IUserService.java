package by.grechishnikov.service;

import by.grechishnikov.entity.User;

public interface IUserService extends IBaseService<User> {
    User get(String login);
}
