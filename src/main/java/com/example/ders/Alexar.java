package com.example.ders;

import java.util.ArrayList;
import java.util.Arrays;

public class Alexar {
    ArrayList<String> tokens = new ArrayList<>();
    private char[] charTokenizer(String str)
    {
        str = str.strip().toLowerCase();
        return str.toCharArray();

    }
    public ArrayList<String> tokenize(String content)
    {
        char[] charTokens = charTokenizer(content);
        int n = 0;
        int counter = 0;
        while(n<charTokens.length)
        {
            if(String.valueOf(charTokens[n]).isBlank()
                    || !isAlphaNumeric(charTokens[n])
                    || n==charTokens.length-1
                    || String.valueOf(charTokens[n]).equals("\n")
                    ||String.valueOf(charTokens[n]).isEmpty())

            {
                if(slicer(counter,n,charTokens).length!=0)
                    tokens.add(new String(slicer(counter, n, charTokens)));

                counter = n+1;
            }
            n++;
        }
        return tokens;
    }

    private char[] slicer(int start, int end, char[] charTokens)
    {
        char[] newCharArray = new char[end-start];
        for(int i = start, n=0;  i<end; i++, n++)
        {
            newCharArray[n] = charTokens[i];
        }
        return newCharArray;
    }

    private boolean isAlphaNumeric(char charToken)
    {
        return (charToken >= '0' & charToken <= '9') ||
                (charToken >= 'a' && charToken <= 'z') ||
                (charToken >= 'A' && charToken <= 'Z');
    }
}