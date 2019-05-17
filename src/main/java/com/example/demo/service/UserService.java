package com.example.demo.service;

import com.example.demo.model.Company;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void save(User user);

    User findByName(String name);


}
