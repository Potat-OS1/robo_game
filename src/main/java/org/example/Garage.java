package org.example;

import java.util.ArrayList;

public class Garage extends Launch {
    public void scene() {
        sceneLoop:
        while(true) {
            System.out.println("""
                    What to do?\s
                    1: Modify Robot\s
                    2: Inspect Tools\s
                    3: Leave""");
            int choice = Tools.select(3);
            switch (choice) {
                case (1) -> modifyMenu();
                case (2) -> System.out.println("aaaaa");
                case (3) -> {
                    break sceneLoop;
                }
            }
        }
    }

    private void modifyMenu() {
        // modifyRobotMenu prints out the robot list then runs Tools.select to pick one.
        while(true) {
            int modify = modifyRobotMenu() - 1;
            if (modify == robotList.size()) {
                break;
            }
            else {
                // print out the parts list.
                robotList.get(modify).getPartsList();
                slotMenu(modify);
            }
        }
    }

    private void slotMenu(int modify) {
        // collect user input on which part to modify. the Integer part will henceforth reference the selected part on selected robot.
        System.out.println("Modify which part?");
        int part = Tools.select(robotList.get(modify).getNumParts()) - 1;

        // if that part is null, the user can only add a part.
        if (robotList.get(modify).targetPart(part) == null) {
            switch(robotList.get(modify).getType()) {
                case("Type A") -> addPartsA(part, modify);
                case("Type B") -> addPartsB(part, modify);
            }
        }

        else {
            System.out.println(robotList.get(modify).targetPart(part).getMods());
            System.out.println("Remove this part? y/n: ");
            boolean addremove = Tools.yesNo();
            if (addremove) {
                switch(robotList.get(modify).getType()) {
                    case ("Type A") -> removePartsA(part, modify);
                    case ("Type B") -> removePartsB(part, modify);
                }
            }
        }
    }

    private int modifyRobotMenu() {
        System.out.println("Modify which robot?: ");
        int a = 1;
        //print out the robot list.
        for (Robot mech : robotList) {
            System.out.printf("%-16s %-40s", a + ": " + mech.getName(), "- " + mech.getType());
            System.out.println();
            a++;
        }
        System.out.println(a + ": Exit");
        // collect user input on which robot to modify. the Integer modify will henceforth reference the selected robot. Minus one cause we started @ 1 instead of 0.
        return Tools.select(robotList.size()+1);
    }

    private void addPartsA(int part, int modify) {
        if (Inventory.frameAParts.isEmpty()) {
            System.out.println("You have no parts on hand");
        }
        else {
            printSets(Inventory.frameAParts);
            System.out.println("Add which part?");
            int num = Tools.select(Inventory.frameAParts.size());

            robotList.get(modify).addPart(Inventory.frameAParts.get(num - 1), part);
        }
    }

    private void addPartsB(int part, int modify) {
        if (Inventory.frameBParts.isEmpty()) {
            System.out.println("You have no parts on hand");
        }
        else {
            printSets(Inventory.frameBParts);
            System.out.println("Add which part?");
            int num = Tools.select(Inventory.frameBParts.size());
            robotList.get(modify).addPart(Inventory.frameBParts.get(num - 1), part);
        }
    }

    private void removePartsA(int part, int modify) {
        robotList.get(modify).removePart(part);
    }

    private void removePartsB(int part, int modify) {
        robotList.get(modify).removePart(part);
    }

    private void printSets(ArrayList<Part> frameParts) {
        for(int b = 0; b < frameParts.size(); b = b + 3) {
            for(int c = 0; c < 3; c++) {
                try{
                    System.out.printf("%-15s %-40s", "Inv Slot " + (b + c + 1) + ":  ", frameParts.get(b + c).getLimb() + " " + frameParts.get(b + c).getSet() + "   ");
                }
                catch(Exception e) {
                    //we are just trying to put mutliple values on each row, we do not *need* to throw an error.
                }
            }
            System.out.println();
        }
    }
}