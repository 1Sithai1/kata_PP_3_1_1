package com.example.kata_pp_3_1_1.controller;

import com.example.kata_pp_3_1_1.model.User;
import com.example.kata_pp_3_1_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String allUser(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "users";
    }

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("users") User user) {
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("users") @Valid User user
            , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        } else {
            userService.addUser(user);
            return "redirect:/";
        }
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("users", userService.getUserId(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("users") @Valid User user
            , BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "edit";
        } else {
            userService.editUser(user);
            return "redirect:/";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
