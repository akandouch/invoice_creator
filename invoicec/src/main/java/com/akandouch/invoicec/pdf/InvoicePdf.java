package com.akandouch.invoicec.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.akandouch.invoicec.domain.Invoice;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class InvoicePdf {
	
	
	public static byte[] generateInvoicePdf(Invoice invoice) {
		
		File tmp = new File("C:\\workspace\\" + invoice.getTitle() + "_gen.pdf" );
		byte[] b = new byte[1024];
		try(FileOutputStream fos = new FileOutputStream(tmp)){
			Document pdf = new Document();
			PdfWriter.getInstance(pdf, fos);
			pdf.open();
			pdf.add(new Paragraph("Invoice"));
			pdf.close();
			
		}catch( IOException ioe ) {
			System.out.println("error : " + ioe.getMessage());
		}catch( DocumentException de ) {
			System.out.println("error : " + de.getMessage());			
		}
		try(FileInputStream fis = new FileInputStream(tmp)){
			b=IOUtils.toByteArray(fis);
		}catch(IOException ioe){
			System.out.println("error : " + ioe.getMessage());			
		}
		return b;
	}

}
