package org.hbrs.ia.control;

import org.hbrs.ia.model.EvaluationRecord;
import org.hbrs.ia.model.SalesMan;

import java.util.List;

public interface ManagePersonal {

    void createSalesMan( SalesMan record );

    SalesMan readSalesMan( int sid );

    /**
     *
     * @param attribute criteria
     * @param key
     * @return list of salesmen that match criteria
     */
    List<SalesMan> querySalesMan(String attribute, String key );

    void updateSalesMan(int sid, SalesMan updatedSalesMan);

    SalesMan deleteSalesMan(int sid);

    void addPerformanceRecord(EvaluationRecord record);

    List<EvaluationRecord> readAllEvaluationRecords( int sid );

    void updatePerformanceRecord(int sid, EvaluationRecord updatedEvaluationRecord );

    void deletePerformanceRecord(int sid, int evaluationRecordId);

    void deleteAllPerformanceRecords(int sid);
}
