package com.finance.archives.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.finance.archives.po.Inventory;
import com.finance.archives.service.IInventoryService;
import com.finance.aspect.IInventoryValidator;
import com.finance.aspect.InventoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private IInventoryService inventoryService;

    @RequestMapping("/get")
    @ResponseBody
    public Inventory getInventoryById(String id){
        Inventory inventory = inventoryService.getById(id);
        return inventory;
    }

}
