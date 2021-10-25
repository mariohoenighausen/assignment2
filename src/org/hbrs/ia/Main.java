package org.hbrs.ia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.hbrs.ia.control.EingabeDialog;
import org.hbrs.ia.control.ManagePersonalController;
import org.hbrs.ia.model.EvaluationRecord;
import org.hbrs.ia.model.SalesMan;

import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class Main {
    public static void main(String[] args) {
        EingabeDialog eingabeDialog = new EingabeDialog();
        eingabeDialog.startEingabe();
    }
}
