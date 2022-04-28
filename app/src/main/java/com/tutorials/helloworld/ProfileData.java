package com.tutorials.helloworld;

public class ProfileData {
    String name;
    String occupation;
    int age;
    String description;

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public int getAge() {
        return age;
    }

    public String getDescription() {
        return description;
    }

    public ProfileData(String name, int age, String occupation, String description){
        this.name = name;
        this.age = age;
        this.occupation = occupation;
        this.description = description;
    }
}
