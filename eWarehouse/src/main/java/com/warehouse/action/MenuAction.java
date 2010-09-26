/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warehouse.action;

import com.genrep.container.service.application.AppFactory;
import com.genrep.guimodel.action.AMenuAction;
import com.genrep.guimodel.service.gui.comp.AGUIContainer;
import com.warehouse.beans.Content;
import com.warehouse.core.Menu;

/**
 *
 * @author Kiril Arsov
 */
public class MenuAction extends AMenuAction {

    @Override
    public boolean doExecute() {
        AGUIContainer container = getContainer();
        Content content = (Content) container.getContainerBean();
        Menu selectedRow = (Menu) AppFactory.getSessionManagerImpl().
                getObjectImpl(getSelectedValue());
        if (selectedRow.getItemField().equalsIgnoreCase("newInput")) {
            content.setCurrentDisplay("warehouse/Input.jspx");
        }
        return true;
    }

    @Override
    public ActionType getType() {
        return ActionType.BOTH;
    }
}
