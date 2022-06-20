package com.example.kata_pp_3_1_1.controller;

import com.example.kata_pp_3_1_1.Dto.Pagination;
import com.example.kata_pp_3_1_1.dao.UserDAO;
import com.example.kata_pp_3_1_1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserDAO userDAO;

    @Autowired
    public void setUserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUserByName() {
        return ResponseEntity.ok(userDAO.allUsers());
    }

//    @GetMapping("/pages")
//    public ResponseEntity<Map<String, Object>> pageAllUsers(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
//                                                            @RequestParam(value = "size", required = false, defaultValue = "2") int size) {
//        return ResponseEntity.ok(userDAO.pageUser(page, size));
//    }

    @GetMapping("/pag")
    public ResponseEntity<Pagination<Object>> pageUsers(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "2") int size,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int offset
    ) {
        return ResponseEntity.ok(userDAO.pagesTest(page, size, offset));
    }

//    @GetMapping("/pageUsers")
//    public ResponseEntity<Page<User>> pages(@RequestParam("page") int page, @RequestParam("size") int size) {
//        return ResponseEntity.ok(userDAO.userPage(PageRequest.of(page, size)));
//    }

    @GetMapping("/test")
    public ResponseEntity<Pagination<Object>> testAnnotation(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "4") int size,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int offset
    ) {
        return ResponseEntity.ok(userDAO.pagingTestAnnotation(page, size, offset));
    }

}
