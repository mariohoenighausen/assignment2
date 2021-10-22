package org.hbrs.ia.control;

import org.hbrs.ia.model.EvaluationRecord;
import org.hbrs.ia.model.SalesMan;

import java.util.List;

public interface ManagePersonal {

    void createSalesMan( SalesMan record );

    SalesMan readSalesMan( int sid );

    List<SalesMan> querySalesMan(String attribute, String key );

    void updateSalesMan(int sid, SalesMan updatedSalesMan);

    SalesMan deleteSalesMan(int sid);

    void addPerformanceRecord(EvaluationRecord record , int sid );

    List<EvaluationRecord> readAllEvaluationRecords( int sid );

    void updatePerformanceRecord(int sid, EvaluationRecord updatedEvaluationRecord );

    EvaluationRecord deletePerformanceRecord(int sid, int evaluationRecordId);

    EvaluationRecord deleteAllPerformanceRecords(int sid);
}
