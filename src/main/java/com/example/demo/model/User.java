package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@ToString
public class User {
    @Id
    private Integer id;
    private String name;
    private Integer age;

}
