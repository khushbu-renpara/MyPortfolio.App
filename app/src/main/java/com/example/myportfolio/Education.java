package com.example.myportfolio;

public class Education {

    private String title;        // e.g. Bachelor's Degree
    private String institute;    // College / School name
    private String duration;     // 2021 - 2025
    private String result;       // CGPA / Percentage

    public Education(String title, String institute, String duration, String result) {
        this.title = title;
        this.institute = institute;
        this.duration = duration;
        this.result = result;
    }

    public String getTitle() {
        return title;
    }

    public String getInstitute() {
        return institute;
    }

    public String getDuration() {
        return duration;
    }

    public String getResult() {
        return result;
    }
}
