package com.example.hcd_fresher048.recyclerviewsearchdemo;

import java.util.ArrayList;


public class MockData {
    public static ArrayList<Data> getData () {
        ArrayList<Data> datas = new ArrayList<>();

        datas.add(new Data("Apple"));
        datas.add(new Data("Orange"));
        datas.add(new Data("Banana"));
        datas.add(new Data("Grape"));
        datas.add(new Data("Mango"));
        datas.add(new Data("Pear"));
        datas.add(new Data("Pineapple"));
        datas.add(new Data("Strawberry"));
        datas.add(new Data("Coconut"));
        datas.add(new Data("Almond"));

        return datas;
    }
}

class Data {
    private String content;

    public Data(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
