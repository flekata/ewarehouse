/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genrep.codexRealEstate.service.reader;


import com.genrep.codex.procedure.ClassificationProcedure;
import com.genrep.codex.service.ICodexService;
import com.genrep.realEstate.app.RealEstateWebApp;
import com.genrep.system.service.AppSystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 *
 * @author ristes
 */
public abstract class AServiceReader {

    protected ICodexService codexService;
    protected Map<String, String> map = new HashMap<String, String>();

    public void setCodexService(ICodexService codexService) {
        this.codexService = codexService;
    }

    public AServiceReader() {
    }

    public List get(String codexEntity) {
        try {
            return findAllByClassName(RealEstateWebApp.class.getName(), codexEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    protected List findAllByClassName(String className, String resultClassName) {
        try {
            List lst = new ArrayList();
            ClassificationProcedure opset = new ClassificationProcedure(className, "codexEntity");
            AppSystem.getLogger().info(Class.forName(resultClassName).toString());
            lst = opset.findAll(Class.forName(resultClassName));
            return lst;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // TODO: ako pritreba za vo nekoj date converter
    public TimeZone getTimeZone() {
        TimeZone tz = TimeZone.getDefault();
        System.out.println("default timezone: " + tz);
        return tz;
    }
}
