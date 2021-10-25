package org.hbrs.ia;
import org.hbrs.ia.control.EingabeDialog;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        EingabeDialog eingabeDialog = new EingabeDialog();
        eingabeDialog.startEingabe();
    }
}
