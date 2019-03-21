package com.finance.archives.mapper;

import com.finance.archives.po.Inventory;

import java.util.List;

public interface IInventoryMapper {

    List<Inventory> getAll();

    Inventory getById(String id);

    void save(Inventory inventory);

    void update(Inventory upate);

    void delete(String id);

}
