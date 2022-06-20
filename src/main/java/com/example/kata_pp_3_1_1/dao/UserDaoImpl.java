package com.example.kata_pp_3_1_1.dao;

import com.example.kata_pp_3_1_1.Dto.Pagination;
import com.example.kata_pp_3_1_1.annotationTest.TestAnnotation;
import com.example.kata_pp_3_1_1.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    //Метод реализация пагинации на странице!
    @Override
    public Map<String, Object> pageUser(int page, int size) {


        Map<String, Object> content = new LinkedHashMap<>();

        //Переменная count содержит в себе число всех пользователей в БД
        Long count = entityManager.createQuery("select count (u.id) from User u", Long.class)
                .getSingleResult();

        //Переменная maxPages содержит в себе максимальное количество страниц
        int maxPages = (int) Math.ceil((double) count / (double) size);

        List<User> users = entityManager.createQuery("select u from User u order by u.id", User.class)
                .setFirstResult((page) * size)
                .setMaxResults(size)
                .getResultList();

        content.put("content", users);
        content.put("count", count);
        content.put("maxPages", maxPages);


        return content;
    }

    @Override
//    @TestAnnotation(entityClass = "User")
    public Pagination<Object> pagesTest(int page, int size, int offset) {

        int limit;

        Pagination<Object> userPagination = new Pagination<>();

        Long count = entityManager.createQuery("select count (u.id) from User u", Long.class)
                .getSingleResult();

        int maxPages = (int) Math.ceil(((double) count - (double) offset) / (double) size);

        limit = offset == 0 ? (page) * size : ((page) * size) + offset;

        List<Object> users = entityManager.createQuery("select u from User u order by u.id", Object.class)
                .setFirstResult(limit)
                .setMaxResults(size)
                .getResultList();

        userPagination.setContent(users);
        userPagination.setMaxPages(maxPages);
        userPagination.setCount(count);

        return userPagination;
    }

    @Override
    public User findByUsername(String username) {
        return entityManager.find(User.class, username);
    }

    //Метод реализации пагинации на странице с использованием Pageable
    @Override
    public Page<User> userPage(Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        List<User> users1 = entityManager.createQuery("select u from User u", User.class)
                .setFirstResult((pageNumber) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();

        Long count = entityManager.createQuery("select count (u.id) from User u", Long.class)
                .getSingleResult();

        return new PageImpl<>(users1, pageable, count);
    }

    @Override
    @TestAnnotation(entityClass = "User")
    public Pagination<Object> pagingTestAnnotation(int page, int size, int offset) {
        return null;
    }
}
