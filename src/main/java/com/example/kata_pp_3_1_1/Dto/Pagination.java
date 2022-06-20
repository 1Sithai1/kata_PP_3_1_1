package com.example.kata_pp_3_1_1.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination <Object> {

    private  List<Object> content;
    private Long count;
    private int maxPages;

}
