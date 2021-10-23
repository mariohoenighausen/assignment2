package org.hbrs.ia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.hbrs.ia.control.ManagePersonalController;
import org.hbrs.ia.model.EvaluationRecord;
import org.hbrs.ia.model.SalesMan;

import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class Main {
    private static void testDriver(){
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        //MongoDatabase database = mongoClient.getDatabase("food");
        /*MongoCollection<Document> fruits = database.getCollection("fruits");
        for (Document document : fruits.find()) {
            System.out.println("Name of the fruit " +document.get("name"));
            System.out.println("Origin of the fruit " +document.get("origin"));
            System.out.println("Price of the fruit " +document.get("price"));
        }*/
        MongoDatabase database = mongoClient.getDatabase("evaluation_records_test");
        MongoCollection<Document> salesmen = database.getCollection("salesman");
        for(Document salesman : salesmen.find()){
            System.out.println(salesman.get("firstName"));
        }
        //CREATE
        //salesmen.insertOne(new Document().append("firstName","Stewie"));

        //SELECT
        Document query = new Document("firstName", "stewie");
        List<Document> results = new ArrayList<>();
        salesmen.find(query).into(results);

        // UPDATE
        query = new Document(
                "sid",new Document(new Document("$eq","1")));
        Document update = new Document(
                "$push",
                new Document("sid","1000"));
        salesmen.updateOne(
                eq("firstName", "Peter"),
                combine(set("sid", "1000"), currentDate("lastModified")),
                new UpdateOptions().upsert(true).bypassDocumentValidation(true));
        //DELETE
        salesmen.deleteOne(eq("firstName", "Stewie"));
        results = new ArrayList<>();
        salesmen.find().into(results);
        for(Document salesman : results){
            System.out.println("Firstname: " + salesman.get("firstName") + "\n Sid: " + salesman.get("sid"));
        }
    }
    public static void main(String[] args) {
        System.out.println("Test");
        ManagePersonalController managePersonalController = new ManagePersonalController();
        // managePersonalController.createSalesMan(new SalesMan(1,"Peter","Griffin","22.10.2021",""));
        // SalesMan s = managePersonalController.readSalesMan(1);
        /*for(SalesMan sa :managePersonalController.querySalesMan("dob","20.08.1995")){
            System.out.println(sa.toString());
        }*/
        //managePersonalController.updateSalesMan(1000,new SalesMan(2,"Peter","Parker","30.06.1990","Being the friendly spider of the neighborhood"));
        //SalesMan s = managePersonalController.readSalesMan(1);

        EvaluationRecord rc = new EvaluationRecord(2,1,2,2015,"Being 65% good",1);
        //managePersonalController.addPerformanceRecord(rc,1);
        //managePersonalController.updatePerformanceRecord(1,rc);

        managePersonalController.deleteAllPerformanceRecords(1);
       for(EvaluationRecord evalRC: managePersonalController.readAllEvaluationRecords(1)){
            System.out.println(evalRC);
        }
    }
}
