package com.zipcode.rot13;

import java.io.*;
import java.util.Scanner;

public class ROT13  {

    Integer charDiff;
    ROT13(Character cs, Character cf) {
        charDiff = cf.charValue() - cs.charValue();
    }

    ROT13() {
        charDiff = 13;
    }


    public String crypt(String text) throws UnsupportedOperationException {
        String retString = encrypt(text);
        return retString;
    }

    public String encrypt(String text) {
        StringBuilder retString = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            Character curChar = text.charAt(i);
            if(curChar.charValue() > 64 && curChar.charValue() < 91){
                if((curChar.charValue() + charDiff) > 90)
                    curChar = (char)(curChar + charDiff - 26);
                else
                    curChar = (char)(curChar + charDiff);
            }else if(curChar.charValue() > 96 && curChar.charValue() < 123){
                if((curChar.charValue() + charDiff) > 122)
                    curChar = (char)(curChar + charDiff - 26);
                else
                    curChar = (char)(curChar + charDiff);
            }
            retString.append(curChar);
        }
        return retString.toString();
    }

    public String decrypt(String text) {
        return encrypt(text);
    }

    public String rotate(String s, Character c) {
        StringBuilder retString = new StringBuilder();
        retString.append(s, s.indexOf(c), s.length());
        retString.append(s, 0, s.indexOf(c));
        return retString.toString();
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
