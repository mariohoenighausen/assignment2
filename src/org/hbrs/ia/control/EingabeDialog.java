package org.hbrs.ia.control;

import java.util.Scanner;

public class EingabeDialog {

    Container container = Container.getInstance();

    public void startEingabe() {

        String strInput = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("Prio-Tool Prototype 1.0");
        while(sc.hasNext()) {

            strInput = sc.nextLine();

            String[] inputs = strInput.split(" ");

            switch(inputs[0]) {
                case "enter":
                    try {
                        container.addMember(new UserStory(Integer.parseInt(inputs[1]), inputs[2], Integer.parseInt(inputs[3]), Integer.parseInt(inputs[4]), Integer.parseInt(inputs[5]), Integer.parseInt(inputs[6])));
                    } catch(ContainerException e) {
                        e.printStackTrace();
                    }
                    System.out.println("User Story hinzugefügt!");
                    break;
                case "store":
                    try {
                        container.store();
                    } catch(PersistenceException e) {
                        e.printStackTrace();
                    }
                    break;
                case "load":
                    if(inputs.length > 1) {
                        if (inputs[1].equals("force")) {
                            try {
                                container.load();
                            } catch (PersistenceException e) {
                                e.printStackTrace();
                            }
                        }
                        if (inputs[1].equals("merge")) {
                            try {
                                container.loadMerge();
                            } catch (PersistenceException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
                case "dump":
                    AusgabeDialog ausgabeDialog = new AusgabeDialog();
                    if(inputs.length > 1) {
                        ausgabeDialog.startAusgabe(container, Integer.parseInt(inputs[2]));
                    }
                    else {
                        ausgabeDialog.startAusgabe(container);
                    }

                    break;
                case "exit":
                    System.exit(0);
                    sc.close();
                    break;
                case "help":
                    System.out.println("Mögliche Befehle: \n" +
                            "enter (ID Beschreibung Mehrwert Strafe Aufwand Risiko)\n" +
                            "store\n" +
                            "load [merge] [force]\n" +
                            "dump\n" +
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
