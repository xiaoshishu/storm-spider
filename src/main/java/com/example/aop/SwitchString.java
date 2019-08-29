package com.example.aop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwitchString {


    public static void main(String[] args){
        method(null);
    }
    // 会报NullPointerException
    public static void method(String param){
        Map<String,String> hashMap = new HashMap();
        hashMap.put("","");
        switch(param){
            // 肯定不是进入这里
            case"sth":
                System.out.println("it's sth");
                break;
            // 也不是进入这里
            case"null":
                System.out.println("it's null");
                break;
            // 也不是进入这里
            default:
                System.out.println("default");
        }
    }




}
