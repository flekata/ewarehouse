/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warehouse.action;

import com.genrep.container.service.application.AppFactory;
import com.genrep.guimodel.action.ARowAction;
import com.genrep.guimodel.gui.comp.table.Table;
import com.genrep.guimodel.service.action.AAction.ActionType;
import com.genrep.guimodel.service.gui.comp.AGUIContainer;
import com.genrep.persistence.service.AEntity;
import com.warehouse.core.Input;
import com.warehouse.core.Output;
import java.util.List;

/**
 *
 * @author Kiril Arsov
 */
public class RowSelector extends ARowAction {

    @Override
    public boolean doExecute() {
        try {
            AGUIContainer container = getContainer();
            AEntity selectedRow = (AEntity) AppFactory.getSessionManagerImpl().
                    getObjectImpl(getRowItem());
            Object[] params = getParams();
            Table table = (Table) params[0];

            if (container.getContainerBean().getClass().equals(Input.class)) {
                Input in = (Input) selectedRow;
                List<Input> tableList = (List<Input>) table.getValueObject();
                in.setTableList(tableList);
                int current = 0;
                for (int i = 0; i < tableList.size(); i++) {
                    Input inptbl = tableList.get(i);
                    if (in.equals(inptbl)) {
                        current = i;
                        break;
                    }
                }
                in.setCurrent(current + 1);
                container.setContainerBean(in);
            } else if (container.getContainerBean().getClass().equals(Output.class)) {
                Output in = (Output) selectedRow;
                List<Output> tableList = (List<Output>) table.getValueObject();
                in.setTableList(tableList);
                int current = 0;
                for (int i = 0; i < tableList.size(); i++) {
                    Output inptbl = tableList.get(i);
                    if (in.equals(inptbl)) {
                        current = i;
                        break;
                    }
                }
                in.setCurrent(current + 1);
                container.setContainerBean(in);
            }


            container.logOperation(selectedRow, "load");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }

    @Override
    public ActionType getType() {
        return ActionType.LOAD;
    }
}
