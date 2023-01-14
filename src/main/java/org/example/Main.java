package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //add save checking here in the future maybe
        Launch launch = new Launch();
        launch.manager();
    }

}

class Launch {
    boolean introSeen = false;
    static ArrayList<Robot> robotList = new ArrayList<>();
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
        Inventory.initialize();
        while(true) {
            System.out.println("""
                    Options|\s
                    1: Your Garage\s
                    2: Mechanics\s
                    3: Junkyard\s
                    4: Arena\s
                    5: Town\s
                    6: test""");
            int choice = Tools.select(6);
            Garage g = new Garage();
            switch (choice) {
                case (1) -> g.scene();
                case (2) -> mechanics();
                case (3) -> junkyard();
                case (4) -> arena();
                case (5) -> town();
                case (6) -> System.out.println();
            }
        }
    }
    void mechanics(){
        System.out.println("Nothing here for now. doop");
        hub();
    }

    void junkyard() {
        System.out.println("Nothing here for now. oop");
        hub();
    }

    void town() {
        System.out.println("Nothing here for now. toop");
        hub();
    }

    void arena() {
        System.out.println("Nothing here for now. roop");
        hub();
    }
}