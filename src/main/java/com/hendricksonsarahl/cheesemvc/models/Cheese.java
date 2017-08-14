package com.hendricksonsarahl.cheesemvc.models;

public class Cheese {

    private String name;

    private String description;

    private CheeseType type;

    private int cheeseId;
    private static int nextId = 1;

    public Cheese(String name, String description, CheeseType type) {
        this();
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Cheese() {
        cheeseId = nextId;
        nextId++;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CheeseType getType() {
        return type;
    }

    public void setType(CheeseType type) {
        this.type = type;
    }
}