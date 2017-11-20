package by.grechishnikov.service.impl;

import by.grechishnikov.dao.IUserDAO;
import by.grechishnikov.dto.UserDTO;
import by.grechishnikov.entity.User;
import by.grechishnikov.sequrity.PasswordHash;
import by.grechishnikov.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {
    private IUserDAO userDAO;

    @Autowired
    public UserService(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void saveOrUpdate(User user) {
        if (isUsersParamValid(user)) {
            if(user.getSalt() != null) {
                userDAO.saveOrUpdate(user);
            } else {
                createNewUser(user);
            }
        }
    }

    @Override
    public User get(int id) {
        return userDAO.get(id);
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public User get(String login) {
        return userDAO.get(login);
    }

    private boolean isUsersParamValid(User user) {
        return user.getLogin().length() > 0 && user.getPassword().length() > 0;
    }

    private boolean isLoginFree(String login) {
        return userDAO.isLoginFree(login);
    }

    @Override
    public boolean createNewUser(User user) {
        if(isUsersParamValid(user) && isLoginFree(user.getLogin())) {
            user.setSalt(PasswordHash.getSalt());
            hashUserPassword(user);
            saveOrUpdate(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserDTO login(User user) {
        if(isUsersParamValid(user)) {
            User userInDB = get(user.getLogin());
            if(userInDB != null && userInDB.getLogin().equals(user.getLogin())) {
                user.setSalt(userInDB.getSalt());
                hashUserPassword(user);
                if(user.getPassword().equals(userInDB.getPassword())) {
                    return new UserDTO(userInDB.getId(), userInDB.getLogin());
                }
            }
        }
        return null;
    }

    private void hashUserPassword(User user) {
        String newPassword = PasswordHash.getHex(user.getSalt() + user.getPassword());
        user.setPassword(newPassword);
    }
}
