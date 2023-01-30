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
                slotMenu(modify);
            }
        }
    }

    // The screen that has the robots to select and displays what type of frame they are to the player.
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

    //this menu shows all the slots that are modifyable to the player.
    private void slotMenu(int modify) {
        // collect user input on which part to modify. the Integer part will henceforth reference the selected part on selected robot.
        int part;
        while (true) {
            robotList.get(modify).getPartsList();
            System.out.println("Modify which part?");
            part = Tools.select((robotList.get(modify).getNumParts() + 1)) - 1;
            if (part == robotList.get(modify).getNumParts()) {
                break;
            }
            // if that part is null, the user can only add a part.
            if (robotList.get(modify).targetPart(part) == null) {
                switch(robotList.get(modify).getType()) {
                    case("Type A") -> addParts(part, modify, Inventory.frameAParts);
                    case("Type B") -> addParts(part, modify, Inventory.frameBParts);
                }
            }
            else {
                modOrRemoveScreen(modify, part);
            }
        }
    }

    private void modOrRemoveScreen(int modify, int part) {
        System.out.println(robotList.get(modify).targetPart(part).getMods());
        int modCount = robotList.get(modify).targetPart(part).getModCount();
        System.out.println((modCount + 1) + ": Remove part");
        int modSelect = Tools.select(modCount + 1);
        if (modSelect == (modCount + 1)) {
            removeParts(part, modify);
        }
        else{
            addMods(part, modify, (modSelect-1));
        }
    }

    private void addMods(int part, int modify, int modSelect) {
        if (!Inventory.modsObtained.isEmpty()) {
            printMods();
            System.out.println("Add which mod? (Input slot #)");
            int modSlot = Tools.select(Inventory.modsObtained.size())-1;
            robotList.get(modify).targetPart(part).addMod(modSelect, Inventory.modsObtained.get(modSlot));
            Inventory.modsObtained.remove(modSlot);
        }
        else {
            System.out.println("You do not have any mods on hand right now.");
        }
    }

    private void addParts(int part, int modify, ArrayList<Part> frameParts) {
        if (frameParts.isEmpty()) {
            System.out.println("You have no parts on hand");
        }
        else {
            printSets(frameParts);
            System.out.println((frameParts.size()+1) + ":      Exit");
            System.out.println("Add which part? (Input slot #)");
            int num = Tools.select(frameParts.size() + 1);
            if (num != frameParts.size() + 1) {
                robotList.get(modify).addPart(frameParts.get(num - 1), part);
            }
        }
    }

    private void removeParts(int part, int modify) {
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

    private void printMods() {
        for(int c = 0; c < Inventory.modsObtained.size(); c = c + 3) {
            for(int d = 0; d < 3; d++) {
                try{
                    System.out.printf("%-15s %-15s", "Inv Slot " + (c + d + 1) + ": ", Inventory.modsObtained.get(c + d).getModName());
                }
                catch(Exception e) {
                    // dont need to throw an error
                }
            }
            System.out.println();
        }
    }
}