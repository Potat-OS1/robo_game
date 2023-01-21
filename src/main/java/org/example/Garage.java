package org.example;

public class Garage extends Launch {
    public void scene() {
        sceneLoop:
        while (true) {
            System.out.println("""
                    What to do?\s
                    1: Modify Robot\s
                    2: Inspect Tools\s
                    3: Leave""");
            int choice = Tools.select(3);
            switch (choice) {
                case (1) -> robotList();
                case (2) -> tools();
                case (3) -> {
                    break sceneLoop;
                }
            }
        }
    }

    public void robotList() {
        System.out.println("Modify which robot?: ");
        int a = 1;
        //print out the robot list.
        for (Robot mech : robotList) {
            System.out.printf("%-16s %-40s", a + ": " + mech.getName(), "- " + mech.getType());
            System.out.println();
            a++;
        }
        // collect user input on which robot to modify. the Integer modify will henceforth reference the selected robot. Minus one cause we started @ 1 instead of 0.
        int modify = Tools.select(robotList.size()) - 1;

        // print out the parts list.
        robotList.get(modify).getPartsList();

        // collect user input on which part to modify. the Integer part will henceforth reference the selected part on selected robot.
        System.out.println("Modify which part?");
        int part = Tools.select(robotList.get(modify).getNumParts()) - 1;

        // if that part is null, the user can only add a part.
        if (robotList.get(modify).targetPart(part) == null) {
            if (robotList.get(modify).getType() == "Type A") {
                addPartsA(part, modify);
            }
            if (robotList.get(modify).getType() == "Type B") {
                addPartsB(part, modify);
            }
        }
        else {
            System.out.println(robotList.get(modify).targetPart(part).getMods());
            System.out.println("Remove this part? y/n: ");
            boolean addremove = Tools.yesNo();
            if (addremove) {
                switch(robotList.get(modify).getType()) {
                    case ("Type A") -> {
                        removePartsA(part, modify);
                    }
                    case ("Type B") -> {
                        removePartsB(part, modify);
                    }
                }
                //add code here to add to the inventory the part removed.
            }
        }
    }

    public void addPartsA(int part, int modify) {
        if (Inventory.frameAParts.isEmpty()) {
            System.out.println("You have no parts on hand");
        }
        else {
            for(int b = 0; b < Inventory.frameAParts.size(); b = b + 3) {
                for(int c = 0; c < 3; c++) {
                    try{
                        System.out.printf("%-15s %-40s", "Inv Slot " + (b + c + 1) + ":  ", Inventory.frameAParts.get(b + c).getLimb() + " " + Inventory.frameAParts.get(b + c).getSet() + "   ");
                    }
                    catch(Exception e) {

                    }
                }
                System.out.println();
            }
            System.out.println("Add which part?");
            int num = Tools.select(Inventory.frameAParts.size());
            robotList.get(modify).addPart(Inventory.frameAParts.get(num - 1), part);
        }
    }

    public void addPartsB(int part, int modify) {
        if (Inventory.frameBParts.isEmpty()) {
            System.out.println("You have no parts on hand");
        }
        else {
            for(int b = 0; b < Inventory.frameBParts.size(); b = b + 3) {
                for(int c = 0; c < 3; c++) {
                    try{
                        System.out.print("Inv Slot " + (b + c + 1) + ":  " + Inventory.frameBParts.get(b + c).getSet() + "   ");
                    }
                    catch(Exception e) {

                    }
                }
                System.out.println();
            }
            System.out.println("Add which part?");
            int num = Tools.select(Inventory.frameBParts.size());

            if (robotList.get(modify).getType() == "Type B") {
                robotList.get(modify).addPart(Inventory.frameBParts.get(num - 1), part);
                Inventory.frameBParts.remove(num - 1);
            }
        }
    }

    public void removePartsA(int part, int modify) {
        robotList.get(modify).removePart(part);
    }

    public void removePartsB(int part, int modify) {
        robotList.get(modify).removePart(part);
    }


    public void tools() {

    }
}