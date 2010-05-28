/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genrep.application.locale;

import com.genrep.system.service.AppSystem;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import javax.faces.application.Application;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author vasko
 */
public class LocaleBean implements Serializable{

    private SelectItem[] supportedLocales;
    private String currentLocale;
    private String selectedLocale;

    public LocaleBean() {
        this.currentLocale = "mk_MK";
    }

    public SelectItem[] getSupportedLocales() {
        ArrayList items = new ArrayList();
        Application application = FacesContext.getCurrentInstance().getApplication();
        Iterator supportedLocs = application.getSupportedLocales();
        while (supportedLocs.hasNext()) {
            Locale loc = (Locale) supportedLocs.next();
            items.add(new SelectItem(loc.toString(), loc.getDisplayName()));
        }
        supportedLocales = new SelectItem[items.size()];
        items.toArray(supportedLocales);
        return supportedLocales;
    }

    public void setSupportedLocales(SelectItem[] supportedLocales) {
        this.supportedLocales = supportedLocales;
    }

    public String getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(String currentLocale) {
        this.currentLocale = currentLocale;
    }

    public String getSelectedLocale() {
        selectedLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale().toString();
        return selectedLocale;
    }

    public void setSelectedLocale(String selectedLocale) {
        Application application = FacesContext.getCurrentInstance().getApplication();
        Iterator supportedLocs = application.getSupportedLocales();
        while (supportedLocs.hasNext()) {
            Locale locale = (Locale) supportedLocs.next();
            if (locale.toString().equals(getSelectedLocale())) {
                FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
            }
        }
        this.selectedLocale = selectedLocale;
    }

    public void switchLocale(ActionEvent e) {
        if (!currentLocale.equals(getSelectedLocale())) {
            setCurrentLocale(getSelectedLocale());
        }
        AppSystem.getLogger().info("switching locale to: " + currentLocale);
    }
}
