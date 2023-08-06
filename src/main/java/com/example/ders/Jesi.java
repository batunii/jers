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
import java.util.HashMap;
import java.util.Optional;

public class Jesi {

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

    public Optional<HashMap<String, HashMap<String, Integer>>> index(String folderPath) throws TikaException, IOException, SAXException {
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
                (new Dexter()).indexFile(parsed, file.getName());
            }
            return Optional.ofNullable(Dexter.getFileIndex());
        }
        catch (Exception e)
        {
            System.out.println("ERROR : In reading files due to: "+e.getMessage());
        }
        return Optional.empty();
    }

    public HashMap<Integer, String> search(String searchTerm)
    {
        //todo
        return null;
    }
}
