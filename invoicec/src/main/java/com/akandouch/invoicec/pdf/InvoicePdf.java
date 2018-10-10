package com.akandouch.invoicec.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.border.Border;

import org.apache.commons.io.IOUtils;

import com.akandouch.invoicec.domain.Invoice;
import com.akandouch.invoicec.domain.Item;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfDiv.BorderTopStyle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class InvoicePdf {
	
	private static BaseFont KARLA_FONT_REGULAR;
	private static BaseFont KARLA_FONT_BOLD;
	private static BaseFont KARLA_FONT_ITALIC;
	private static BaseFont KARLA_FONT_BOLD_ITALIC;
	
	static {
		try {
			createFont();
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static byte[] generateInvoicePdf(Invoice invoice) {
		
		File tmp = new File("C:\\workspace\\" + invoice.getTitle() + "_gen.pdf" );
		byte[] b = new byte[1024];
		try(FileOutputStream fos = new FileOutputStream(tmp)){
			Document pdf = new Document();
			PdfWriter.getInstance(pdf, fos);
			pdf.open();
			pdf.add(new Paragraph("Invoice",new Font(KARLA_FONT_REGULAR)));
			pdf.add(addInvoicer(invoice));
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
	private static Paragraph addInvoicer(Invoice invoice) {
		Paragraph paragraph = new Paragraph("invoicer", new Font(KARLA_FONT_REGULAR));
		paragraph.add("Zaide");
		paragraph.add("Akandouch");
		return paragraph;
	}
	private static PdfPTable addTable(List<Item> items) throws DocumentException, IOException {
		System.out.println(items.size());
		PdfPTable table = new PdfPTable(5);
		PdfPCell cell = new PdfPCell();
		PdfPCell cellh = new PdfPCell();
		Font font = new Font(KARLA_FONT_REGULAR);
		Font fonth = new Font(KARLA_FONT_BOLD);


		fonth.setSize(9);
		fonth.setFamily("Arrial");
		fonth.setColor(90,90,90);
		
		font.setSize(8);
		font.setFamily("Arrial");
		font.setColor(110, 110, 110);
		
		cell.setBorderWidth((float)0.1);
		cell.setBorderColor(new BaseColor(90, 90, 90));
		cell.setPaddingLeft(10);
		cell.setPaddingTop(5);
		cell.setPaddingBottom(5);
		
		cellh.setBorderColor(new BaseColor(90, 90, 90));
		//cellh.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellh.setPaddingLeft(10);
		cellh.setPaddingBottom(7);
		cellh.setPaddingTop(7);
		
		table.setWidthPercentage(100);
        table.setWidths(new int[]{3, 3, 3, 3, 3});
        
        cellh.setPhrase(new Phrase("Project",fonth));table.addCell(cellh);
        cellh.setPhrase(new Phrase("Period",fonth));table.addCell(cellh);
        cellh.setPhrase(new Phrase("Natur",fonth));table.addCell(cellh);
        cellh.setPhrase(new Phrase("Days",fonth));table.addCell(cellh);
        cellh.setPhrase(new Phrase("Rate",fonth));table.addCell(cellh);
        
		items.forEach(i->{
			cell.setPhrase(new Phrase(i.getProject(),font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase(i.getPeriod().toString(),font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase(i.getNature(),font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("" + i.getDays(),font));
			table.addCell(cell);
			
			cell.setPhrase(new Phrase("" + i.getRate(),font));
			table.addCell(cell);
		});
		
		return table;
	}
	
	private static void createFont() throws DocumentException, IOException {
		KARLA_FONT_REGULAR = BaseFont.createFont("fonts/Karla/Karla-Regular.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
		KARLA_FONT_BOLD = BaseFont.createFont("fonts/Karla/Karla-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
		KARLA_FONT_ITALIC = BaseFont.createFont("fonts/Karla/Karla-Italic.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
		KARLA_FONT_BOLD_ITALIC = BaseFont.createFont("fonts/Karla/Karla-BoldItalic.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	}

}
