package org.example;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Tesseract tesseract = new Tesseract();
        try {
//            tesseract.setDatapath("classpath://tessdata");
            tesseract.setDatapath("D:\\code\\spring-boot-demo\\demo06\\src\\main\\resources\\tessdata");
            tesseract.setLanguage("chi_sim");

            // the path of your tess data folder
            // inside the extracted file
            final File file = new File("C:\\Users\\larry.zhang\\Desktop\\11.jpg");
            String text = tesseract.doOCR(file);

            // path of your image file
            System.out.print(text);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }
}