package com.finance.interceptor;

import java.lang.reflect.InvocationTargetException;

/**
 * 切面 拦截器
 */
public class MyInterceptor implements Interceptor{
    @Override
    public boolean before() {
        System.out.println("before .....");
        return true;
    }

    @Override
    public void after() {
        System.out.println("after .....");
    }

    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("arount before ......");
        Object obj = invocation.proceed();
        System.out.println("arount after ......");
        return obj;
    }

    @Override
    public void afterReturning() {
        System.out.println("afterReturning ......");
    }

    @Override
    public void afterThrowing() {
        System.out.println("afterThrowing ......");
    }

    @Override
    public boolean useAround() {
        return true;
    }
}
