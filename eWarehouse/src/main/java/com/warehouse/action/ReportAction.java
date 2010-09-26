/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warehouse.action;

import com.genrep.guimodel.gui.comp.date.DateComp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
public class ReportAction extends JasperPrintAction {

    @Override
    public String getInputFilePath() {
        return "rep1.jrxml";
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
        DateComp datdo = (DateComp) params[1];
        Date datod1 = (Date) datod.getValueObject();
        Date datdo1 = (Date) datdo.getValueObject();
        m.put("OD", datod1);
        m.put("DO", datdo1);
        return m;
    }
}
