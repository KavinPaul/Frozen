package com.example.frozen;

public class Category {
    private String categoryImageUrl;

    private String categoryType;

    public Category() {
    }

    public String getCategoryImageUrl() {

        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }

    public String getCategoryType() {

        return categoryType;
    }

    public void setCategoryType(String categoryType) {

        this.categoryType = categoryType;
    }
}
