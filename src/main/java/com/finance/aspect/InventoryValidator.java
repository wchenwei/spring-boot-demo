package com.finance.aspect;

import com.finance.archives.po.Inventory;
import org.springframework.stereotype.Component;


@Component
public class InventoryValidator implements IInventoryValidator {
    @Override
    public boolean validate(Inventory inventory) {
        System.out.println("引入方法"+InventoryValidator.class.getSimpleName());
        return inventory != null;
    }
}
