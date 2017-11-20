package by.grechishnikov.dao;

import by.grechishnikov.entity.User;

/**
 * DAO for User entity
 */
public interface IUserDAO extends IBaseDAO<User> {
    /**
     * Get User from DB by login
     *
     * @param login - login
     * @return - bean
     */
    User get(String login);

    /**
     * Check is login free
     *
     * @param login - login
     * @return - true if free
     */
    boolean isLoginFree(String login);
}