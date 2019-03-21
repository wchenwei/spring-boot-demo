package com.finance.archives.service;

import com.finance.archives.po.Inventory;

import java.util.List;

public interface IInventoryService {

    List<Inventory> getAll();

    Inventory getById(String id);

    void save(Inventory inventory);

    void update(Inventory inventory);

    void delete(String id);
}
