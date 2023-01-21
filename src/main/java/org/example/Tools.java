package org.example;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Tools {
    static int select(int max) {
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

    static boolean yesNo() {
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

    static void generateRandomPart(String type, int value) {
        //                                       set(String)      model#(int)       rank       modlist              limb
        //Inventory.frameAParts.add(new Part("Iron Defender", 1, 2, new String[]{"Strong"}, Information.partType[0]));
        //System.out.println("Selected Set: " + randSet + "\nSelected Model Index: " + randModel + "\nSelected Rank: " + randRank);
        int randSet = generateRandom(0, 6);
        int randModel = generateRandom(0, Information.setModel[randSet][0].length - 1);
        int randRank = generateRandom(1, 3);
        if (type == "Type A") {
            int randLimb = generateRandom(0, Information.partTypeA.length - 1);
            Inventory.frameAParts.add(new Part(Information.setNames[randSet], randModel, randRank, null, Information.partTypeA[randLimb]));
        }
        if (type == "Type B") {
            int randLimb = generateRandom(0, Information.partTypeB.length - 1);
            Inventory.frameBParts.add(new Part(Information.setNames[randSet], randModel, randRank, null, Information.partTypeB[randLimb]));
        }
    }

    static int generateRandom(int min, int max) {
        //i took this code from stack overflow trying to find the old method that i did it that i couldn't quite
        //remember, the max bound needs to be one more than the max bound you intend. this method of
        //getting a random int has the upside of not creating a .random instance.
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
