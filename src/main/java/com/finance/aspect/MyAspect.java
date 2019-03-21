package com.finance.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 切面
 */
@Aspect
public class MyAspect {
    /**
     * 切点
     */
    @Pointcut("execution(* com.finance.archives.service.Impl.InventoryServiceImpl.getById(..))")
    public void pointCut(){

    }

    /**
     * 前置通知
     */
    @Before("pointCut()")
    public void before(){
        System.out.println("before....");
    }

    /**
     * 后置通知
     */
    @After("pointCut()")
    public void after(){
        System.out.println("after....");
    }

    /**
     * 后置返回通知
     */
    @AfterReturning("pointCut()")
    public void afterReturning(){
        System.out.println("afterReturning.....");
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("afterThrowing.....");
    }

    /**
     * 环绕通知（异常强大 谨慎使用）
     * @param joinPoint
     * @throws Throwable
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before.....");
        Object o =joinPoint.proceed();
        System.out.println("around after.....");
        return o;
    }

}
