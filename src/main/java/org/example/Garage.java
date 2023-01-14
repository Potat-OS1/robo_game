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
            System.out.print(a + ": " + mech.getInformation());
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
            //add future logic here so they can't add double of parts. or don't cause it could be funny.
            // also add a thing to actually add from inventory.
            if (Inventory.frameAParts.isEmpty()) {
                System.out.println("You have no parts on hand");
            } else {
                for(int b = 0; b < Inventory.frameAParts.size(); b = b + 3) {
                    for(int c = 0; c < 3; c++) {
                        try{
                            System.out.print("Inv Slot " + (b + c + 1) + ":  " + Inventory.frameAParts.get(b + c).getSet() + "   ");
                        }
                        catch(Exception e) {

                        }
                    }
                    System.out.println();
                }
                System.out.println("Add which part?");
                int num = Tools.select(Inventory.frameAParts.size());
                robotList.get(modify).addPart(Inventory.frameAParts.get(num - 1), part);
                Inventory.frameAParts.remove(num - 1);
            }
        }
        else {
            robotList.get(modify).targetPart(part).getMods();
            System.out.println("Remove this part? y/n: ");
            boolean addremove = Tools.yesNo();
            if (addremove) {
                try {
                    Inventory.frameAParts.add(robotList.get(modify).targetPart(part));
                    robotList.get(modify).removePart(part);
                } catch (Exception e) {
                    System.out.println("Fuck");
                }

                //add code here to add to the inventory the part removed.
            }
        }
    }


    public void addParts(int part, int modify) {
        robotList.get(modify).addPart(Inventory.frameAParts.get(0), part);
        Inventory.frameAParts.remove(0);
    }

    public void removeParts(int part, int modify) {
        Inventory.frameAParts.add(robotList.get(modify).targetPart(part));
        robotList.get(modify).removePart(part);
    }


    public void tools() {

    }
}