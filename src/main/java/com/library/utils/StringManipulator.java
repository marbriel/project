package com.library.utils;

import java.util.Scanner;

public class StringManipulator {

    public static String upperEveryStarts(String word){

        String[] words =word.split("\\s");
        StringBuilder newString = new StringBuilder();
        for(String w: words){
            String first = w.substring(0,1);
            String rest = w.substring(1);
            newString.append(first.toUpperCase()).append(rest).append(" ");
        }
        return newString.toString().trim();
    }
    
}
