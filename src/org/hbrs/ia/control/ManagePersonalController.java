package org.hbrs.ia.control;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.hbrs.ia.model.EvaluationRecord;
import org.hbrs.ia.model.SalesMan;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

//TODO: Singleton
public class ManagePersonalController implements ManagePersonal{
    private String databaseName = "evaluation_records_test";
    private final String databaseStr = "mongodb://localhost:27017";
    private MongoClientURI mongoClientURI;
    private List<String> collectionNames;

    private static ManagePersonalController instance;

    public static synchronized ManagePersonalController getInstance(String databaseName){
        if(instance == null){
            instance = new ManagePersonalController(databaseName);
        }
        return instance;
    }
    private ManagePersonalController(String databaseName){
        this.databaseName = databaseName;
        mongoClientURI = new MongoClientURI(databaseStr);
        collectionNames = new ArrayList<>();
        collectionNames.add("salesman");
        collectionNames.add("evaluation_record");
    }
    @Override
    public void createSalesMan(SalesMan record) {

        try{
            MongoClient mongoClient = new MongoClient(mongoClientURI);
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> salesmen = database.getCollection(collectionNames.get(0));
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
            MongoClient mongoClient = new MongoClient(mongoClientURI);
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> salesmen = database.getCollection(collectionNames.get(0));
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
        SalesMan salesMan = new SalesMan((Integer) doc.get("sid"),doc.get("firstName").toString(),doc.get("lastName").toString(),doc.get("dob").toString(),doc.get("experience").toString());
        return salesMan;
    }

    @Override
    public List<SalesMan> querySalesMan(String attribute, String key) {
        List<Document> results = new ArrayList<>();
        try{
            MongoClient mongoClient = mongoClient = new MongoClient(mongoClientURI);
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> salesmen = database.getCollection(collectionNames.get(0));
            Document query = new Document(attribute, key);
            results = new ArrayList<>();
            salesmen.find(query).into(results);
        }
        catch(Exception exception){

        }
        ArrayList<SalesMan> salesMEN = new ArrayList<SalesMan>();
        for(Document doc : results){
            SalesMan s = new SalesMan(Integer.parseInt(doc.get("sid").toString()),doc.get("firstName").toString(),doc.get("lastName").toString(),doc.get("dob").toString(),doc.get("experience").toString());
            salesMEN.add(s);
        }
        return salesMEN;
    }

    @Override
    public void updateSalesMan(int sid, SalesMan updatedSalesMan) {
        List<Document> results = new ArrayList<>();
        try{
            MongoClient mongoClient = mongoClient = new MongoClient(mongoClientURI);
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> salesmen = database.getCollection(collectionNames.get(0));
            results = new ArrayList<>();

            salesmen.updateOne(
                    eq("sid", sid),
                    combine(set("firstName", updatedSalesMan.getFirstName()),set("lastName",updatedSalesMan.getLastName()),set("dob",updatedSalesMan.getDob()),set("experience",updatedSalesMan.getExperience()), currentDate("lastModified")),
                    new UpdateOptions().upsert(true).bypassDocumentValidation(true));
        }
        catch(Exception exception) {

        }
    }

    @Override
    public SalesMan deleteSalesMan(int sid) {
        SalesMan salesMan = new SalesMan();
        try {
            MongoClient mongoClient = mongoClient = new MongoClient(mongoClientURI);
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> salesmen = database.getCollection(collectionNames.get(0));
            salesMan = readSalesMan(sid);
            salesmen.deleteOne(eq("sid", sid));
            // TODO: maybe delete all associated evaluation_records aswell
        }
        catch(Exception ex){

        }
        return salesMan;
    }

    @Override
    public void addPerformanceRecord(EvaluationRecord record, int sid) {
        try {
            MongoClient mongoClient = mongoClient = new MongoClient(mongoClientURI);
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> evalRC = database.getCollection(collectionNames.get(1));
            evalRC.insertOne(new Document()
                    .append("erid",record.getErid())
                    .append("actualValue",record.getActualValue())
                    .append("targetValue",record.getTargetValue())
                    .append("year",record.getYear())
                    .append("goalDescription",record.getGoalDesc())
                    .append("sid",record.getSid()));
        }
        catch (Exception exception){

        }
    }
    @Override
    public List<EvaluationRecord> readAllEvaluationRecords(int sid) {
        List<Document> results = new ArrayList<>();
        try{
            MongoClient mongoClient = mongoClient = new MongoClient(mongoClientURI);
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> evaluationRC = database.getCollection(collectionNames.get(1));
            Document query = new Document("sid",sid);
            results = new ArrayList<>();
            evaluationRC.find(query).into(results);
        }
        catch(Exception exception){

        }
        ArrayList<EvaluationRecord> evaluationRecords = new ArrayList<>();
        for(Document doc : results){
            EvaluationRecord eva = new EvaluationRecord(Integer.parseInt(doc.get("erid").toString()),Integer.parseInt(doc.get("actualValue").toString()),Integer.parseInt(doc.get("targetValue").toString()),Integer.parseInt(doc.get("year").toString()),doc.get("goalDescription").toString(),Integer.parseInt(doc.get("sid").toString()));
            evaluationRecords.add(eva);
        }
        return evaluationRecords;
    }
    //TODO: implement method for selecting a single evaluation_record
    public EvaluationRecord readEvaluationRecord(int sid,int erid) {
        List<Document> results = new ArrayList<Document>();
        try{
            MongoClient mongoClient = mongoClient = new MongoClient(mongoClientURI);
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> evaluationRC = database.getCollection(collectionNames.get(1));
            Document query = new Document();
            evaluationRC.find(and(eq("sid",sid),eq("erid",erid))).into(results);
            mongoClient.close();
        }
        catch(Exception exception){

        }
        Document doc = results.get(0);
        EvaluationRecord eva = new EvaluationRecord(Integer.parseInt(doc.get("erid").toString()),Integer.parseInt(doc.get("actualValue").toString()),Integer.parseInt(doc.get("targetValue").toString()),Integer.parseInt(doc.get("year").toString()),doc.get("goalDescription").toString(),Integer.parseInt(doc.get("sid").toString()));

        return eva;
    }



    @Override
    public void updatePerformanceRecord(int sid, EvaluationRecord updatedEvaluationRecord) {
        List<Document> results = new ArrayList<>();
        try{
            MongoClient mongoClient = mongoClient = new MongoClient(mongoClientURI);
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> evalRC = database.getCollection(collectionNames.get(1));

            evalRC.updateOne(
                    and(eq("sid",sid),eq("erid", updatedEvaluationRecord.getErid())),
                    combine(set("erid", updatedEvaluationRecord.getErid()),set("actualValue",updatedEvaluationRecord.getActualValue()),set("targetValue",updatedEvaluationRecord.getTargetValue()),set("year",updatedEvaluationRecord.getYear()),set("goalDescription",updatedEvaluationRecord.getGoalDesc()), currentDate("lastModified")),
                    new UpdateOptions().upsert(true).bypassDocumentValidation(true));
        }
        catch(Exception exception) {

        }
    }

    //TODO: maybe return deleted performance record
    @Override
    public void deletePerformanceRecord(int sid, int evaluationRecordId) {
        EvaluationRecord evaluationRecord = new EvaluationRecord();
        try {
            MongoClient mongoClient = mongoClient = new MongoClient(mongoClientURI);
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> evalRcs = database.getCollection(collectionNames.get(1));
            //evaluationRecord = ;
            evalRcs.deleteOne(and(eq("sid", sid),eq("erid",evaluationRecordId)));
        }
        catch(Exception ex){

        }
        // return evaluationRecord;
    }
    //TODO: maybe return deleted performance records
    @Override
    public void deleteAllPerformanceRecords(int sid) {
        try{
            MongoClient mongoClient = mongoClient = new MongoClient(mongoClientURI);
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> evalRcs = database.getCollection(collectionNames.get(1));
            evalRcs.deleteMany(eq("sid", sid));
        }
        catch(Exception ex){

        }
    }
}
