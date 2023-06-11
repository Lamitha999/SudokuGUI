package com.example.scenebuilder;

public class Config {
    private final int width;
    private final int height;


    public Config(){
        width = 720; //setting height
        height = width*(9/16); //setting width to a 16:9 format
    }
}
