/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warehouse.action;

import com.genrep.container.service.application.AppFactory;
import com.genrep.guimodel.gui.action.ModeTransitionManager;
import com.genrep.guimodel.gui.comp.table.Table;
import com.genrep.guimodel.service.action.AAction;
import com.genrep.guimodel.service.gui.comp.AGUIContainer;
import com.genrep.operationset.service.exception.OperationSetException;
import com.genrep.operationset.service.procedure.ISaveable;
import com.warehouse.core.Input;
import com.warehouse.core.Output;
import com.warehouse.procedure.WarehouseProcedure;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kiril Arsov
 */
public class Delete extends AAction {

    @Override
    public boolean doExecute() {
        AGUIContainer container = (AGUIContainer) getContainer();
        Object[] params = getParams();
        Table table = (Table) params[0];
        WarehouseProcedure proc = new WarehouseProcedure(AppFactory.getCurrentApplication().getName(), "codexSession");
        if (container.getContainerBean().getClass().equals(Input.class)) {
            Input in = (Input) getContainer().getContainerBean();
            List list = in.getTableList();
            Integer curr = in.getCurrent();

            int listSize = list.size();
            if (listSize > 1) {
                if (curr.intValue() < listSize) {
                    Input inNew = (Input) list.get(curr);
                    inNew.setCurrent(curr.intValue());

                    ISaveable saveable = (ISaveable) in;
                    saveable.setCommitTransaction(true);
                    try {
                        proc.deleteObject(in, false);
                    } catch (OperationSetException ex) {
                        Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    list.remove(in);
                    inNew.setTableList(list);
                    container.setContainerBean(inNew);
                    table.setValue(list);


                } else {
                    Input inNew = (Input) list.get(0);
                    inNew.setCurrent(1);

                    ISaveable saveable = (ISaveable) in;
                    saveable.setCommitTransaction(true);
                    try {
                        proc.deleteObject(in, false);
                    } catch (OperationSetException ex) {
                        Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    list.remove(in);
                    inNew.setTableList(list);
                    container.setContainerBean(inNew);
                    table.setValue(list);


                }
            } else if (listSize == 1) {
                Input ot = new Input();

                ISaveable saveable = (ISaveable) in;
                saveable.setCommitTransaction(true);
                try {
                    proc.deleteObject(in, false);
                } catch (OperationSetException ex) {
                    Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
                }
                ModeTransitionManager modeManager = new ModeTransitionManager(container);
                list.remove(in);
                ot.setCurrent(0);
                ot.setTableListSize(0);
                container.setContainerBean(ot);
                table.setValue(list);
                boolean result = modeManager.handleModeTransition("new");
                return false;

            }

        } else if (container.getContainerBean().getClass().equals(Output.class)) {
            Output in = (Output) getContainer().getContainerBean();
            List list = in.getTableList();
            Integer curr = in.getCurrent();

            int listSize = list.size();
            if (listSize > 1) {
                if (curr.intValue() < listSize) {
                    Output inNew = (Output) list.get(curr);
                    inNew.setCurrent(curr.intValue());

                    ISaveable saveable = (ISaveable) in;
                    saveable.setCommitTransaction(true);
                    try {
                        proc.deleteObject(in, false);
                    } catch (OperationSetException ex) {
                        Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    list.remove(in);
                    inNew.setTableList(list);
                    container.setContainerBean(inNew);
                    table.setValue(list);


                } else {
                    Output inNew = (Output) list.get(0);
                    inNew.setCurrent(1);

                    ISaveable saveable = (ISaveable) in;
                    saveable.setCommitTransaction(true);
                    try {
                        proc.deleteObject(in, false);
                    } catch (OperationSetException ex) {
                        Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    list.remove(in);
                    inNew.setTableList(list);
                    container.setContainerBean(inNew);
                    table.setValue(list);


                }
            } else if (listSize == 1) {
                Output ot = new Output();

                ISaveable saveable = (ISaveable) in;
                saveable.setCommitTransaction(true);
                try {
                    proc.deleteObject(in, false);
                } catch (OperationSetException ex) {
                    Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
                }
                ModeTransitionManager modeManager = new ModeTransitionManager(container);
                list.remove(in);
                ot.setCurrent(0);
                ot.setTableListSize(0);
                container.setContainerBean(ot);
                table.setValue(list);
                boolean result = modeManager.handleModeTransition("new");
                return false;
            }
        }

        return true;
    }

    @Override
    public ActionType getType() {
        return ActionType.BOTH;
    }
}
