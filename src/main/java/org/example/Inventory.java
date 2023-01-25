package org.example;

import java.util.ArrayList;

class Inventory{
    public static ArrayList<ArrayList<Part>> inv = new ArrayList<>();
    public static ArrayList<Part> frameAParts = new ArrayList<>();
    public static ArrayList<Part> frameBParts = new ArrayList<>();
    public static ArrayList<Mod> modsObtained = new ArrayList<>();

    public static void initialize() {
        inv.add(frameAParts);
        inv.add(frameBParts);

        // add parts to inventory just for debug
        for (int a = 0; a < 6; a++) {
            //Tools.generateRandomPart("Type A", 2);
            Tools.generateRandomPart("Type A");
        }
//        frameAParts.add(new Part("Iron Defender", 2, 2, new String[]{"Strong"}, Information.partType[1]));
    }
}

class Information{
    public static String[] setNames = {"Rusted Service-Droid", "Iron Defender", "Steel Protector", "Value-Brand Economy", "Performance", "Carbon-Alloy", "Aluminum"}; //7

    // first [] = set, second [] = model or slot info, third [] = value
    public static String[][][] setModel = {
            // model #                               slots
            {{"E-023", "E-047", "E-434"},            {"1", "1", "2"}},
            {{"FE-01", "FE-02", "FE-03"},            {"2", "2", "3"}},
            {{"CFE-04", "CFE-08"},                   {"3", "3"}},
            {{"40030", "43342", "45456", "49876"},   {"2", "2", "2", "3"}},
            {{"PER-234"},                            {"4"}},
            {{"2CFeNMnP", "4C3FeNMnP"},              {"4", "5"}},
            {{"AL-333", "AL-666", "AL-999"},         {"4", "4", "4"}}};

    public static String[] partTypeA = {"Right Arm","Left Arm","Right Leg", "Left Leg","Back Plate","Chest Plate","Head Peice"}; //7
    public static String[] partTypeB = {"Leg", "Head Peice", "Upper Shell", "Lower Shell"}; //4

    //                               name - weight - power - hp - perk
    public static String[][] Mods = {{"Strong","5","5","5","+2 power"},{"Light","1","2","2","+1 speed"}};
}

// N# = a number of mods determined by the model of part.
// D# = a number of parts determined by the frame.
//
// set -- frame type A -- Model[N] -- Right Arm -- N# Mods
//     |                           |- Left Arm -- N# Mods
//     |                           |- Right Leg -- N# Mods
//     |                           |- Left Leg -- N# Mods
//     |                           |- Right Leg -- N# Mods
//     |                           |- Back Plate -- N# Mods
//     |                           |- Chest Plate -- N# Mods
//     |                           |- Head Peice -- N# Mods
//     |
//     |- frame type B [D] -- Model[N] -- Upper Shell -- N# Mods
//                                     |- Lower Shell -- N# Mods
//                                     |- D# Leg -- N# Mods
//                                     |- Head Peice -- N# Mods