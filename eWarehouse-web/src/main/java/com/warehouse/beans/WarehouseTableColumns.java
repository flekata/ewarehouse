/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warehouse.beans;

import com.genrep.document.ADocument;
import java.util.Date;

/**
 *
 * @author Kiril Arsov
 */
public class WarehouseTableColumns extends ADocument {

    private String itemName;
    private String size;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
