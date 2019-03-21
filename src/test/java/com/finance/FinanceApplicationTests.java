package com.finance;

import com.finance.archives.mapper.IInventoryMapper;
import com.finance.archives.po.Inventory;
import com.finance.archives.po.SexEmum;
import com.finance.archives.po.User;
import com.finance.archives.service.IInventoryService;
import com.finance.archives.service.IUserService;
import com.finance.interceptor.MyInterceptor;
import com.finance.interceptor.ProxyBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FinanceApplicationTests {

    @Autowired
    private IInventoryService inventoryService;

    @Autowired
    private IInventoryMapper inventoryMapper;

    @Autowired
    private IUserService userService;

    @Test
    public void insertUser(){
//        User user = new User();
//        user.setUsername("大标");
//        user.setSex(SexEmum.FEMALE);
//        user.setNote("老二");
//        userService.insertUser(user);
//        System.out.println(user.toString());
        User user = userService.getUser3(4L);
        user.setSex(SexEmum.MALE);
        userService.updateUser(user);
        List<User> users = userService.findUsers("大标", "老二");
        System.out.println(users.toString());
    }

//    @Test
////    public void testQuery(){
////        List<Inventory> inventoryList = inventoryService.getAll();
////        System.out.println(inventoryList.get(0).toString());
////    }
////
////    @Test
////    public void testQueryById(){
////        Inventory inventory = inventoryService.getById("6bc3a337-9fa8-4fb4-9b20-dc6671b7d496");
////        System.out.println(inventory.toString());
////    }
////
////
////    @Test
////    public void testProxy(){
////
////        IInventoryService proxyBean = (IInventoryService) ProxyBean.getProxyBean(inventoryService,new MyInterceptor());
////        proxyBean.getById("6bc3a337-9fa8-4fb4-9b20-dc6671b7d496");
////        System.out.println("---------------is not null-----------");
////        proxyBean.getById("");
////
////    }

//    @Test
//    public void testSave(){
//        Inventory inventory = new Inventory();
//        inventory.setId(UUID.randomUUID().toString());
//        inventory.setCode("0001");
//        inventory.setName("SpringBoot");
//        inventory.setShortName("SpringBoot");
//        inventoryService.save(inventory);
//        System.out.println(inventory.toString());
//    }


}
