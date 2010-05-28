package com.genrep.codexRealEstate.service.reader;

import com.genrep.classification.service.IClassification;
import com.genrep.classification.service.IClassificationScheme;
import com.genrep.codex.procedure.ClassificationProcedure;
import com.genrep.container.service.application.AppFactory;
import com.genrep.guimodel.gui.comp.combo.Combo;
import com.genrep.operationset.service.procedure.IProcedure;
import com.genrep.realEstate.app.RealEstateWebApp;
import com.genrep.session.service.manager.ISessionManagerImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author stojan
 */
public class WebServiceReader extends AServiceReader {

    private String byLawTypeId;
    private String legalDocumentId;

    public String getLegalDocumentId() {
        return legalDocumentId;
    }

    public void setLegalDocumentId(String legalDocumentId) {
        this.legalDocumentId = legalDocumentId;
    }

    public String getByLawTypeId() {
        return byLawTypeId;
    }

    public void setByLawTypeId(String byLawTypeId) {
        this.byLawTypeId = byLawTypeId;
    }

    public List<IClassification> getClassifications(String schemeName) {
        List<IClassification> clsfs = new ArrayList();
        ClassificationProcedure opSet =
                new ClassificationProcedure(
                RealEstateWebApp.class.getName(), "classificationsSession");
        try {
            clsfs = opSet.getClassifications(schemeName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clsfs;
    }

    public List<IClassification> getClassificationsByID(String schemeID) {
        List<IClassification> clsfs = new ArrayList();
        try {
             ClassificationProcedure opSet =
                new ClassificationProcedure(
                RealEstateWebApp.class.getName(), "classificationsSession");
            clsfs = opSet.getClassificationsByID(schemeID);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return clsfs;
    }

    public List<IClassification> getByLawTypeByID() {
        return getClassificationsByID(byLawTypeId);
    }

    public List<IClassification> getClassificationsByIDModified(HashMap fields, Object classification_SR) {
        List<IClassification> clsfs = new ArrayList();
        IClassificationScheme cls = null;
        Combo combo = (Combo) fields.get("classification_SR");
        if (combo != null) {
            cls = (IClassificationScheme) combo.getValueObject();
        } else if (classification_SR != null) {
            cls = (IClassificationScheme) classification_SR;
        }

        ClassificationProcedure proc =
                new ClassificationProcedure(
                RealEstateWebApp.class.getName(), "classificationsSession");
        try {
            if (cls != null) {
                clsfs = proc.getClassificationsByID(cls.getSchemeId());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return clsfs;
    }

    public List<SelectItem> getClassificationsAsSelectItems(String schemeName) {
        List<IClassification> clsfs = new ArrayList();
        ClassificationProcedure proc =
                new ClassificationProcedure(
                RealEstateWebApp.class.getName(), "classificationsSession");
        List<SelectItem> clsfsItems = new ArrayList<SelectItem>();
        try {
            clsfs = proc.getClassifications(schemeName);

            for (IClassification classification : clsfs) {
                clsfsItems.add(new SelectItem(classification.getClassificationName(), classification.getClassificationName()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clsfsItems;
    }

    public List<String> getClassificationsAsListString(String schemeNameID) {
        List<IClassification> clsfs = new ArrayList();
         ClassificationProcedure proc =
                new ClassificationProcedure(
                RealEstateWebApp.class.getName(), "classificationsSession");
        List<String> clsfsItems = new ArrayList<String>();
        try {
            clsfs = proc.getClassificationsByID(schemeNameID);

            for (IClassification classification : clsfs) {
                clsfsItems.add(classification.getClassificationName());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clsfsItems;
    }

    public List<String> getState(String schemeID) {
        List<IClassification> cls = new ArrayList();
         ClassificationProcedure proc =
                new ClassificationProcedure(
                RealEstateWebApp.class.getName(), "classificationsSession");
        IClassificationScheme shemeState = (IClassificationScheme) codexService.getClassificationSchemeBySchemeId((IProcedure) proc, schemeID);
        List<String> clsfsItems = new ArrayList<String>();
        try {
            cls = proc.getClassificationsByID(schemeID);
            cls.addAll(proc.getClassificationsByID(
                    shemeState.getSuperScheme().getSchemeId()));

            for (IClassification classification : cls) {
                clsfsItems.add(classification.getClassificationName());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clsfsItems;

    }

    //Get all classifications for a given scheme, including those in the subschemas
    public List<IClassification> getClassificationsForRootScheme(String schemeId) {
        List<IClassification> clsfs = new ArrayList();
        IClassificationScheme rootScheme = null;
        try {
            ClassificationProcedure opSet =
                new ClassificationProcedure(
                RealEstateWebApp.class.getName(), "classificationsSession");
            List<IClassificationScheme> schemes = opSet.getClassificationSchemeBySchemeId(schemeId);
            if (schemes != null && schemes.size() == 1) {
                rootScheme = schemes.get(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        List<IClassificationScheme> allSchemes = new ArrayList<IClassificationScheme>();
        if (rootScheme != null) {
            allSchemes.add(rootScheme);
            if (recursiveGetterForSchemes(rootScheme, null) != null) {
                allSchemes.addAll(recursiveGetterForSchemes(rootScheme, null));
            }

            for (IClassificationScheme iClassificationScheme : allSchemes) {
                clsfs.addAll((List) iClassificationScheme.getClassifications());
            }
        }

        return clsfs;
    }

    //Get all sub schemes for a given scheme
    public List<IClassificationScheme> recursiveGetterForSchemes(IClassificationScheme rootScheme, List<IClassificationScheme> schemes) {
        ISessionManagerImpl sessMngr = AppFactory.getSessionManagerImpl();
        if (schemes == null) {
            schemes = new ArrayList<IClassificationScheme>();
        }

        if (rootScheme.getSubSchemas() != null) {
            schemes.addAll((List) sessMngr.getObjectImpl(rootScheme.getSubSchemas()));
            for (IClassificationScheme iClassificationScheme : rootScheme.getSubSchemas()) {
                recursiveGetterForSchemes(iClassificationScheme, schemes);
            }
        }

        return schemes;
    }

    public List<IClassification> getLegalDocumentTypes() {
        return getClassificationsForRootScheme(legalDocumentId);
    }
}
