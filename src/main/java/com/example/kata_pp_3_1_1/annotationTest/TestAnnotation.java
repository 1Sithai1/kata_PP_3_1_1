package com.example.kata_pp_3_1_1.annotationTest;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
    String entityClass();
}
