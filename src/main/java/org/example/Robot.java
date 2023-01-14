package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Robot {
    private Part[] parts;
    private String type;
    private String name;
    private int maxSlots;
    private int currentSlots;

    Robot(String name, String type) {
        this.name = name;
        this.type = type;
        if (type.contains("bipedal") || type.contains("I")) {
            this.maxSlots = 6;
            this.currentSlots = 0;
            parts = new Part[6];
        }
    }

    public void addPart(String name, int modelIndex, int rank, String[] mods, int slot) {
        // add interaction with current slots later ig
        parts[slot] = new Part(name, rank, modelIndex, mods);
    }

    public void addPart(Part part, int slot) {
        parts[slot] = part;
    }

    public String getInformation() {
        return name + " - Type: " + type + "\n";
    }

    public void getPartsList() {
        int a = 1;
        for (Part part : parts) {
            try {
                System.out.println("Slot " + a + ":  " + part.getPart());
            }
            catch(Exception e) {
                System.out.println("Slot " + a + ":  Nothing Installed.");
            }

            a++;
        }
    }

    public int getNumParts() {
        return maxSlots;
    }

    public Part targetPart(int slot){
        return parts[slot];
    }

    public void addModPart(Part part, String name){
        part.addMod(name);
    }

    public void removeModPart(Part part, int mod) {
        part.removeMod(mod);
    }

    public void removePart(int slot) {
        Part p = parts[slot];
        Inventory.frameAParts.add(p);
        parts[slot] = null;
    }
}

class Part{
    private String set;
    private String model;
    private Mod[] modList;
    private int modCount;
    private int rank;

    Part(String set, int modelIndex, int rank) {
        this.set = set;
        this.model = Information.setModel[Arrays.asList(Information.setNames).indexOf(this.set)][0][modelIndex];
        this.modCount = Integer.parseInt(Information.setModel[Arrays.asList(Information.setNames).indexOf(this.set)][1][modelIndex]);
        this.modList = new Mod[this.modCount];
        for(Mod m : modList) {
            m = addMod();
        }
        this.rank = rank;
    }

    Part(String set, int modelIndex, int rank, String[] mods) {
        this.set = set;
        this.model = Information.setModel[Arrays.asList(Information.setNames).indexOf(this.set)][0][modelIndex];
        this.modCount = Integer.parseInt(Information.setModel[Arrays.asList(Information.setNames).indexOf(this.set)][1][modelIndex]);
        this.modList = new Mod[this.modCount];
        int a = 0;
        // try to fill the part with mods given, but if not possible, fill with a blank mod.
        for(Mod m : modList) {
            try {
                m = addMod(mods[a]);
            }
            catch(Exception e) {
                m = addMod();
            }
            a++;
        }
        this.rank = rank;
    }

    public String getPart() {
        return "Rank " + getRank() + " " + getSet() + " " + getModel() + " w/" + getSlotCount() + " slots.";
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
        String temp = "";
        for(Mod m : this.modList) {
            try {
                temp = temp + m.getModName() + "\n";
            }
            catch(Exception e) {

            }
        }
        return temp;
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
        return "Aggregated HP: " + hp + " Aggregated Power: " + power + " Aggregated Weight" + weight + "List of Mods: " + modNames + " And their associated perks: " + modPerks;
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
            if (Information.Mods[a][0] == mod) {
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