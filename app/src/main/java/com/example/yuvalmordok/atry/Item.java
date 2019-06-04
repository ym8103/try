package com.example.yuvalmordok.atry;

public class Item {
    public String id;
    public String name;
    public String target;

    public Item(String id, String name,String target) {
        this.id = id;
        this.name = name;
        this.target = target;

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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}


