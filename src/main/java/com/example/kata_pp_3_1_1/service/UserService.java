package com.example.kata_pp_3_1_1.service;

import com.example.kata_pp_3_1_1.model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    void addUser(User user);
    void deleteUser(int id);
    void editUser(User user);
    User getUserId(int id);
}
