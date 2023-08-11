package com.example.ders;

public class Main {
    public static void main(String[] args){
        Jesi jesi = new Jesi();
        jesi.index(args[0]);
        System.out.println(jesi.search(args[1]));


    }
}
