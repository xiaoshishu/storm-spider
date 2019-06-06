package com.example.demo.config;

import com.example.demo.model.IPDetail;
import com.example.demo.repository.IPDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.util.List;

@Component
public class DownLoader {

    @Autowired
    private static IPDetailRepository ipDetailRepository;

    public static HttpClientDownloader newIpDownloader(){
        HttpClientDownloader downloader = new HttpClientDownloader(){
            @Override
            protected void onError(Request request) {
                List<IPDetail> list = ipDetailRepository.findAll();
                list.size();
                int a = (int)(1+Math.random()*(list.size()-1+1));
                IPDetail ipDetail = list.get(a);
                String ip = ipDetail.getIp();
                String port = ipDetail.getPort();
                setProxyProvider(SimpleProxyProvider.from(new Proxy(ip,Integer.parseInt(port))));
            }
        };
        return downloader;
    }



}
