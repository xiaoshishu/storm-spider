package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Company {

    @Id
    private String id;

    private String companyName;

    private String name;

    private String mobile;

    private String iphone;

}
