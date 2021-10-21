package org.hbrs.ia.control;

import org.hbrs.ia.model.EvaluationRecord;
import org.hbrs.ia.model.SalesMan;

import java.util.List;

public class ManagePersonalController implements ManagePersonal{
    @Override
    public void createSalesMan(SalesMan record) {

    }

    @Override
    public void addPerformanceRecord(EvaluationRecord record, int sid) {

    }

    @Override
    public SalesMan readSalesMan(int sid) {
        return null;
    }

    @Override
    public List<SalesMan> querySalesMan(String attribute, String key) {
        return null;
    }

    @Override
    public EvaluationRecord readEvaluationRecords(int sid) {
        return null;
    }
}
