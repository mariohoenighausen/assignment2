package main.main;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {
        System.out.println("Test");
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoDatabase database = mongoClient.getDatabase("food");
        MongoCollection<Document> fruits = database.getCollection("fruits");
        for (Document document : fruits.find()) {
            System.out.println("Name of the fruit " +document.get("name"));
            System.out.println("Origin of the fruit " +document.get("origin"));
            System.out.println("Price of the fruit " +document.get("price"));
        }


    }
}
