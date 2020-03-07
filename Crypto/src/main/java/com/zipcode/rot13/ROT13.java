package com.zipcode.rot13;

import java.io.*;
import java.util.Scanner;

public class ROT13  {

    String rotateString = "abcdefghijklmnopqrstuvwxyz";
    Integer charDiff = 0;
    ROT13(Character cs, Character cf) {
        charDiff = cf.charValue() - cs.charValue();
    }

    ROT13() {
    }


    public String crypt(String text) throws UnsupportedOperationException {
        String retString = encrypt(text);
        return retString;
    }

    public String encrypt(String text) {
        StringBuilder retString = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            retString.append(getCharFromAlphabet(rotateString, text.charAt(i), charDiff));
        }
        return retString.toString();
    }

    public String decrypt(String text) {
        return encrypt(text);
    }

    public String rotate(String s, Character c) {
        charDiff = c.charValue() - s.charAt(0);
        rotateString = s;
        String retString = encrypt(s);
        return retString;
    }

    public Character getCharFromAlphabet(String rotateString, Character checkChar, Integer rotateBy){
        if(checkChar.charValue() < 65 || (checkChar.charValue() > 90 && checkChar.charValue() < 97) ||
                checkChar.charValue() > 122)
            return checkChar;

        Character retChar = null;
        Boolean isUpper = false;
        if(checkChar.charValue() >= 65 && checkChar.charValue() <= 90) {
            isUpper = true;
        }
        String lowChar = checkChar.toString().toLowerCase();
        for (int i = 0; i < rotateString.length(); i++) {
            Character alphaChar = rotateString.toLowerCase().charAt(i);
            if(lowChar.charAt(0) == (alphaChar)) {
                retChar = rotateString.charAt((i + rotateBy + rotateString.length()) % rotateString.length());
                break;
            }
        }
        if(isUpper)
            retChar = retChar.toString().toUpperCase().charAt(0);
        return retChar;
    }

    public void encryptTextFile(File f) throws IOException {
        String nonEncrypt = new Scanner(f)
                .useDelimiter("\\Z").next();
        File encrypted = new File("/Users/vle/Documents/Projects/Week5/SimpleCrypt 3.7/SimpleCrypt/sonnet18.enc");
        charDiff = 13;
        FileWriter myWriter = new FileWriter(encrypted);
        myWriter.write(encrypt(nonEncrypt));
        myWriter.close();
    }
}
