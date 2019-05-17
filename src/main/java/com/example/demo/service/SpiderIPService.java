package com.example.demo.service;


import com.example.demo.model.IPDetail;
import com.example.demo.repository.IPDetailRepository;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Random;

@Service
public class SpiderIPService implements PageProcessor {


    private Site site = Site.me().setRetryTimes(3).setSleepTime(2000).setTimeOut(10000).setCharset("UTF-8");

    @Autowired
    private IPDetailRepository ipDetailRepository;

    @Override
    public void process(Page page) {
        try{
            Document document = Jsoup.parse(page.getRawText());

            if (page.getUrl().regex("http://www.89ip.cn/").match()) {


                Elements elements = document.getElementsByClass("layui-table");
                for (Element element : elements) {
                    Elements elementForTd = element.child(1).select("tr");
                    for(int i = 0; i< elementForTd.size(); i++){
                        IPDetail ipDetail = new IPDetail();
                        Element ip = elementForTd.select("td").get(0);
                        ipDetail.setIp(ip.text());
                        Element port = elementForTd.select("td").get(1);
                        ipDetail.setPort(port.text());
                        if (StringUtils.isNotBlank(ipDetail.getIp())) {
                            ipDetailRepository.save(ipDetail);
                        }
                    }
                }

                page.addTargetRequests(page.getHtml().xpath("//*[@id=\"layui-laypage-1\"]/a[@class='layui-laypage-next']/@href").all());
            }
        } catch (Exception e){

        }

    }

    @Override
    public Site getSite() {
        site.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.75 Safari/537.36");
        site.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        site.addHeader("Accept-Encoding", "gzip, deflate");
        site.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
        site.addHeader("Upgrade-Insecure-Requests", "1");
        site.setCharset("UTF-8");
        return site;
    }
}
