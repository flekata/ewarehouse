/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warehouse.action;

import com.genrep.guimodel.service.action.AAction;
import com.genrep.guimodel.service.gui.comp.AGUIContainer;
import com.warehouse.core.Input;

/**
 *
 * @author Kiril Arsov
 */
public class Edit extends AAction {

    @Override
    public boolean doExecute() {
//        AGUIContainer container = getContainer().getContainer();
//        AGUIContainer inputContainer = (AGUIContainer) container.getContainers().get("Input");
//        AGUIContainer inputViewContainer = (AGUIContainer) container.getContainers().get("InputView");
//        Content content = (Content) container.getContainerBean();
//
//        Input input = (Input) inputViewContainer.getContainerBean();
//        inputContainer.setContainerBean(input);
//        inputViewContainer.clearContainerBean();
//        inputViewContainer.clearContainer();
//        inputViewContainer.clear();
//        content.setCurrentDisplay("warehouse/Input.jspx");
        return true;
    }

    @Override
    public ActionType getType() {
        return ActionType.BOTH;
    }
}
