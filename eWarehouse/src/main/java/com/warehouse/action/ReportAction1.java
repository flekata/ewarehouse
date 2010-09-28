/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warehouse.action;

import com.genrep.guimodel.gui.comp.combo.Combo;
import com.genrep.guimodel.gui.comp.date.DateComp;
import com.warehouse.core.Warehouse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
public class ReportAction1 extends JasperPrintAction {

    @Override
    public String getInputFilePath() {
        return "rep2.jrxml";
    }

    @Override
    public ActionType getType() {
        return ActionType.NONE;
    }

    @Override
    public Map prepareParams() {
        Map m = new HashMap();
        Object[] params = getParams();
        DateComp datod = (DateComp) params[0];
        Combo  datdo = (Combo) params[1];
        Date datod1 = (Date) datod.getValueObject();
        if (datod1==null) {
            datod1 = new Date();
        }
        Warehouse datdo1 = (Warehouse) datdo.getValueObject();
        m.put("DATE", datod1);
        m.put("WAR", datdo1.getName());
        return m;
    }
}
