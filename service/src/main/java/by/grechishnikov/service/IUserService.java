package by.grechishnikov.service;

import by.grechishnikov.dto.UserDTO;
import by.grechishnikov.entity.User;

/**
 * Service for User entity
 */
public interface IUserService extends IBaseService<User> {
    /**
     * Create and initialize a new user
     *
     * @param user - user
     * @return - true if users fields are valid
     */
    boolean createNewUser(User user);

    /**
     * Do login
     *
     * @param user - user
     * @return - user DTO if all is ok. Else return null
     */
    UserDTO login(User user);

    /**
     * Get user by login
     *
     * @param login - login in DB
     * @return - entity
     */
    User get(String login);
}
