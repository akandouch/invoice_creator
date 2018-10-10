package com.akandouch.invoicec;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableHeader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableMongoRepositories
public class App
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    	/*
    	
    	File file = new File("C:\\\\workspace\\\\test.pdf");
    	
		try(FileOutputStream os = new FileOutputStream(file)) {
			Document pdf = new Document();
			PdfWriter.getInstance(pdf, os);
			pdf.open();
			Rectangle r = new Rectangle(150, 150);
			PdfPTableHeader tableh = new PdfPTableHeader();
			PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(60);
            table.setWidths(new int[]{3, 3, 3, 3, 3});
			table.addCell("test");
			table.addCell("test");
			table.addCell("test");
			table.addCell("test");
			table.addCell("test");
	    	pdf.add(table);
	    	pdf.close();
		} catch (FileNotFoundException fileEx) {
			System.out.println("1 : " + fileEx.getMessage());
		} catch (DocumentException docEx) {
			System.out.println("2 : " + docEx.getMessage());
		} catch (IOException e) {
			System.out.println("3 : " + e.getMessage());
		}*/
    }
}
