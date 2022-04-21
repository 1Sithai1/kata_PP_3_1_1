package com.example.kata_pp_3_1_1.dao;



import com.example.kata_pp_3_1_1.model.User;

import java.util.List;

public interface UserDAO {
    List<User> allUsers();
    void addUser(User user);
    void deleteUser(int id);
    void editUser(int id, User user);
    User getUserId(int id);
}
