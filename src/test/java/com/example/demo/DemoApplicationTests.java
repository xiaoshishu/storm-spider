package com.example.demo;

import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class DemoApplicationTests {

    @Autowired
    private UserRepository repository;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void contextLoads() {
        try{
            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            //时间转为字符串
            LocalDateTime date =LocalDateTime.now();
            String str = date.format(f);  // 2014-11-07 14:10:36
            System.out.println(str);
            //字符串转为时间
            String str2 = "2019-01-01 12:12:12";
            date = LocalDateTime.parse(str2,f);
            System.out.println(date);
        } catch (Exception e){

        }

        /*User user = new User(11,"22",23);

        repository.save(user);

        Company company = new Company();
        company.setCompanyName("1");
        company.setName("1");
        company.setMobile("1");
        company.setIphone("1");
        companyRepository.save(company);*/

    }

}
