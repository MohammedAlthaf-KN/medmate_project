package com.example.medmate;

public class healthcaremodel {
    String id,title,tips,description;

    public healthcaremodel(String id, String title, String tips, String description) {
        this.id = id;
        this.title = title;
        this.tips = tips;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTips() {
        return tips;
    }

    public String getDescription() {
        return description;
    }
}
