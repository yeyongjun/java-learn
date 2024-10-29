package com.learn.base.reflect.proxy;

/**
 * @author yeyongjun
 * @since 2024/8/3 16:26
 */
public class ProxyTest {

    public static void main(String[] args) {
        long currentTime = System.currentTimeMillis();
        IProxyAction proxyAction = (IProxyAction) ProxyFactory.getJdkProxy(new ProxyAction());
        proxyAction.doSth("123");

        long tagTime = System.currentTimeMillis();
        System.out.println(tagTime - currentTime);

        proxyAction = ProxyFactory.getCglibProxy(ProxyAction.class);
        proxyAction.doSth("1234");
        System.out.println(System.currentTimeMillis() - currentTime);
    }

}
