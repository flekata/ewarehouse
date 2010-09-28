/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warehouse.action;

import com.genrep.guimodel.gui.comp.date.DateComp;
import com.genrep.system.service.AppSystem;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Map m = new HashMap();
        Object[] params = getParams();
        DateComp datod = (DateComp) params[0];
        DateComp datdo = (DateComp) params[1];
        Date datod1 = (Date) datod.getValueObject();
        Date datdo1 = (Date) datdo.getValueObject();
        if (datod1 == null) {
            try {
                datod1 = df.parse("01.01.2010");
            } catch (Exception ex) {
                AppSystem.getLogger().info("" + ex);
            }
        }
        if (datdo1 == null) {
            try {
                datdo1 = df.parse("31.12.2010");
            } catch (Exception ex) {
                AppSystem.getLogger().info("" + ex);
            }
        }
        m.put("OD", datod1);
        m.put("DO", datdo1);
        return m;
    }
}
