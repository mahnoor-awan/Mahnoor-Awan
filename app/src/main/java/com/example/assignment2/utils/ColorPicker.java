package com.example.assignment2.utils;

public class ColorPicker {
    private static String[] color = {"#3eb9df", "#3685bc", "#d36280", "#E44F55", "#E44F55", "#E44F55","#E44F55", "#d36280"};
    private static int curretcolor = 0;

    public static String getColor() {
        curretcolor = (curretcolor + 1) % color.length;
        return color[curretcolor];
    }
}
