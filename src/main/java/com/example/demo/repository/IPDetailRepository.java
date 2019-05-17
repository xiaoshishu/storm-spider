package com.example.demo.repository;


import com.example.demo.model.IPDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IPDetailRepository extends MongoRepository<IPDetail,String> {

    //IPDetail getByIP (String ip);
    IPDetail findIPDetailByIp(String ip);



}
