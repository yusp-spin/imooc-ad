package com.imooc.ad.debugTest;

import com.imooc.ad.entity.AdUser;

public class test {
    public static void main(String[] args) {
        addMethod method = new addMethod();
        int a = 10;
        int b = 20;
        System.out.println(method.add(a,b));
        System.out.println(method.sub(a,b));
        method.xunhuan(6);
        AdUser user = new AdUser(){{
            setId(1L);
            setUserName("xiaoyu");
        }};
        login(user);
    }

    private static void login(AdUser user) {
        String name = user.getUserName();
        switch (name){
            case "xiaofang":
                System.out.println("xiaofang");
            case "xiaoyu":
                System.out.println("xiaoyu");
            case "xiaoliu":
                System.out.println("xiaoliu");
        }
    }
}
