package org.example;

public class Robot {
    private Slot[] parts;
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
            parts = new Slot[6];
        }
    }

    public void addPart(String name, int rank, String location, int modCount, int slot) {
        parts[slot] = new Slot(name, rank, location, modCount);
    }

    public String getInformation() {
        return name + " - Type: " + type + "\n";
    }

    public void getPartsList() {
        int a = 1;
        for (Slot part : parts) {
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

    public Slot targetPart(int slot){
        return parts[slot];
    }

    public void modPart(Slot part, String name){
        part.addMod(name);
        currentSlots++;
    }

    public void removePart(int slot) {
        parts[slot] = null;
    }
}

class Slot{
    private String part;
    private String rank;
    private String location;
    private int slots;
    private String[] mods;

    public String getPart() {
        return location + " " + part + " Rank: " + rank;
    }

    Slot(String part, int rank, String location, int modCount){
        setPart(part, rank, location, modCount);
    }

    public void setPart(String part, int rank, String location, int modCount) {
        this.part = part;
        this.rank = "Rank: " + rank;
        this.slots = modCount;
        mods = new String[this.slots];
        this.location = location;
    }

    public void setPart(String part){
        this.part = part;
    }

    public void addMod(String name) {
        int count = 0;
        for (String slot : mods) {
            if (slot.isBlank()) {
                slot = name;
                break;
            }
            else{
                count++;
            }
        }
        if (count == this.slots) {
            System.out.println("All slots are full.");
        }
    }

    public void getMods() {
        for (String slot : mods) {
            if (slot == null) {
                System.out.println("Empty mod slot");
            }
            else {
                System.out.println(slot);
            }
        }
    }
}