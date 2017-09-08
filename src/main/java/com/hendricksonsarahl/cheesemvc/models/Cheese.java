package com.hendricksonsarahl.cheesemvc.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Cheese {

    @NotNull // Built in Java validation not null when we are validating
    @Size(min=3,max =15) // This field has to be at least 3 char but no more then 15 char
    private String name; // Validate this field

    @NotNull
    @Size(min=1,message = "This field may not be empty")
    private String description; // Validate this field

    private CheeseType type;

    public int cheeseId;
    private static int nextId = 1;

    public Cheese(String name, String description) {
        this(); //Calls the default constructor
        this.name = name;
        this.description = description;
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