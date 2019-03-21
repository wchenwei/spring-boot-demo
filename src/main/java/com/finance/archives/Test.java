package com.finance.archives;


import com.finance.FinanceApplication;
import com.finance.archives.po.Inventory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(FinanceApplication.class);
        Inventory inventory = context.getBean(Inventory.class);
        System.out.println(inventory.toString());
    }
}
