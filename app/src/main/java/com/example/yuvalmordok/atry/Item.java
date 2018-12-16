package com.example.yuvalmordok.atry;

public class Item {
    public String id;
    public String name;

    public Item(String id, String name) {
        this.id = id;
        this.name = name;

    }

    public Item() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


