package com.example.ders;

import java.util.ArrayList;
import java.util.Arrays;

public class Alexar {
    static ArrayList<String> tokens = new ArrayList<>();
    private static char[] charTokenizer(String str)
    {
        str = str.strip();
        System.out.println(str);
        return str.toCharArray();

    }
    public static ArrayList<String> tokenize(String content)
    {
        char[] charTokens = charTokenizer(content);
        int n = 0;
        int counter = 0;
        StringBuilder builder = new StringBuilder();
        while(n<charTokens.length)
        {
            if(!String.valueOf(charTokens[n]).isBlank()
                    && isAlphaNumeric(charTokens[n])
                    && !String.valueOf(charTokens[n]).contains("\n"))

            {
                builder.append(charTokens[n]);
            }

            else
            {
                tokens.add(builder.toString());
                builder.delete(0, builder.length()-1);
            }
            n++;
        }

        return tokens;
    }

    private static char[] slicer(int start, int end, char[] charTokens)
    {
        char[] newCharArray = new char[end-start];
        for(int i = start, n=0;  i<end; i++, n++)
        {
            newCharArray[n] = charTokens[i];
        }
        return newCharArray;
    }

    private static boolean isAlphaNumeric(char charToken)
    {
        return (charToken >= '0' & charToken <= '9') ||
                (charToken >= 'a' && charToken <= 'z') ||
                (charToken >= 'A' && charToken <= 'Z');
    }
}