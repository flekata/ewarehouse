/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warehouse.core;

import com.genrep.persistence.service.AEntity;

/**
 *
 * @author Kiril Arsov
 */
public class Menu extends AEntity {

    private String groupField;
    private String itemField;

    public String getGroupField() {
        return groupField;
    }

    public void setGroupField(String groupField) {
        this.groupField = groupField;
    }

    public String getItemField() {
        return itemField;
    }

    public void setItemField(String itemField) {
        this.itemField = itemField;
    }
}
