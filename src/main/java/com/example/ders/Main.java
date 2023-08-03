package com.example.ders;

import java.io.File;
import java.io.FileInputStream;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");

        BodyContentHandler bodyContentHandler = new BodyContentHandler(); 
        String filePath = "derstest\\Shreyansh Soni.pdf";
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        Metadata data = new Metadata();
        ParseContext contex = new ParseContext(); 
        PDFParser parser = new PDFParser();
        parser.parse(inputStream, bodyContentHandler, data, contex);
        //System.out.println(bodyContentHandler.toString());

        Tokenizer tokens = new Tokenizer();
        System.out.println(tokens.tokenizer(bodyContentHandler.toString(), '.').toString());


    }
}