package com.learn.base.reflect.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yeyongjun
 * @since 2024/8/3 16:21
 */
public class DebugInvocationHandler implements InvocationHandler {
    /**
     * 代理类中的真实对象
     */
    private final Object target;

    public DebugInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk proxy before method " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("jdk proxy after method " + method.getName());
        return result;
    }
}
