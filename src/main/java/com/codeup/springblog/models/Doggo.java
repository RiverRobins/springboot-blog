package com.codeup.springblog.models;
import javax.persistence.*;

@Entity
public class Doggo {
    @Id
    private int id;
    private byte age;
    private String name;
    private String state;

    public Doggo(){}

    public Doggo(int id, byte age, String name, String state) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.state = state;
    }

    public Doggo(byte age, String name, String state) {
        this.age = age;
        this.name = name;
        this.state = state;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}