package com.example.myportfolio;

public class Project {

    private String name;
    private String description;
    private String tech;
    private String link;
    private String duration;

    public Project(String name,
                   String description,
                   String tech,
                   String link,
                   String duration) {

        this.name = name;
        this.description = description;
        this.tech = tech;
        this.link = link;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTech() {
        return tech;
    }

    public String getLink() {
        return link;
    }

    public String getDuration() {
        return duration;
    }
}
