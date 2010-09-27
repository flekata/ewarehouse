/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warehouse.methods;

import com.genrep.container.service.application.AppFactory;
import com.genrep.guimodel.gui.comp.listBox.SelectOneListbox;
import com.genrep.guimodel.service.gui.comp.AGUIContainer;
import com.warehouse.beans.WarehouseTableColumns;
import com.warehouse.core.Warehouse;
import com.warehouse.procedure.WarehouseProcedure;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kiril Arsov
 */
public class WarehouseMethods {

    public List getWarehausesFromSql(AGUIContainer container) {
        List<WarehouseTableColumns> list = new ArrayList<WarehouseTableColumns>();
        AGUIContainer cont = (AGUIContainer) container.getContainers().get("Warehouse");
        SelectOneListbox lbox = (SelectOneListbox) cont.getComponents().get("name");
        Warehouse wh = (Warehouse) lbox.getValueObject();
        if (wh != null) {
            String wrname = (String) wh.getName();
            List inputList = null;
            List outputList = null;
            WarehouseProcedure proc = new WarehouseProcedure(AppFactory.getCurrentApplication().getName(), "codexSession");
            list = makeListFromObj(proc.getWarehausesBalance(wrname));
        }
        return list;
    }

    public List getWarehauses(AGUIContainer container) {
        List<WarehouseTableColumns> list = new ArrayList<WarehouseTableColumns>();
        AGUIContainer cont = (AGUIContainer) container.getContainers().get("Warehouse");
//        Warehouse wh = (Warehouse) cont.getContainerBean();
        SelectOneListbox lbox = (SelectOneListbox) cont.getComponents().get("name");
        Warehouse wh = (Warehouse) lbox.getValueObject();
        if (wh != null) {
            String wrname = (String) wh.getName();
            List inputList = null;
            List outputList = null;
            WarehouseProcedure proc = new WarehouseProcedure(AppFactory.getCurrentApplication().getName(), "codexSession");
            inputList = proc.getWarehausesInput(wrname);
            outputList = proc.getWarehausesOutput(wrname);

            list = balance(makeListFromObj(inputList), makeListFromObj(outputList));
        }
        return list;
    }

    public List<WarehouseTableColumns> makeListFromObj(List list) {
        List<WarehouseTableColumns> resultList = new ArrayList<WarehouseTableColumns>();
        for (int i = 0; i < list.size(); i++) {
            boolean add = true;
            WarehouseTableColumns wti = new WarehouseTableColumns();
            Object[] objects = (Object[]) list.get(i);
            if (objects[2] != null) {
                wti.setItemName(((String) objects[2]).toString());
            } else {
                add = false;
            }
            if (objects[0] != null) {
                wti.setSize(((Integer) objects[0]).toString());
            } else {
                add = false;
            }
            if (add) {
                resultList.add(wti);
            }

        }
        return resultList;
    }

    public List<WarehouseTableColumns> balance(List<WarehouseTableColumns> list1, List<WarehouseTableColumns> list2) {
//        List<WarehouseTableColumns> resultList = new ArrayList<WarehouseTableColumns>();
        if (list1 != null && list2 != null
                && !list1.isEmpty() && !list2.isEmpty()) {
            int size2 = list2.size();
            for (int i = 0; i < list1.size(); i++) {
                WarehouseTableColumns ls1 = list1.get(i);
                for (int j = 0; j < size2; j++) {
                    WarehouseTableColumns ls2 = list2.get(j);
                    if (ls1.getItemName().equalsIgnoreCase(ls2.getItemName())) {
                        Integer one = new Integer(ls1.getSize());
                        Integer two = new Integer(ls2.getSize());
                        Integer rez = new Integer(one.intValue() - two.intValue());
                        ls1.setSize(rez.toString());
                        list2.remove(j);
                        size2--;
                    }
                }
            }
        }
        return list1;
    }
}
