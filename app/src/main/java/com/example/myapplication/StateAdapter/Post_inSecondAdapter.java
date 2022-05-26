package com.example.myapplication.StateAdapter;

public class Post_inSecondAdapter {
    private long id;
    private String name; // название
    private String date;  // дата
    private String img; // картинка

    public Post_inSecondAdapter(long id, String name, String date ,String img){
        this.id = id;
        this.name=name;
        this.date=date;
        this.img=img;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}