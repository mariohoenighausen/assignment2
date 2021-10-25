import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import org.hbrs.ia.control.ManagePersonalController;
import org.hbrs.ia.model.EvaluationRecord;
import org.hbrs.ia.model.SalesMan;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class salesmenTest {
    private ManagePersonalController mpc;

    @BeforeEach
    public void setup() {
        mpc = ManagePersonalController.getInstance("database_test");
    }

    @Test
    public void createandReadTest() {
        mpc.createSalesMan(new SalesMan(1, "Frank", "Castle", "01.05.2001", "bad"));
        SalesMan frankTest = mpc.readSalesMan(1);
        assertEquals(frankTest.getSid(), 1);
        assertEquals(frankTest.getFirstName(), "Frank");
        assertEquals(frankTest.getLastName(), "Castle");
        assertEquals(frankTest.getDob(), "01.05.2001");
        assertEquals(frankTest.getExperience(), "bad");

        mpc.addPerformanceRecord(new EvaluationRecord(1,1000,2000,2010,"good",1));
        for(EvaluationRecord evalRC: mpc.readAllEvaluationRecords(1)){
            assertEquals(evalRC.getErid(), 1);
            assertEquals(evalRC.getActualValue(), 1000);
            assertEquals(evalRC.getTargetValue(), 2000);
            assertEquals(evalRC.getYear(), 2010);
            assertEquals(evalRC.getGoalDesc(), "good");
            assertEquals(evalRC.getSid(),1);
        }
    }

    @Test
    public void updateTest()
    {
        mpc.updateSalesMan(1,new SalesMan(1,"Tom","test","01.01.00","new"));
        assertEquals(mpc.readSalesMan(1).getSid(),1);
        assertEquals(mpc.readSalesMan(1).getFirstName(),"Tom");
        assertEquals(mpc.readSalesMan(1).getLastName(),"test");
        assertEquals(mpc.readSalesMan(1).getDob(),"01.01.00");
        assertEquals(mpc.readSalesMan(1).getExperience(),"new");

        mpc.updatePerformanceRecord(1,new EvaluationRecord(1,500,750,2020,"very nice",1));
        for(EvaluationRecord evalRC: mpc.readAllEvaluationRecords(1)) {
            assertEquals(evalRC.getErid(), 1);
            assertEquals(evalRC.getActualValue(), 500);
            assertEquals(evalRC.getTargetValue(), 750);
            assertEquals(evalRC.getYear(), 2020);
            assertEquals(evalRC.getGoalDesc(), "very nice");
            assertEquals(evalRC.getSid(), 1);
        }
    }

    @Test
    public void deleteTest()
    {
        mpc.deleteSalesMan(1);
        assertThrows(NoSuchElementException.class,
                ()->{ assertNull(mpc.readSalesMan(1)); });

        mpc.deleteAllPerformanceRecords(1);
        assertThrows(NoSuchElementException.class,
                ()->{ assertNull(mpc.readEvaluationRecord(1,1)); });
    }

    @AfterAll
    public static void drop(){
        MongoClient mongoClient = mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoDatabase database = mongoClient.getDatabase("database_test");
        database.drop();
    }
}
