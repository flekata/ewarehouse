/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warehouse.beans;

import com.genrep.document.ADocument;
import com.genrepsoft.facelets.FacesUtils;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Kiril Arsov
 */
public class Content extends ADocument {

    private String currentDisplay = "warehouse/welcome.jspx";
    private String actionFired;
    // records the param value for the menu item which fired the event
    private String param;

    public String getCurrentDisplay() {
        return currentDisplay;
    }

    public void setCurrentDisplay(String currentDisplay) {
        this.currentDisplay = currentDisplay;
    }

    public void primaryListener(ActionEvent e) {

        actionFired = ((UIComponent) e.getSource()).getClientId(FacesContext.getCurrentInstance());
        param = FacesUtils.getRequestParameter("myParam");
        if (!param.equalsIgnoreCase("")) {
            this.currentDisplay = param;
        }

        // highlight server side backing bean values. 
//        valueChangeEffect.setFired(false);

    }
}
