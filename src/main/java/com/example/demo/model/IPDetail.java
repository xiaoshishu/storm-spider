package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class IPDetail {

    @Id
    private String id;

    private String ip;

    private String port;

}
