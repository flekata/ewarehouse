package com.genrep.coreRealEstate.service.reader;


import com.genrep.classification.service.IClassification;
import com.genrep.codex.operationset.ClassificationOperationSet;
import com.genrep.container.service.application.AppFactory;
import com.genrep.operationset.service.exception.OperationSetException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stojan
 */
public class ServiceReader extends AServiceReader {

    public List<IClassification> getClassificationsByClsID(String clsID) throws OperationSetException {
        List<IClassification> clsfs = new ArrayList();
        ClassificationOperationSet proc =
                new ClassificationOperationSet(
                AppFactory.getCurrentApplication().getName(), "codexEntity");
        clsfs = proc.getClassificationsByClsID(clsID);
        return clsfs;
    }

    public List<IClassification> getClassifications(String schemeName) throws OperationSetException {
        List<IClassification> clsfs = new ArrayList();
         ClassificationOperationSet proc =
                new ClassificationOperationSet(
                AppFactory.getCurrentApplication().getName(), "codexEntity");
        clsfs = proc.getClassifications(schemeName);
        return clsfs;
    }

    public List<IClassification> getClassificationsByID(String schemeID) throws OperationSetException {
        List<IClassification> clsfs = new ArrayList();
        ClassificationOperationSet proc = new ClassificationOperationSet(
                AppFactory.getCurrentApplication().getName(), "codexEntity");
        clsfs = proc.getClassificationsByID(schemeID);
        return clsfs;
    }

    public List<IClassification> getClassificationsByIDs(String schemeIDs) throws OperationSetException {
        ClassificationOperationSet proc =
                new ClassificationOperationSet(
                AppFactory.getCurrentApplication().getName(), "codexEntity");
        List<IClassification> clsfs = new ArrayList();
        String[] ids = schemeIDs.split("!");
        for (String id : ids) {

            clsfs.addAll(proc.getClassificationsByID(schemeIDs));
        }
        return clsfs;
    }


    public List<String> getClassificationsAsListString(String schemeNameID) throws OperationSetException {
        List<IClassification> clsfs = new ArrayList();
        ClassificationOperationSet proc =
                new ClassificationOperationSet(
                AppFactory.getCurrentApplication().getName(), "codexEntity");
        clsfs = proc.getClassificationsByID(schemeNameID);
        List<String> clsfsItems = new ArrayList<String>();
        for (IClassification classification : clsfs) {
            clsfsItems.add(classification.getClassificationName());
        }
        return clsfsItems;
    }

}
