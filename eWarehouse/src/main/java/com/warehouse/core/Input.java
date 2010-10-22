/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warehouse.core;

import com.genrep.document.ADocument;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kiril Arsov
 */
public class Input extends ADocument {

    private Warehouse warehouse;
    private Date date = new Date();
    private Organization organization;
    private String type;
    private BigDecimal price;
    private Item item;
    private Integer size;
    private String note;
    /// view fields
    List<Input> tableList;
    Integer current;
    Integer tableListSize;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getTableListSize() {
        if (tableList != null) {
            tableListSize = tableList.size();
        }
        return tableListSize;
    }

    public void setTableListSize(Integer tableListSize) {
        if (tableList != null) {
            tableListSize = tableList.size();
        }
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public List<Input> getTableList() {
        return tableList;
    }

    public void setTableList(List<Input> tableList) {
        this.tableList = tableList;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
