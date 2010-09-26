package com.genrep.service.reader;

import com.genrep.classification.service.Classification;
import com.genrep.classification.service.ClassificationScheme;
import com.genrep.classification.service.IClassification;
import com.genrep.codex.procedure.ClassificationProcedure;
import com.genrep.container.service.application.AppFactory;
import com.genrep.operationset.service.exception.OperationSetException;
import com.genrep.operationset.service.procedure.IProcedure;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author stojan
 */
public class RegistrationServiceReader extends AServiceReader {

    /**
     *
     * @param schemeName
     * @return
     */
    public List<Classification> getClassifications(String schemeName) {
        List legalStatuses = new ArrayList();
        ClassificationProcedure proc =
                new ClassificationProcedure(
                AppFactory.getCurrentApplication().getName(), "codexEntity");
        try {
            legalStatuses = proc.getClassifications(schemeName);
        } catch (OperationSetException ose) {
            ose.printStackTrace();
        }
        return legalStatuses;
    }

    public List<String> getState(String schemeID) {
        List<IClassification> cls = new ArrayList();
        ClassificationProcedure proc =
                new ClassificationProcedure(
                AppFactory.getCurrentApplication().getName(), "codexEntity");
        ClassificationScheme shemeState = (ClassificationScheme) codexService.getClassificationSchemeBySchemeId((IProcedure) proc, schemeID);
        cls = (List<IClassification>) codexService.getClassificationsByID((IProcedure) proc, schemeID);
        if (shemeState == null) {
            return null;
        }
        cls.addAll((Collection<? extends IClassification>) codexService.getClassificationsByID((IProcedure) proc, shemeState.getSuperScheme().getSchemeId()));
        List<String> clsfsItems = new ArrayList<String>();
        for (IClassification classification : cls) {
            clsfsItems.add(classification.getClassificationName());
        }
        return clsfsItems;

    }
}
