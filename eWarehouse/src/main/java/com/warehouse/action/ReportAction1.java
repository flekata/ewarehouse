/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warehouse.action;

import com.genrep.guimodel.gui.comp.date.DateComp;
import com.genrep.guimodel.gui.comp.text.Text;
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
        Text datdo = (Text) params[1];
        Date datod1 = (Date) datod.getValueObject();
        String datdo1 = (String) datdo.getValueObject();
        m.put("DATE", datod1);
        m.put("WAR", datdo1);
        return m;
    }
}
