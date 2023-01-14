package org.example;

import java.util.Scanner;

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
}
