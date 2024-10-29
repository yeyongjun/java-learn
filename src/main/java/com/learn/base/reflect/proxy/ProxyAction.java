package com.learn.base.reflect.proxy;

/**
 * @author yeyongjun
 * @since 2024/8/3 16:15
 */
public class ProxyAction implements IProxyAction{

    @Override
    public void doSth(String param) {
        System.out.println("doing sth: " + param);
    }

    public void execute() {
        System.out.println("test changed method name");
    }

    public void changeMethodContent() {
        System.out.println("test changed method");
    }
}
