package com.example.ders;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Jesi {

    static HashMap<String, HashMap<String,Integer>> fileIndex = new HashMap<>();
    Dexter dexter = new Dexter();
    private String parsePDF(String filePath) throws IOException, TikaException, SAXException {
        BodyContentHandler bodyContentHandler = new BodyContentHandler();
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        Metadata data = new Metadata();
        ParseContext contex = new ParseContext();
        PDFParser parser = new PDFParser();
        parser.parse(inputStream, bodyContentHandler, data, contex);

        return bodyContentHandler.toString();
    }

    public void index(String folderPath) throws TikaException, IOException, SAXException {
        File directory = new File(folderPath);
        File [] files = directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile() &&
                        pathname.getAbsolutePath()
                                .toLowerCase().endsWith(".pdf");
            }});
        assert files != null;
        try {
            for (File file : files) {
                String parsed = parsePDF(file.getAbsolutePath());
                dexter.indexFile(parsed, file.getName());
                System.out.println("Indexing file: "+file.getName()+"...");
            }
           fileIndex = dexter.getFileIndex();
            //System.out.println(fileIndex);
        }
        catch (Exception e)
        {
            System.out.println("ERROR : In reading files due to: "+e.getMessage());
        }

    }

    public HashMap<String, Integer> search(String searchTerm)
    {
        HashMap<String, Integer>score = new HashMap<>();
        ArrayList<String> tokens = (new Alexar()).tokenize(searchTerm);
        for(String token : tokens)
        {
            System.out.println(token);
            for(String fileName : fileIndex.keySet())
            {
                if(fileIndex.get(fileName).containsKey(token))
                {
                    score.computeIfPresent(fileName,
                            (k,v)-> v+fileIndex.get(fileName).get(token));
                    score.putIfAbsent(fileName,fileIndex.get(fileName).get(token));
                }
            }
        }

        return score;
    }

    private double tf()
    {
        // TODO: 07-08-2023 Add a TF function which returns the TF
        return 0;
    }
    private double idf()
    {
        // TODO: 07-08-2023 Add the IDF function which calculates the IDF part!
        return 0;
    }
}
