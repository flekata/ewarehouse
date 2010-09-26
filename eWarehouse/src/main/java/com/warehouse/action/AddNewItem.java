/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warehouse.action;

import com.genrep.container.service.application.AppFactory;
import com.genrep.guimodel.gui.comp.combo.Combo;
import com.genrep.guimodel.gui.comp.text.Text;
import com.genrep.guimodel.service.action.AAction;
import com.genrep.operationset.service.procedure.ISaveable;
import com.warehouse.core.Input;
import com.warehouse.core.Item;
import com.warehouse.core.Output;
import com.warehouse.procedure.WarehouseProcedure;

/**
 *
 * @author Kiril Arsov
 */
public class AddNewItem extends AAction {

    @Override
    public boolean doExecute() {
        if (getContainer().getContainerBean().getClass().equals(Input.class)) {
            Input in = (Input) getContainer().getContainerBean();
            Object[] params = getParams();
            Text text = (Text) params[0];
            Combo combo = (Combo) params[1];

            String newItemName = (String) text.getValueObject();
            Item newItem = new Item();
            newItem.setName(newItemName);
            WarehouseProcedure proc = new WarehouseProcedure(AppFactory.getCurrentApplication().getName(), "codexSession");
            ISaveable saveable = (ISaveable) newItem;
            saveable.setCommitTransaction(true);
            newItem.save(proc);

            in.setItem(newItem);
            text.setValueObject(null);
            combo.init();
        } else if (getContainer().getContainerBean().getClass().equals(Output.class)) {
            Output out = (Output) getContainer().getContainerBean();
            Object[] params = getParams();
            Text text = (Text) params[0];
            Combo combo = (Combo) params[1];

            String newItemName = (String) text.getValueObject();
            Item newItem = new Item();
            newItem.setName(newItemName);
            WarehouseProcedure proc = new WarehouseProcedure(AppFactory.getCurrentApplication().getName(), "codexSession");
            ISaveable saveable = (ISaveable) newItem;
            saveable.setCommitTransaction(true);
            newItem.save(proc);

            out.setItem(newItem);
            text.setValueObject(null);
            combo.init();
        }

        return true;
    }

    @Override
    public ActionType getType() {
        return ActionType.BOTH;
    }
}
