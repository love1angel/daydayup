package com.njupt.cn.model;

import java.io.Serializable;

public class Person implements Serializable {
    private int id = 0;
    private String name = "";
    private boolean awarded = false;
    private boolean hasAvatars = false;
    private String avatars = "";

    public Person() {
    }

    public Person(int id, String name, boolean awarded, boolean hasAvatars, String avatars) {
        this.id = id;
        this.name = name;
        this.awarded = awarded;
        this.hasAvatars = hasAvatars;
        this.avatars = avatars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAwarded() {
        return awarded;
    }

    public void setAwarded(boolean awarded) {
        this.awarded = awarded;
    }

    public boolean isHasAvatars() {
        return hasAvatars;
    }

    public void setHasAvatars(boolean hasAvatars) {
        this.hasAvatars = hasAvatars;
    }

    public String getAvatars() {
        return avatars;
    }

    public void setAvatars(String avatars) {
        this.avatars = avatars;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", awarded=" + awarded +
                ", hasAvatars=" + hasAvatars +
                ", avatars='" + avatars + '\'' +
                '}';
    }
}
