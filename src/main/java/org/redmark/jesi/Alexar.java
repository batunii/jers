package org.redmark.jesi;

import java.util.ArrayList;

class Alexar {
    ArrayList<String> tokens = new ArrayList<>();
    private char[] charTokenizer(String str)
    {
        str = str.strip().toLowerCase();
        return str.toCharArray();

    }
    ArrayList<String> tokenize(String content)
    {
        char[] charTokens = charTokenizer(content);
        int n = 0;
        int counter = 0;
        while(n<charTokens.length)
        {
            if(!isAlphaNumeric(charTokens[n]))
            {
                if(slicer(counter,n,charTokens).length!=0)
                    tokens.add(new String(slicer(counter, n, charTokens)));

                counter = n+1;
            }
            else if(n==charTokens.length-1)
            {
                if(slicer(counter,n+1,charTokens).length!=0)
                    tokens.add(new String(slicer(counter, n+1, charTokens)));

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