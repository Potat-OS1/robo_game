package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Robot {
    private Part[] parts;
    private String type;
    private final String name;
    private int maxSlots;

    Robot(String name, String type) {
        this.name = name;
        if (type.contains("Type A")){
            this.maxSlots = 6;
            parts = new Part[6];
            this.type = "Type A";
        }
        if (type.contains("Type B")){
            this.maxSlots = 9;
            parts = new Part[9];
            this.type = "Type B";
        }
    }

    public void addPart(Part part, int slot) {
        if (type.contains("Type A")) {
            parts[slot] = part;
            Inventory.frameAParts.remove(part);
        }
        if (type.contains("Type B")) {
            parts[slot] = part;
            Inventory.frameBParts.remove(part);
        }

    }

    public String getName() {
        return name;
    }
    public void getPartsList() {
        int b = 0;
        for (Part part : parts) {
            b++;
            try {
                System.out.println("Slot " + b + ":  " + part.getLimb() + part.getPart());
            }
            catch(Exception e) {
                System.out.println("Slot " + b + ":  Nothing Installed.");
            }
        }
        System.out.println();
    }

    public String getType() {
        return type;
    }

    public int getNumParts() {
        return maxSlots;
    }

    public Part targetPart(int slot){
        return parts[slot];
    }

    public void removePart(int slot) {
        Inventory.frameAParts.add(targetPart(slot));
        parts[slot] = null;
    }
}

class Part{
    private final String set;
    private final String model;
    private final Mod[] modList;
    private final int modCount;
    private final int rank;
    private final String limb;

    //plan to use this later.
    Part(String set, int modelIndex, int rank, String limb) {
        this.set = set;
        this.model = Information.setModel[Arrays.asList(Information.setNames).indexOf(this.set)][0][modelIndex];
        this.modCount = Integer.parseInt(Information.setModel[Arrays.asList(Information.setNames).indexOf(this.set)][1][modelIndex]);
        this.modList = new Mod[this.modCount];
        this.limb = limb;
        //didnt know i could make the field name ignored to not worry about a name specifically
        for(Mod ignored : modList) {
            addMod();
        }
        this.rank = rank;
    }

    Part(String set, int modelIndex, int rank, String[] mods, String limb) {
        this.set = set;
        this.model = Information.setModel[Arrays.asList(Information.setNames).indexOf(this.set)][0][modelIndex];
        this.modCount = Integer.parseInt(Information.setModel[Arrays.asList(Information.setNames).indexOf(this.set)][1][modelIndex]);
        this.modList = new Mod[this.modCount];
        this.limb = limb;
        // try to fill the part with mods given, but if not possible, fill with a blank mod.
        for(int a = 0; a < modList.length; a++) {
            try {
                modList[a] = addMod(mods[a]);
            }
            catch(Exception e) {
                modList[a] = addMod();
            }
        }
        this.rank = rank;
    }

    public String getLimb() {
        return limb;
    }

    public String getPart() {
        return " Rank " + getRank() + " " + getSet() + " " + getModel() + " w/" + getSlotCount() + " slots.";
    }

    public String getModel() {
        return model;
    }

    public String getSet() {
        return set;
    }

    public int getRank() {
        return rank;
    }

    public int getSlotCount() {
        return modList.length;
    }

    public Mod addMod() {
        return new Mod();
    }

    public Mod addMod(String mod) {
        return new Mod(mod);
    }

    public String getMods() {
        //string builder is something intellij suggested.
        StringBuilder temp = new StringBuilder();
        for(Mod m : this.modList) {
            try {
                temp.append(m.getModName()).append("\n");
            }
            catch(Exception e) {
                //dont need to error handle
            }
        }
        return temp.toString();
    }

    public void removeMod(int slot) {
        Mod m = modList[slot];
        Inventory.modsObtained.add(m);
        modList[slot] = addMod();
    }

    // grab all mods stats
    public String modStats() {
        int hp = 0;
        int power = 0;
        int weight = 0;
        ArrayList<String> modNames = new ArrayList<>();
        ArrayList<String> modPerks = new ArrayList<>();
        for(Mod m : modList) {
            hp = hp + m.getModHp();
            power = power + m.getModPower();
            weight = weight + m.getModWeight();
            modNames.add(m.getModName());
            modPerks.add(m.getPerk());
        }
        return "Aggregated HP: " + hp + " Aggregated Power: " + power + " Aggregated Weight" + weight + "\nList of Mods: " + modNames + "\nAnd their associated perks: " + modPerks;
    }
}

class Mod{
    private String modName;
    private int modHp;
    private int modWeight;
    private int modPower;
    private String perk;

    // an empty mod.
    Mod() {
        this.modName = "No mod installed.";
        this.modHp = 0;
        this.modWeight = 0;
        this.modPower = 0;
        this.perk = "None";
    }

    Mod(String mod) {
        for(int a = 0; a < Information.Mods.length; a++) {
            if (Objects.equals(Information.Mods[a][0], mod)) {
                this.modName = Information.Mods[a][0];
                this.modWeight = Integer.parseInt(Information.Mods[a][1]);
                this.modPower = Integer.parseInt(Information.Mods[a][2]);
                this.modHp = Integer.parseInt(Information.Mods[a][3]);
                this.perk = Information.Mods[a][4];
            }
        }
    }

    public String getModName() {
        return modName;
    }

    public int getModHp() {
        return modHp;
    }

    public int getModPower() {
        return modWeight;
    }

    public int getModWeight() {
        return modPower;
    }

    public String getPerk() {
        return perk;
    }
}