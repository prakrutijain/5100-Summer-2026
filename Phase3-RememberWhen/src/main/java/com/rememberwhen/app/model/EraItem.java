package com.rememberwhen.app.model;

// Represents one nostalgia data point: a movie, song, toy, or candy tied to a specific year/country
public class EraItem {

    private String title;         // e.g. "Titanic"
    private String category;      // e.g. "Movie", "Music", "Toy", "Candy"
    private int year;             // e.g. 1997
    private String country;       // e.g. "India", "USA"
    private String imageUrl;      // poster/cover image link
    private double eraMatchScore; // how strongly this item matches the user's era (0-100)

    // Empty constructor - Spring Boot needs this to convert JSON <-> Java objects
    public EraItem() {
    }

    // Constructor to quickly create a fully-filled EraItem
    public EraItem(String title, String category, int year, String country, String imageUrl, double eraMatchScore) {
        this.title = title;
        this.category = category;
        this.year = year;
        this.country = country;
        this.imageUrl = imageUrl;
        this.eraMatchScore = eraMatchScore;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getEraMatchScore() {
        return eraMatchScore;
    }

    public void setEraMatchScore(double eraMatchScore) {
        this.eraMatchScore = eraMatchScore;
    }
}