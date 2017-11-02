package by.grechishnikov.service.impl;

import by.grechishnikov.dao.IUserDAO;
import by.grechishnikov.entity.User;
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
        userDAO.saveOrUpdate(user);
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
}
