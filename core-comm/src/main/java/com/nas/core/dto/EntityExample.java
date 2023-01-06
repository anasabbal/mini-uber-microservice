package com.nas.core.dto;

public class EntityExample {
    private String id;
    private String name;


    public EntityExample(String name){
        this.name = name;
    }

    public EntityExample(String id, String name) {
        this.id = id;
        this.name = name;
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
