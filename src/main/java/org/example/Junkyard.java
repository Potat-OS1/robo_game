package org.example;

public class Junkyard {
    public void scene() {
        sceneLoop:
        while (true) {
            System.out.println("""
                    What to do?\s
                    1: Scavenge for Parts\s
                    2: aaaaa\s
                    3: Leave""");
            int choice = Tools.select(3);
            switch (choice) {
                case (1) -> generatePart();
                case (2) -> System.out.println("aaaa");
                case (3) -> {
                    break sceneLoop;
                }
            }
        }
    }
    public void generatePart() {
        int value = Tools.generateRandom(0, 3);
        if (value == 0) {
            System.out.println("Despite your searching, there was nothing valueable to be found");
        }
        else {
            int type = Tools.generateRandom(0, 1);
            switch(type) {
                case(0) -> {
                    Tools.generateRandomPart("Type A", value);
                    System.out.println("You found a part! its a: " + Inventory.frameAParts.get(Inventory.frameAParts.size() - 1).getSet()
                            + " " + Inventory.frameAParts.get(Inventory.frameAParts.size() - 1).getModel() + "! it appears to be a "
                            + Inventory.frameAParts.get(Inventory.frameAParts.size() - 1).getLimb());
                }
                case(1) -> {
                    Tools.generateRandomPart("Type B", value);

                }
            }
        }
    }
}
