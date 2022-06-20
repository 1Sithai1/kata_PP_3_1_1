package com.example.kata_pp_3_1_1.annotationTest;

import com.example.kata_pp_3_1_1.Dto.Pagination;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Aspect
@Component
public class TestAnnotationImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Pointcut(value = "@annotation(com.example.kata_pp_3_1_1.annotationTest.TestAnnotation)")
    private void pointcut() {
    }

    @Around(value = "pointcut() && @annotation(testA)")
    public Object paging(ProceedingJoinPoint point, TestAnnotation testA) {

        System.out.println("Выполняется метод paging()");

        String entity = testA.entityClass();

        Object[] args = point.getArgs();

        int page = Math.max((int) args[0], 0);
        int size = Math.max((int) args[1], 0);
        int offset = Math.max((int) args[2], 0);

        int limit;

        Pagination<Object> userPagination = new Pagination<>();

        Long count = entityManager.createQuery("select count (u.id) from " + entity + " u", Long.class)
                .getSingleResult();

        int maxPages = (int) Math.ceil(((double) count - (double) offset) / (double) size);

        limit = offset == 0 ? (page) * size : (page * size) + offset;

        List<Object> users = entityManager.createQuery("select u from " + entity + " u order by u.id", Object.class)
                .setFirstResult(limit)
                .setMaxResults(size)
                .getResultList();

        userPagination.setCount(count);
        userPagination.setMaxPages(maxPages);
        userPagination.setContent(users);

//        try {
//            point.proceed(); //Этот метод позволяет запускать метод afterReturning(), если нужен функционал после return
//            return userPagination;
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }

        return userPagination;
    }

    @AfterReturning(value = "pointcut() && @annotation(testA)", returning = "result")
    public Object afterReturning(JoinPoint joinPoint, TestAnnotation testA, Object result) {
        System.out.println("Выполняется метод AfterReturning");
        return null;
    }

    @AfterThrowing(value = "pointcut() && @annotation(testAnnotation)", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, TestAnnotation testAnnotation, Exception ex) {
        System.out.println("Выполнен метод afterThrowing");
        System.out.println("Запрос:" + testAnnotation.entityClass() + "Произошло исключение");
    }

}
