package com.example.myapplication.StateAdapter;

public class Post_inSecondAdapter {
    private String name; // название
    private String date;  // дата
    private int img; // картинка

    public Post_inSecondAdapter(String name, String date , int img){

        this.name=name;
        this.date=date;
        this.img=img;
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

    public int getImg() {
        return this.img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}