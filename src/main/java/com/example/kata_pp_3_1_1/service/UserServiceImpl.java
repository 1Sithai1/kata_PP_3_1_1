package com.example.kata_pp_3_1_1.service;

import com.example.kata_pp_3_1_1.dao.UserDAO;
import com.example.kata_pp_3_1_1.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> allUsers() {
        return userDAO.allUsers();
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    @Override
    public void editUser(int id, User user) {
        userDAO.editUser(id, user);
    }

    @Override
    public User getUserId(int id) {
        return userDAO.getUserId(id);
    }
}
