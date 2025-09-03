package org.example;

public class Article {
    private int id;
    private String title;
    private String body;
    private String date;
    private String newDate;

    public Article(int id, String title, String body, String date, String newDate) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;
        this.newDate = newDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNewDate() {
        return newDate;
    }

    public void setNewDate(String newDate) {
        this.newDate = newDate;
    }
}
