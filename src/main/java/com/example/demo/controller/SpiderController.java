package com.example.demo.controller;

import com.example.demo.config.DownLoader;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;


@Controller
public class SpiderController implements CommandLineRunner {

    @Autowired
    private SpiderService spiderService;


    @Autowired
    private UserRepository repository;

    @Override
    public void run(String... args) throws Exception {

        /*while (true){
            System.out.println("开始抓取");  163.204.247.2  9999   103.205.14.1	8080
            Spider.create(spiderService).addUrl("http://www.cn716.com/company").thread(1).run();
        }*/
        System.out.println("开始抓取");
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(
                SimpleProxyProvider.from(
                        new Proxy("115.226.226.212", 8883),
                        new Proxy("114.217.98.26", 8883)));
        Spider.create(spiderService).addUrl("http://www.cn716.com/company").thread(15).run();

    }

}
