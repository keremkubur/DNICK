package com.example.javacademy.Service;

import com.itextpdf.html2pdf.HtmlConverter;

import java.io.File;
import java.io.IOException;

public class GeneratePDF {

    public static void main(String[] args) throws IOException {

        HtmlConverter.convertToPdf(new File("./certificatepdf.html"),new File("demo-html.pdf"));
    }
}
