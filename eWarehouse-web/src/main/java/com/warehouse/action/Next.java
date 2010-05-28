/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warehouse.action;

import com.genrep.guimodel.service.action.AAction;
import com.genrep.guimodel.service.gui.comp.AGUIContainer;
import com.warehouse.core.Input;
import com.warehouse.core.Output;
import java.util.List;

/**
 *
 * @author Kiril Arsov
 */
public class Next extends AAction {

    @Override
    public boolean doExecute() {
        AGUIContainer container = getContainer();
        if (container.getContainerBean().getClass().equals(Input.class)) {
            Input in = (Input) getContainer().getContainerBean();
            List list = in.getTableList();
            Integer curr = in.getCurrent();
            Input inNext = null;

            if (list.size() > curr.intValue()) {
                inNext = (Input) list.get(curr.intValue());
                inNext.setCurrent(curr.intValue() + 1);
            } else {
                inNext = (Input) list.get(0);
                inNext.setCurrent(1);
            }
            inNext.setTableList(list);
            getContainer().setContainerBean(inNext);
        } else if (container.getContainerBean().getClass().equals(Output.class)) {
            Output in = (Output) getContainer().getContainerBean();
            List list = in.getTableList();
            Integer curr = in.getCurrent();
            Output inNext = null;

            if (list.size() > curr.intValue()) {
                inNext = (Output) list.get(curr.intValue());
                inNext.setCurrent(curr.intValue() + 1);
            } else {
                inNext = (Output) list.get(0);
                inNext.setCurrent(1);
            }
            inNext.setTableList(list);
            getContainer().setContainerBean(inNext);
        }

        return true;
    }

    @Override
    public ActionType getType() {
        return ActionType.LOAD;
    }
}
