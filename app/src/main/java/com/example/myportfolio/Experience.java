package com.example.myportfolio;

public class Experience {

    private String role;
    private String company;
    private String duration;
    private String description;
    private String tech;

    public Experience(String role,
                      String company,
                      String duration,
                      String description,
                      String tech) {

        this.role = role;
        this.company = company;
        this.duration = duration;
        this.description = description;
        this.tech = tech;
    }

    public String getRole() {
        return role;
    }

    public String getCompany() {
        return company;
    }

    public String getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public String getTech() {
        return tech;
    }
}
