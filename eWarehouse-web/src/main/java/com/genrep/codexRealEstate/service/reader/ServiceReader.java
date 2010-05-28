package com.genrep.codexRealEstate.service.reader;

import com.genrep.classification.service.IClassification;
import com.genrep.codex.procedure.ClassificationProcedure;
import com.genrep.operationset.service.exception.OperationSetException;
import com.genrep.realEstate.app.RealEstateWebApp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stojan
 */
public class ServiceReader extends AServiceReader {

    /**
     *
     * @return
     */
    public List<IClassification> getClassifications(String schemeName) {
        List legalStatuses = new ArrayList();
        ClassificationProcedure proc =
                new ClassificationProcedure(
                RealEstateWebApp.class.getName(), "classificationsSession");
        try {
            legalStatuses = proc.getClassifications(schemeName);
        } catch (OperationSetException ose) {
            ose.printStackTrace();
        }
        return legalStatuses;
    }

    public List<IClassification> getClassificationsByID(String schemeID) {
        List<IClassification> clsfs = new ArrayList();
        ClassificationProcedure proc =
                new ClassificationProcedure(
                RealEstateWebApp.class.getName(), "codexEntity");
        try {
            clsfs = proc.getClassificationsByID(schemeID);
        } catch (OperationSetException ose) {
            ose.printStackTrace();
        }
        return clsfs;
    }
}
