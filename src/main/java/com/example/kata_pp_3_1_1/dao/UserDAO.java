package com.example.kata_pp_3_1_1.dao;


import com.example.kata_pp_3_1_1.Dto.Pagination;
import com.example.kata_pp_3_1_1.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface UserDAO {
    List<User> allUsers();

    void addUser(User user);

    void deleteUser(int id);

    void editUser(int id, User user);

    User getUserId(int id);

    User findByUsername(String username);

    Map<String, Object> pageUser(int page, int size);

    Page<User> userPage(Pageable pageable);

    Pagination<Object> pagesTest(int page, int size, int offset);

    Pagination<Object> pagingTestAnnotation(int page, int size, int offset);
}