package com.example.ders;

import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.codelibs.jhighlight.fastutil.Hash;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Jesi {

    static HashMap<String, HashMap<String,Integer>> fileIndex = new HashMap<>();
    Dexter dexter = new Dexter();
    private String parsePDF(String filePath) throws IOException, TikaException, SAXException {
        BodyContentHandler bodyContentHandler = new BodyContentHandler();
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        InputStream stream = TikaInputStream.get(inputStream);
        Metadata data = new Metadata();
        ParseContext contex = new ParseContext();
        PDFParser parser = new PDFParser();
        parser.parse(stream, bodyContentHandler, data, contex);

        return bodyContentHandler.toString();
    }

    public void index(String folderPath) throws TikaException, IOException, SAXException {
        File directory = new File(folderPath);
        File[] files = directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile() &&
                        pathname.getAbsolutePath()
                                .toLowerCase().endsWith(".pdf");
            }
        });
        assert files != null;
        long startTime = System.nanoTime();
        for (File file : files) {
            try {
                String parsed = parsePDF(file.getAbsolutePath());
                dexter.indexFile(parsed, file.getName());
                System.out.println("Indexing file: " + file.getName() + "...");
            }
            //System.out.println(fileIndex);
            catch (Exception e) {
                System.out.println("ERROR : In reading file : " +
                        file.getName() + " due to: " + e.getMessage());
            }

        }
        System.out.println("------"+(System.nanoTime()-startTime)/1_000_000_000+"------");
        fileIndex = dexter.getFileIndex();
    }

    public Map<String, Double> search(String searchTerm)
    {
        HashMap<String, Double>score = new HashMap<>();
        ArrayList<String> tokens = (new Alexar()).tokenize(searchTerm);

            for(String fileName : fileIndex.keySet())
            {
                System.out.println(fileName);
                for(String token : tokens)
                {
                    if(fileIndex.get(fileName).containsKey(token)) {
                        double tf = tf(fileIndex.get(fileName).get(token)
                                , fileIndex.get(fileName).size());
                        double idf = idf(token);
                        double tfIdf = tf * idf;
                        score.computeIfPresent(fileName,
                                (k, v) -> v + tfIdf);
                        score.putIfAbsent(fileName, tfIdf);
                        System.out.println(token + " => "+ tfIdf);
                    }
                }
            }

        return score.entrySet().stream()
                .sorted((e1, e2)->e2.getValue()>e1.getValue()?1:e2.getValue().equals(e1.getValue())?0:-1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (v1,v2)->v2, LinkedHashMap::new));
    }

    private double tf(int t, int d )
    {
        return  (double) t/d;

    }
    private double idf(String token)
    {
        double numerator = fileIndex.size();
        double denominator = fileIndex.keySet().stream()
                .filter(e->fileIndex.get(e).containsKey(token)).count()+1;

        //System.out.println(token+"=>"+numerator + " " + denominator);

        return Math.log10(Math.max(numerator/denominator, 1));
    }

}
