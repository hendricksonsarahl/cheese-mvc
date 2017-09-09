package com.hendricksonsarahl.cheesemvc.models;

public enum CheeseType {

    HARD ("Hard"),
    SOFT ("Soft"),
    FAKE ("Fake");

    // once initialized, we don't want the type to change. String name = the display name, i.e. "Hard"
    private final String name;

    // constructor to initialize enum list
    CheeseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
        }

    }
