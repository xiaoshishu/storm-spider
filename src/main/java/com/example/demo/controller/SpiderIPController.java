package com.example.demo.controller;

import com.example.demo.repository.UserRepository;
import com.example.demo.service.SpiderIPService;
import com.example.demo.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import us.codecraft.webmagic.Spider;


@Controller
public class SpiderIPController implements CommandLineRunner {

    @Autowired
    private SpiderIPService spiderIPService;


    @Autowired
    private UserRepository repository;

    @Override
    public void run(String... args) throws Exception {

        /*while (true){
            System.out.println("开始抓取");
            Spider.create(spiderService).addUrl("http://www.cn716.com/company").thread(1).run();
        }*/
        /*System.out.println("开始抓取");
        Spider.create(spiderIPService).addUrl("http://www.89ip.cn/").thread(5).run();*/

    }

}
