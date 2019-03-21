package com.finance.archives.service.Impl;

import com.finance.archives.mapper.IInventoryMapper;
import com.finance.archives.po.Inventory;
import com.finance.archives.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("inventoryService")
public class InventoryServiceImpl implements IInventoryService {

    @Autowired
    private IInventoryMapper inventoryMapper;

    @Override
    public List<Inventory> getAll() {
        return inventoryMapper.getAll();
    }

    @Override
    public Inventory getById(String id) {
        Inventory inventory = inventoryMapper.getById(id);
        if (inventory==null){
            throw new RuntimeException("存货不存在");
        }
        System.out.println(inventory.getId());
        return inventory;
    }

    @Override
    public void save(Inventory inventory) {
        inventoryMapper.save(inventory);
    }

    @Override
    public void update(Inventory inventory) {
        inventoryMapper.update(inventory);
    }

    @Override
    public void delete(String id) {
        inventoryMapper.delete(id);
    }
}
