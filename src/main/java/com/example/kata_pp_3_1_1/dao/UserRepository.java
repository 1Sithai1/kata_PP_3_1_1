package com.example.kata_pp_3_1_1.dao;

import com.example.kata_pp_3_1_1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
