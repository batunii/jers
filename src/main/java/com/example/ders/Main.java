package com.example.ders;

import java.io.File;
import java.io.FileFilter;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
//         Jesi jesi = new Jesi();
//         jesi.getIndex();

//        File directory = new File("C:\\Users\\Shrey\\Downloads");
//        File [] files = directory.listFiles(new FileFilter() {
//            @Override
//            public boolean accept(File pathname) {
//                return pathname.isFile() &&
//                        pathname.getAbsolutePath().toLowerCase().endsWith(".pdf");
//            }
//        });

//        assert files != null;
//        for (File file : files)
//        {
//            System.out.println(file.getAbsolutePath());
//        }

        //System.out.println((new Jesi()).index("C:\\Users\\Shrey\\Downloads").orElseThrow());

        Jesi jesi = new Jesi();
        jesi.index("C:\\Users\\Shrey\\Desktop\\TestPDFs");
        System.out.println(jesi.search("Wells in Fargo"));


    }
}
