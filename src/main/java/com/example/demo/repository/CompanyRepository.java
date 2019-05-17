package com.example.demo.repository;

import com.example.demo.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company,String> {

    Company findByName(String name);

}
