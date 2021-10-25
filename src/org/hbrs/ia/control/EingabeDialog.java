package org.hbrs.ia.control;

import org.hbrs.ia.model.EvaluationRecord;
import org.hbrs.ia.model.SalesMan;

import java.util.ArrayList;
import java.util.Scanner;

public class EingabeDialog {

    ManagePersonalController mpc = ManagePersonalController.getInstance("personal_manager");

    public void startEingabe() {

        String strInput;

        Scanner sc = new Scanner(System.in);
        System.out.println("Personal Manager");
        while (sc.hasNext()) {

            strInput = sc.nextLine();

            String[] inputs = strInput.split(" ");

            switch (inputs[0]) {
                case "createSalesMan":
                    mpc.createSalesMan(new SalesMan(Integer.parseInt(inputs[1]), inputs[2],
                            inputs[3], inputs[4], inputs[5]));
                    System.out.println("Salesman created!");
                    break;
                case "readSalesMan":
                    System.out.println(mpc.readSalesMan(Integer.parseInt(inputs[1])));

                    break;
                case "deleteSalesMan":
                    mpc.deleteSalesMan(Integer.parseInt(inputs[1]));
                    System.out.println("Salesman deleted!");
                    break;
                case "querySalesMan":
                    System.out.println(mpc.querySalesMan(inputs[1], inputs[2]));
                    break;
                case "updateSalesMan":
                    mpc.updateSalesMan(Integer.parseInt(inputs[1]), new SalesMan(Integer.parseInt(inputs[2]), inputs[3],
                            inputs[4], inputs[5], inputs[6]));
                    System.out.println("Salesman updated!");
                    break;
                case "addPerformanceRecord":
                    mpc.addPerformanceRecord(new EvaluationRecord(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]),
                            Integer.parseInt(inputs[3]), Integer.parseInt(inputs[4]), inputs[5],
                            Integer.parseInt(inputs[6])), Integer.parseInt(inputs[7]));
                    System.out.println("Performance Record added!");
                    break;
                case "readAllEvaluationRecords":
                    System.out.println(mpc.readAllEvaluationRecords(Integer.parseInt(inputs[1])));

                    break;
                case "updatePerformanceRecord":
                    mpc.updatePerformanceRecord(Integer.parseInt(inputs[1]),
                            new EvaluationRecord(Integer.parseInt(inputs[2]), Integer.parseInt(inputs[3]),
                                    Integer.parseInt(inputs[4]), Integer.parseInt(inputs[5]),
                                    inputs[6], Integer.parseInt(inputs[7])));
                    System.out.println("Performance Record updated!");
                    break;
                case "deletePerformanceRecord":
                    mpc.deletePerformanceRecord(Integer.parseInt(inputs[1]),
                            Integer.parseInt(inputs[2]));
                    System.out.println("Performance Record deleted!");
                    break;
                case "deleteAllPerformanceRecords":
                    mpc.deleteAllPerformanceRecords(Integer.parseInt(inputs[1]));
                    System.out.println("All Performance Records deleted!");
                    break;
                case "exit":
                    System.exit(0);
                    sc.close();
                    break;
                case "help":
                    System.out.println("Mögliche Befehle:\n" +
                            "createSalesMan\n" +
                            "readSalesMan\n" +
                            "deleteSalesMan\n" +
                            "querySalesMan\n" +
                            "updateSalesMan\n" +
                            "addPerformanceRecord\n" +
                            "readAllEvaluationRecords\n" +
                            "updatePerformanceRecord\n" +
                            "deletePerformanceRecord\n" +
                            "deleteAllPerformanceRecords\n" +
                            "exit\n" +
                            "help");
                    break;
                default:
                    System.out.println("Ungültiger Befehl!");
                    break;
            }

        }

    }

}
