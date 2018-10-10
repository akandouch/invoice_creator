package com.akandouch.invoicec.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.akandouch.invoicec.domain.Invoice;
import com.akandouch.invoicec.domain.Item;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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
			pdf.add(addTable(invoice.getItems()));
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
		tmp.delete();
		return b;
	}
	private static PdfPTable addTable(List<Item> items) throws DocumentException {
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(60);
        table.setWidths(new int[]{3, 3, 3, 3, 3, 3});
		items.forEach(i->{
			table.addCell(i.getProject());
			table.addCell(i.getPeriod().toString());
			table.addCell(i.getNature());
			table.addCell("" + i.getDays());
			table.addCell("" + i.getRate());
		});
		
		return table;
	}

}
