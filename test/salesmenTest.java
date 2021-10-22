import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class salesmenTest {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private static MongoCollection<Document> salesmenTest;
    private List<Document> results;
    @BeforeEach
    public void setup(){
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        database = mongoClient.getDatabase("evaluation_records_test");
        salesmenTest = database.getCollection("salesmanTest");
        results = new ArrayList<>();
    }

    @Test
    public void createTest()
    {
        String name = "Stewie";
        salesmenTest.insertOne(new Document().append("firstName","Stewie"));
        for(Document salesman : salesmenTest.find()) {
            assertEquals(name, salesman.get("firstName"));
        }
    }


    @Test
    public void readTest()
    {
        String name = "Stewie";
        Document query = new Document("firstName", "stewie");
        List<Document> results = new ArrayList<>();
        salesmenTest.find(query).into(results);
        for(Document salesman : salesmenTest.find()) {
            assertEquals(name, salesman.get("firstName"));
        }
    }
    @Test
    public void updateTest()
    {
        String name = "Stewie";
        String id = "1000";
        Document query = new Document(
                "sid",new Document(new Document("$eq","1")));
        Document update = new Document(
                "$push",
                new Document("sid","1000"));
        salesmenTest.updateOne(
                eq("firstName", "Stewie"),
                combine(set("sid", "1000"), currentDate("lastModified")),
                new UpdateOptions().upsert(true).bypassDocumentValidation(true));
        for(Document salesman : salesmenTest.find()) {
            assertEquals(name, salesman.get("firstName"));
            assertEquals(id, salesman.get("sid"));
        }
    }
    @Test
    public void deleteTest()
    {
        salesmenTest.deleteOne(eq("firstName", "Stewie"));
        results = new ArrayList<>();
        salesmenTest.find().into(results);
        for(Document salesman : results){
            assertNull(salesman.get("sid"));
        }

    }

    @AfterAll
    public static void drop(){
        salesmenTest.drop();
    }
}
