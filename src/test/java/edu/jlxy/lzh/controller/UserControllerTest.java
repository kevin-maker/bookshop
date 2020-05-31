package edu.jlxy.lzh.controller;

import java.lang.reflect.Method;

public class UserControllerTest {

    public void login(){
        System.out.println("这是login()方法调用了");
    }

    public void regist(){
        System.out.println("这是regist()方法调用了");
    }

    public void update(){
        System.out.println("这是update()方法调用了");
    }

    public void phone(){
        System.out.println("这是phone()方法调用了");
    }

    public static void main(String[] args) {
        String action = "phone";
        try {
            //获取action业务相应字符串，获取相应的业务方法反射对象
            Method method = UserControllerTest.class.getMethod(action);
//            System.out.println(method);
            //调用目标业务方法
            method.invoke(new UserControllerTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
