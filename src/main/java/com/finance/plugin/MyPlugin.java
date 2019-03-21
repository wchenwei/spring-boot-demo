package com.finance.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

//定义拦截签名
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class,Integer.class})})
public class MyPlugin implements Interceptor {

    Properties properties = null;

    // 拦截方法逻辑
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        System.out.println(target);
        System.out.println(invocation.getMethod());
        System.out.println("插件拦截方法。。。");
        return invocation.proceed();
    }
    //生成mybatis拦截器对象
    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o,this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
        System.out.println(properties.stringPropertyNames());
    }
}
