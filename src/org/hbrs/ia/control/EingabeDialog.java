package org.hbrs.ia.control;

import org.hbrs.ia.model.EvaluationRecord;
import org.hbrs.ia.model.SalesMan;

import java.util.ArrayList;
import java.util.Scanner;

public class EingabeDialog {

    ManagePersonalController mpc = new ManagePersonalController();

    public void startEingabe() {

        String strInput = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("Personal Manager");
        while(sc.hasNext()) {

            strInput = sc.nextLine();

            String[] inputs = strInput.split(" ");

            switch(inputs[0]) {
                case "createSalesMan":
                        mpc.createSalesMan(new SalesMan(Integer.parseInt(inputs[1]), inputs[2],
                                inputs[3], inputs[4], inputs[5]));
                    System.out.println("Salesman created!");
                    break;
                case "addPerformanceRecord":
                    mpc.addPerformanceRecord(new EvaluationRecord(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]),
                            Integer.parseInt(inputs[3]), Integer.parseInt(inputs[4]), Integer.parseInt(inputs[5])));
                    break;
                case "readSalesMan":
                    break;
                case "querySalesMan":
                    mpc.querySalesMan(inputs[1], inputs[2]);

                    break;
                case "readEvaluationRecords":
                    mpc.readEvaluationRecords(Integer.parseInt(inputs[1]));
                    break;
                case "exit":
                    System.exit(0);
                    sc.close();
                    break;
                case "help":
                    System.out.println("Mögliche Befehle: \n" +
                            "createSalesMan\n" +
                            "addPerformanceRecord\n" +
                            "readSalesMan\n" +
                            "querySalesMan\n" +
                            "readEvaluationRecords\n" +
                            "exit\n +" +
                            "help");
                    break;
                default:
                    System.out.println("Ungültiger Befehl!");
                    break;
            }

        }

    }

}
