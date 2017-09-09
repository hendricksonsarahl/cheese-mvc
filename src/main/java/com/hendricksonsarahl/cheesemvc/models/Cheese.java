package com.hendricksonsarahl.cheesemvc.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Cheese {

    @NotNull // Not null validates that the user cannot submit a null field
    @Size(min=3,max =15) // This field has to be at least 3 char but no more then 15 char
    private String name;

    @NotNull
    @Size(min=1,message = "Please enter a description") // Description of any length required
    private String description;

    private CheeseType type;

    @NotNull // Not null validates that the user cannot submit a null field
    @Max(5) // Max integer that can be entered is 5. (rating from 0-5)
    private Integer rating;

    public int cheeseId;
    private static int nextId = 1;

    public Cheese(String name, String description, int rating) {
        this(); //Calls the default constructor first
        this.name = name;
        this.description = description;
        this.rating = rating;
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

    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
}