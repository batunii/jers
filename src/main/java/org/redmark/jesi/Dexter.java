package org.redmark.jesi;

import java.util.ArrayList;
import java.util.HashMap;

class Dexter {
    private final HashMap<String, HashMap<String, Integer>> fileIndex = new HashMap<>();
    private HashMap<String, Integer> giveIndex(ArrayList<String> tokens)
    {
        HashMap<String, Integer> index = new HashMap<>();

        for(String token : tokens)
        {
            if(index.containsKey(token))
                index.replace(token, index.get(token)+1);
            else
                index.put(token, 1);

        }
        return index;
    }
    void indexFile(String content, String file_name)
    {
        HashMap<String, Integer> index = giveIndex(new Alexar().tokenize(content));
        fileIndex.put(file_name,index);
    }

    HashMap<String, HashMap<String, Integer>> getFileIndex() {
        return fileIndex;
    }
}
