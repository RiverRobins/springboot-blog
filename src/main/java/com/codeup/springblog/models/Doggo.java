package com.codeup.springblog.models;

import java.util.ArrayList;
import java.util.List;

public class Doggo {
    public static ArrayList<Post> reverse(List<Post> n){
        ArrayList<Post> temp = new ArrayList<>();
        for (int i = n.size() - 1; i > 0; i--) {
            temp.add(n.get(i));
        }
        return temp;
    }

    public static ArrayList<Comment> reverse(List<Comment> n, int n2){
        ArrayList<Comment> temp = new ArrayList<>();
        for (int i = n.size() - 1; i > 0; i--) {
            temp.add(n.get(i));
        }
        return temp;
    }


//    @Id
////    @Column(nullable = false, unique = true)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @Column
//    private byte age;
//    @Column
//    private String name;
//    @Column
//    private String state;
//
//    public Doggo(){}
//
//    public Doggo(int id, byte age, String name, String state) {
//        this.id = id;
//        this.age = age;
//        this.name = name;
//        this.state = state;
//    }
//
//    public Doggo(byte age, String name, String state) {
//        this.age = age;
//        this.name = name;
//        this.state = state;
//    }
//
//    public byte getAge() {
//        return age;
//    }
//
//    public void setAge(byte age) {
//        this.age = age;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
}
