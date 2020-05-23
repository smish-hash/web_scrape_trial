package com.example.web_scrape_trial;

public class ParseItem {
    private String imgUrl;
    private  String title;
    private String year;

    public ParseItem() {
    }

    public ParseItem(String imgUrl, String title,String year) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
