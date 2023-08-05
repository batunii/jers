package com.example.ders;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Dexter {
    static HashMap<String, Integer> index = new HashMap<>();

    public static HashMap<String, Integer> giveIndex(ArrayList<String>tokens)
    {
        for(String token : tokens)
        {
            if(index.containsKey(token))
                index.replace(token, index.get(token)+1);
            else
                index.put(token, 1);

            if(index.get(token)>259) {
                token.chars().forEach(System.out::println);
                if(token.isEmpty())
                {
                    System.out.println("Yes");
                }
            }
        }
        return index;
    }
}
