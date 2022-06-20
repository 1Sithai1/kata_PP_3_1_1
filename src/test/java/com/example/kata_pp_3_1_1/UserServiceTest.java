package com.example.kata_pp_3_1_1;

import com.example.kata_pp_3_1_1.dao.UserDAO;
import com.example.kata_pp_3_1_1.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;

@SpringBootTest
class UserServiceTest {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    UserDAO userDAO;


    @Test
    void getUserTest() {

        User userAc = new User(1, "Василий", "Шведов", "asd@asd.ru");
        User user = entityManager.find(User.class, 1);
        Assertions.assertThat(userAc).isEqualTo(user);
    }

    @Test
    @Transactional
    void userByIdTest() {
        User user = new User("Петр", "Петров", "user@mail.ru");

        entityManager.persist(user);

        User testUser = entityManager.createQuery("select u from User u where u.name=:name", User.class)
                .setParameter("name", "Петр")
                .getSingleResult();

        Assertions.assertThat(user).isEqualTo(testUser);
    }

    @Test
    void paginationTest() {
        Map<String, Object> pages = userDAO.pageUser(0, 3);

        Assertions.assertThat(pages.keySet().size()).isEqualTo(3);
    }
}
