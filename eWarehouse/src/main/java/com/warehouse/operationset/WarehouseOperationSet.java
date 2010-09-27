/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warehouse.operationset;

import com.genrep.operationset.service.AOperationSet;
import com.genrep.operationset.service.annotation.ModifyMethod;
import com.genrep.operationset.service.exception.OperationSetException;
import com.genrep.session.service.query.ICriteria;
import com.genrep.session.service.query.IExpression;
import com.genrep.session.service.query.IProjections;
import com.genrep.session.service.query.IQuery;
import com.genrep.session.service.query.ISqlQuery;
import com.warehouse.core.Input;
import com.warehouse.core.Output;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user1
 */
public class WarehouseOperationSet extends AOperationSet {

    public WarehouseOperationSet(String appClassName, String sessionName) {
        super(appClassName, sessionName);
    }

    public List getWarehausesBalance(String wrhName) {
        try {
            ISqlQuery query;
            query = getOpSession().createSQLQuery("select sum(neso.size1) as siz, "
                    + "neso.war, neso.itm "
                    + "from ( "
                    + "select WAREHOUSE.INPU.SIZE as size1, WAREHOUSE.WARH.NAME as war, WAREHOUSE.ITM.NAME as itm "
                    + "from WAREHOUSE.INPU join WAREHOUSE.WARH on WAREHOUSE.WARH.UID=WAREHOUSE.INPU.WRHS join WAREHOUSE.ITM "
                    + "on WAREHOUSE.ITM.UID=WAREHOUSE.INPU.ITMM WHERE WAREHOUSE.WARH.NAME='" + wrhName + "' "
                    + "union select -WAREHOUSE.OUTPU.SIZE as size1, WAREHOUSE.WARH.NAME as war, WAREHOUSE.ITM.NAME as itm "
                    + "from WAREHOUSE.OUTPU join WAREHOUSE.WARH on WAREHOUSE.WARH.UID=WAREHOUSE.OUTPU.WRHS join WAREHOUSE.ITM on WAREHOUSE.ITM.UID=WAREHOUSE.OUTPU.ITMM "
                    + "WHERE WAREHOUSE.WARH.NAME='" + wrhName + "' ) as neso group by neso.war, neso.itm");

            return query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List getWarehausesInput(String wrhName) {
        try {
            ICriteria criteria = getOpSession().createCriteria(Input.class);
            IExpression expression = criteria.createExpression();
            IProjections projection = criteria.createProjections();

            criteria.createAlias("warehouse", "warehouse");
            criteria.createAlias("item", "item");
            criteria.addExpresion(expression.eq("warehouse.name", wrhName));
            projection.add(projection.groupProperty("item.name"));
            projection.add(projection.sum("size"));
            projection.add(projection.groupProperty("warehouse.name"));
            criteria.addOrderDesc("warehouse.name");
            criteria.setProjection(projection);

            return criteria.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List getWarehausesOutput(String wrhName) {
        try {
            ICriteria criteria = getOpSession().createCriteria(Output.class);
            IExpression expression = criteria.createExpression();
            IProjections projection = criteria.createProjections();

            criteria.createAlias("warehouse", "warehouse");
            criteria.createAlias("item", "item");
            criteria.addExpresion(expression.eq("warehouse.name", wrhName));
            projection.add(projection.groupProperty("item.name"));
            projection.add(projection.sum("size"));
            projection.add(projection.groupProperty("warehouse.name"));
            criteria.addOrderDesc("warehouse.name");
            criteria.setProjection(projection);

            return criteria.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List findInput(String itemName, String whname, Integer size, BigDecimal price, Date date) {
        try {
            ICriteria criteria = getOpSession().createCriteria(Input.class);
            IExpression expression = criteria.createExpression();

            if (itemName != null) {
                criteria.createAlias("item", "item");
                criteria.addExpresion(expression.eq("item.name", itemName));
            }
            if (whname != null) {
                criteria.createAlias("warehouse", "warehouse");
                criteria.addExpresion(expression.eq("warehouse.name", whname));
            }
            if (size != null) {
                criteria.addExpresion(expression.eq("size", size));
            }
            if (price != null) {
                criteria.addExpresion(expression.eq("price", price));
            }
            if (date != null) {
                criteria.addExpresion(expression.eq("date", date));
            }

            return criteria.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List findOutput(String itemName, String whname, Integer size, String orderCode, Date date, String individualname) {
        try {
            ICriteria criteria = getOpSession().createCriteria(Output.class);
            IExpression expression = criteria.createExpression();

            if (itemName != null) {
                criteria.createAlias("item", "item");
                criteria.addExpresion(expression.eq("item.name", itemName));
            }
            if (whname != null) {
                criteria.createAlias("warehouse", "warehouse");
                criteria.addExpresion(expression.eq("warehouse.name", whname));
            }
            if (size != null) {
                criteria.addExpresion(expression.eq("size", size));
            }
            if (orderCode != null) {
                criteria.addExpresion(expression.eq("orderCode", orderCode));
            }
            if (date != null) {
                criteria.addExpresion(expression.eq("date", date));
            }
            if (individualname != null) {
                criteria.createAlias("individual", "individual");
                criteria.addExpresion(expression.eq("individual.name", individualname));
            }
            return criteria.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @ModifyMethod(modificationName = "deleteObject", modificationType = ModifyMethod.ModificationType.DELETE, modificationResult = ModifyMethod.ModificationResult.COLLECTION)
    public void deleteObject(Object o, boolean non_transactional)
            throws OperationSetException {
        try {

            if (!non_transactional) {
                beginTransaction();
            }
            getOpSession().delete(o);
            getOpSession().flush();
            successTransaction(null);
        } catch (Exception e) {
            failTransaction(new OperationSetException("deleteObject", e));
        } finally {
            cleanUp();
        }
    }
}
