package com.example.kata_pp_3_1_1.dao;

import com.example.kata_pp_3_1_1.model.User;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(int id) {
        User user1 = entityManager.find(User.class, id);
        entityManager.remove(user1);
    }

    @Override
    public void editUser(int id, User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserId(int id) {
        return entityManager.find(User.class, id);
    }
}
