/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warehouse.core;

import com.genrep.document.ADocument;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kiril Arsov
 */
public class Output extends ADocument {

    private Warehouse warehouse;
    private Date date= new Date();
    private Individual individual;
    private String orderCode;
    private String note;
    private Item item;
    private Integer size;
    /// view fields
    List<Output> tableList;
    Integer current;
    Integer tableListSize;

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

    public List<Output> getTableList() {
        return tableList;
    }

    public void setTableList(List<Output> tableList) {
        this.tableList = tableList;
    }

    public Integer getSize() {
        return size;
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

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
