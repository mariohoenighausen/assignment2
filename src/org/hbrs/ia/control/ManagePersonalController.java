package org.hbrs.ia.control;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.hbrs.ia.model.EvaluationRecord;
import org.hbrs.ia.model.SalesMan;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ManagePersonalController implements ManagePersonal{
    @Override
    public void createSalesMan(SalesMan record) {
        try{
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            MongoDatabase database = mongoClient.getDatabase("evaluation_records_test");
            MongoCollection<Document> salesmen = database.getCollection("salesman");
            salesmen.insertOne(new Document()
                    .append("sid", record.getSid())
                    .append("firstName",record.getFirstName())
                    .append("lastName",record.getLastName())
                    .append("dob",record.getDob())
                    .append("experience",record.getExperience()));

        }
        catch(Exception exception){

        }
    }

    @Override
    public SalesMan readSalesMan(int sid) {
        List<Document> results = new ArrayList<>();
        try{
            MongoClient mongoClient = mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            MongoDatabase database = mongoClient.getDatabase("evaluation_records_test");
            MongoCollection<Document> salesmen = database.getCollection("salesman");
            Document query = new Document("sid",new Document(new Document("$eq",sid)));
             results = new ArrayList<>();
            salesmen.find(query).into(results);
            mongoClient.close();
        }
       catch(Exception exception){

       }
        if(results.size() == 0){
            throw new NoSuchElementException();
        }
        Document doc = results.get(0);
        SalesMan salesMan = new SalesMan(Integer.parseInt((String)doc.get("sid")),doc.get("firstName").toString(),doc.get("lastName").toString(),doc.get("dob").toString(),doc.get("experience").toString());
        return salesMan;
    }

    @Override
    public List<SalesMan> querySalesMan(String attribute, String key) {
        return null;
    }

    @Override
    public void updateSalesMan(int sid, SalesMan updatedSalesMan) {

    }

    @Override
    public SalesMan deleteSalesMan(int sid) {
        return null;
    }

    @Override
    public void addPerformanceRecord(EvaluationRecord record, int sid) {

    }

    @Override
    public List<EvaluationRecord> readAllEvaluationRecords(int sid) {
        return null;
    }

    @Override
    public List<EvaluationRecord> readSpecificEvaluationRecords(int sid, String attribute, String key) {
        return null;
    }

    @Override
    public void updatePerformanceRecord(int sid, EvaluationRecord updatedEvaluationRecord) {

    }

    @Override
    public EvaluationRecord deletePerformanceRecord(int sid, int evaluationRecordId) {
        return null;
    }

    @Override
    public EvaluationRecord deleteAllPerformanceRecords(int sid) {
        return null;
    }
}
