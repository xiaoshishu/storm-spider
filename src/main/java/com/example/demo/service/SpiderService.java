package com.example.demo.service;

import com.example.demo.model.Company;
import com.example.demo.model.IPDetail;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.IPDetailRepository;
import com.sun.org.apache.xml.internal.serializer.Encodings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SpiderService implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(2000).setTimeOut(10000);

    private String baseUrl = "http://www.cn716.com/";

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private IPDetailRepository ipDetailRepository;


    @Override
    public void process(Page page) {
        // 获取原始页面
        String referer = page.getUrl().get();
        Document document = Jsoup.parse(page.getRawText());
        try{
            if (document.toString().contains("连续刷新本页面")) {
                List<IPDetail> list = ipDetailRepository.findAll();
                list.size();
                int a = (int)(1+Math.random()*(list.size()-1+1));
                IPDetail ipDetail = list.get(a);
                String ip = ipDetail.getIp();
                String port = ipDetail.getPort();
                String url = page.getUrl().get();
                System.getProperties().setProperty("http.proxyHost", ip);
                System.getProperties().setProperty("http.proxyPort", port);
                URL realUrl = new URL(url);
                URLConnection conn = realUrl.openConnection();
                // 设置通用的请求属性
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("user-agent", "Mozilla/5.0 (Linux; U; Android 4.2.2; zh-cn; SCH-P709 Build/JDQ39) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (referer.equals("http://www.cn716.com/company")) {
            List<String>  list = page.getHtml().xpath("/html/body/div[@class='class2_content2']/div[@class='class2_2']/div[@style='clear:both;margin:8px auto;']/div[@class='city']/a/@href").all();
            List<String>  stringList = new ArrayList<>();
            for (String str : list) {
                String href = str.replace("../","").trim();
                stringList.add(href);
            }
            page.addTargetRequests(stringList);

        } else if (page.getUrl().regex("http://www.cn716.com/company[0-9]*-[0-9]*").match()) {

            page.addTargetRequests(
                    page.getHtml().xpath("/html/body/div[@class='class2_content']/div[@class='class2_content2']/div[@class='ul_right']/" +
                            "div/ul[@class='sell_new']/li/a/@href").all());


            page.addTargetRequests(
                    page.getHtml().xpath("/html/body/div[@class='class2_content']/div[@class='class2_content2']/div[@class='ul_right']/" +
                            "div/div[@class='sell_1_b1_page']/span[3]/a/@href").all());

        } else if (page.getUrl().regex("http://www.cn716.com/sellmarket/[a-z 0-9]*").match()) {
            Elements elements3 = document.getElementsByClass("codebuy");
            for (Element element : elements3) {
                Element table = element.child(0);
                Elements trs = table.select("tr");
                Company company = new Company();
                for (int i = 0; i < trs.size(); i ++) {
                    if (i == 1 || i == 7 || i == 9 || i ==11) {
                        Element tr = trs.get(i);
                        Elements tds = tr.select("td");
                        Element td = tds.get(tds.size()-1);
                        if (i == 1) {
                            company.setCompanyName(td.text());
                        } else if (i == 7) {
                            company.setName(td.text());
                        } else if (i == 9) {
                            company.setMobile(td.text());
                        } else if (i == 11) {
                            company.setIphone(td.text());
                        }
                    }
                }
                companyRepository.save(company);
            }
        }


        /*if (referer.equals("http://www.cn716.com/company")) {

            *//*page.addTargetRequests(
                    page.getHtml().xpath("/html/body/div[@class='class2_content2']/div[@class='class2_1']/span[@class='class2_1x']/a/@href").all());*//*



        } else if (page.getUrl().regex("http://www.cn716.com/company[0-9]+").match() && !referer.contains("-")) {

            page.addTargetRequests(
                    page.getHtml().xpath("/html/body/div[@class='class2_content']/div[@class='class2_content2']/div[@class='ul_left']/ul/li[@class='class2_1']/span[@class='class2_1x']/a/@href").all());

        } else if (page.getUrl().regex("http://www.cn716.com/company[0-9]*-[0-9]*").match()) {
            *//*Elements elements2 = document.getElementsByClass("sell_new");
            for (Element element : elements2) {
                String attr = element.child(0).child(0).attr("href");
                String url = baseUrl + attr;
                page.addTargetRequest(url);

            }*//*
            ///html/body/div[6]/div[1]/div[2]/ul/li[1]/span[1]/a


            //  /html/body/div[6]/div[1]/div[1]/div/ul[1]/li/a

            page.addTargetRequests(
                    page.getHtml().xpath("/html/body/div[@class='class2_content']/div[@class='class2_content2']/div[@class='ul_right']/" +
                            "div/ul[@class='sell_new']/li/a/@href").all());


            *//*Elements elementsForNext = document.getElementsByClass("read");
            for (Element element : elementsForNext) {
                if (element.toString().contains("下一页")) {
                    String attr = element.child(0).attr("href");
                    String url = attr.replace("../","");
                    page.addTargetRequest(baseUrl + url);
                }
            }*//*
            page.addTargetRequests(
                    page.getHtml().xpath("/html/body/div[@class='class2_content']/div[@class='class2_content2']/div[1]/div/div[1]/span[3]/a/@href").all());

        } else if (page.getUrl().regex("http://www.cn716.com/sellmarket/[a-z 0-9]*").match()) {
            Elements elements3 = document.getElementsByClass("codebuy");
            for (Element element : elements3) {
                Element table = element.child(0);
                Elements trs = table.select("tr");
                Company company = new Company();
                for (int i = 0; i < trs.size(); i ++) {
                    if (i == 1 || i == 7 || i == 9 || i ==11) {
                        Element tr = trs.get(i);
                        Elements tds = tr.select("td");
                        Element td = tds.get(tds.size()-1);
                        if (i == 1) {
                            company.setCompanyName(td.text());
                        } else if (i == 7) {
                            company.setName(td.text());
                        } else if (i == 9) {
                            company.setMobile(td.text());
                        } else if (i == 11) {
                            company.setIphone(td.text());
                        }
                    }
                }
                companyRepository.save(company);
            }
        }*/
    }

    @Override
    public Site getSite() {
        site.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.75 Safari/537.36");
        site.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        site.addHeader("Accept-Encoding", "gzip, deflate");
        site.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
        site.addHeader("Referer", baseUrl);
        site.addHeader("Upgrade-Insecure-Requests", "1");
        site.setCharset("gb2312");
        return site;
    }
}
