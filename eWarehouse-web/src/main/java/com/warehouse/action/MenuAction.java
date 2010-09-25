/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warehouse.action;

import com.genrep.container.service.application.AppFactory;
import com.genrep.guimodel.action.AMenuAction;
import com.genrep.guimodel.service.gui.comp.AGUIContainer;

/**
 *
 * @author Kiril Arsov
 */
public class MenuAction extends AMenuAction {

    @Override
    public boolean doExecute() {
        AGUIContainer container = getContainer();
        Menu selectedRow = (Menu) AppFactory.getSessionManagerImpl().
                getObjectImpl(getSelectedValue());
        return true;
    }

    @Override
    public ActionType getType() {
        return ActionType.BOTH;
    }
}
