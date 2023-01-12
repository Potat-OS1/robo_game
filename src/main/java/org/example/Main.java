package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //add save checking here in the future maybe
        Launch launch = new Launch();
        launch.manager();
    }

}

class Launch {
    boolean introSeen = false;
    ArrayList<Robot> robotList = new ArrayList<>();
    public void manager() {
        intro();
        hub();
    }

    void intro() {
        if (!introSeen) {
            System.out.println("""
                    You were bored one day, so you with your infinite tinkering expertise, decided to make a mecha!\s
                    With a humble start, your first attempt was met with, well one might call them results.
                    Everything you could find until now was rusted to all get out. One day you find a flyer for a\s
                    competition. Maybe this will be your big break? lets find out.""");
            Robot firstRobot = new Robot("Rusty", "I");
            robotList.add(firstRobot);
            introSeen = true;
        }
    }

    void hub() {
        //in the future maybe try an enum here.
        System.out.println("""
                Options|\s
                1: Your Garage\s
                2: Mechanics\s
                3: Junkyard\s
                4: Arena\s
                5: Town""");
        int choice = Select(5);
        switch(choice) {
            case (1): garage();
            case (2): mechanics();
            case (3): junkyard();
            case (4): arena();
            case (5): town();
        }
    }

    void garage() {
        while(true) {
            //in the future maybe try an enum here.
            System.out.println("""
                    What to do?\s
                    1: Modify Robot\s
                    2: Inspect Tools\s
                    3: Leave""");
            int choice = Select(3);

            switch (choice) {
                case (1) -> {
                    System.out.println("Modify which robot?: ");
                    int a = 1;
                    //print out the robot list.
                    for (Robot mech : robotList) {
                        System.out.print(a + ": " + mech.getInformation());
                        a++;
                    }
                    // collect user input on which robot to modify. the Integer modify will henceforth reference the selected robot. Minus one cause we started @ 1 instead of 0.
                    int modify = Select(robotList.size()) - 1;

                    // print out the parts list.
                    robotList.get(modify).getPartsList();

                    // collect user input on which part to modify. the Integer part will henceforth reference the selected part on selected robot.
                    System.out.println("Modify which part?");
                    int part = Select(robotList.get(modify).getNumParts()) - 1;

                    // if that part is null, the user can only add a part.
                    if (robotList.get(modify).targetPart(part) == null) {
                        //add future logic here so they can't add double of parts. or don't cause it could be funny.
                        // also add a thing to actually add from inventory.
                        robotList.get(modify).addPart("Rusted Servicedroid Arm", 1, "Right Arm", 6, part);
                    } else {
                        robotList.get(modify).targetPart(part).getMods();
                        System.out.println("Remove this part? y/n: ");
                        boolean addremove = yesNo();
                        if (addremove) {
                            try {
                                robotList.get(modify).removePart(part);
                            }
                            catch(Exception e) {
                                System.out.println("Fuck");
                            }

                            //add code here to add to the inventory the part removed.
                        }
                    }
                }
                case (2) -> System.out.println("Your tools, while they may be old are in perfectly good condition.");
            }

            if (choice == 3) {
                break;
            }
        }
        hub();
    }

    void mechanics(){
        System.out.println("Nothing here for now.");
        hub();
    }

    void junkyard() {
        System.out.println("Nothing here for now.");
        hub();
    }

    void town() {
        System.out.println("Nothing here for now.");
        hub();
    }

    void arena() {
        System.out.println("Nothing here for now.");
        hub();
    }

    int Select(int max) {
        Scanner sc = new Scanner(System.in);
        int num;
        while(true) {
            try {
                num = Integer.parseInt(sc.nextLine());
                if (0 < num && num <= max) {
                    break;
                }
                else {
                    System.out.println("Invalid Menu Item: Out of Bounds.");
                }

            }
            catch(Exception e) {
                System.out.println("Invalid Menu Item: " + e.getCause());
            }
        }
        return num;
    }

    boolean yesNo() {
        Scanner sc2 = new Scanner(System.in);
        String input;
        while(true) {
            input = sc2.nextLine();
            if (input.equals("") || input.equals("n") || input.equals("y")) {
                break;
            }
            else {
                System.out.println("Invalid Input. Please type y for yes and n for no.");
            }
        }
        return input.equals("") || input.equals("y");
    }

}