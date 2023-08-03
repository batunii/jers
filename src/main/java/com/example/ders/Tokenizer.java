package com.example.ders;
//import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Tokenizer {

    public List<String> tokenizer(String parsedBody, Character ...delimiters)
    {
        String[] newArray = parsedBody.stripIndent().strip().split(" ");
        newArray = reconString(newArray).split("\n");
        
        List<String> newList = Arrays.asList(newArray);


        return newList.stream().filter(e->!e.equals(" ")).toList(); 
    }

    private String reconString(String [] newArray)
    {
        StringBuffer buffer= new StringBuffer();
        for(String ele : newArray)
        {
             
            buffer.append(ele);
        }
        return buffer.toString(); 
    }
    
}
