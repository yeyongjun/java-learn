package com.learn.base.reflect.proxy;

import com.learn.base.reflect.proxy.cglib.DebugMethodInterceptor;
import com.learn.base.reflect.proxy.jdk.DebugInvocationHandler;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * @author yeyongjun
 * @since 2024/8/3 16:25
 */
public class ProxyFactory {
    public static Object getJdkProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), // 目标类的类加载器
                target.getClass().getInterfaces(),  // 代理需要实现的接口，可指定多个
                new DebugInvocationHandler(target)   // 代理对象对应的自定义 InvocationHandler
        );
    }

    public static <T> T getCglibProxy(Class<T> clazz) {
        // 创建动态代理增强类
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        // 设置被代理类
        enhancer.setSuperclass(clazz);
        // 设置方法拦截器
        enhancer.setCallback(new DebugMethodInterceptor());
        // 创建代理类
        return clazz.cast(enhancer.create());
    }
}
