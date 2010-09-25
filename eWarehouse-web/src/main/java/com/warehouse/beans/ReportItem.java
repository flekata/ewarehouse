/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warehouse.beans;

import com.genrep.document.ADocument;
import com.warehouse.core.Item;
import com.warehouse.core.Organization;
import com.warehouse.core.Warehouse;

/**
 *
 * @author Kiril Arsov
 */
public class ReportItem extends ADocument {

    public Item item;
    public Warehouse warehouse;
    public String type;
    public Organization organization;
    public Integer size;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
