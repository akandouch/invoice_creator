package com.akandouch.invoicec.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.border.Border;

import com.akandouch.invoicec.domain.InvoiceProfile;
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

	private static double total = 0;
	
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
			pdf.add(addInvoiceProfile(invoice.getInvoiced()));
			pdf.add(addTable(invoice.getItems()));
			pdf.add(addInvoiceProfile(invoice.getInvoicer()));
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
	private static Paragraph addInvoiceProfile(InvoiceProfile invoiceProfile) {

		String newLine = System.getProperty("line.separator");
		Font f = new Font(KARLA_FONT_REGULAR);

		f.setSize(10);
		f.setColor(90,90,90);
		Paragraph paragraph = new Paragraph("", f);

		paragraph.setSpacingBefore(25);
		paragraph.setSpacingAfter(25);

		paragraph.add(new Phrase(invoiceProfile.getFirstname() + " " + invoiceProfile.getLastname().toUpperCase() + "\n", new Font(KARLA_FONT_BOLD)));

		paragraph.add(invoiceProfile.getVat() + "\n" );

		paragraph.add(invoiceProfile.getAddress().getStreet() + " " + invoiceProfile.getAddress().getStreetNumber() + newLine );
		paragraph.add(invoiceProfile.getAddress().getCity() + ", " + invoiceProfile.getAddress().getPostcode() + " " + invoiceProfile.getAddress().getCountry());

		return paragraph;
	}
	private static PdfPTable addTable(List<Item> items) throws DocumentException, IOException {
		System.out.println(items.size());
		PdfPTable table = new PdfPTable(6);
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
		cell.setPaddingBottom(10);
		
		cellh.setBorderColor(new BaseColor(90, 90, 90));
		//cellh.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellh.setPaddingLeft(10);
		cellh.setPaddingBottom(7);
		cellh.setPaddingTop(7);
		
		table.setWidthPercentage(100);
        table.setWidths(new int[]{3, 3, 3, 2, 2, 3});
        
        cellh.setPhrase(new Phrase("Project",fonth));table.addCell(cellh);
        cellh.setPhrase(new Phrase("Period",fonth));table.addCell(cellh);
        cellh.setPhrase(new Phrase("Natur",fonth));table.addCell(cellh);
        cellh.setPhrase(new Phrase("Days",fonth));table.addCell(cellh);
        cellh.setPhrase(new Phrase("Rate",fonth));table.addCell(cellh);
		cellh.setPhrase(new Phrase("Amount",fonth));table.addCell(cellh);

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
			total += Float.valueOf(i.getRate()) * Float.valueOf(i.getDays());
			cell.setPhrase(new Phrase("" + Float.valueOf(i.getRate()) * Float.valueOf(i.getDays()),font));
			table.addCell(cell);
		});

		/*TOTAL*/
		PdfPCell noBorder = new PdfPCell();
		noBorder.setPaddingLeft(10);
		noBorder.setPaddingTop(5);
		noBorder.setPaddingBottom(10);
		noBorder.setBorder(0);
		table.addCell(noBorder);
		table.addCell(noBorder);
		table.addCell(noBorder);
		table.addCell(noBorder);

		noBorder.setPhrase(new Phrase("SUBTOTAL",font));
		table.addCell(noBorder);
		cell.setPhrase(new Phrase(""+total,font));
		table.addCell(cell);


		/*TAXABLE*/
		noBorder.setPhrase(null);
		noBorder.setBorder(0);
		table.addCell(noBorder);
		table.addCell(noBorder);
		table.addCell(noBorder);
		table.addCell(noBorder);

		noBorder.setPhrase(new Phrase("Taxable",font));
		table.addCell(noBorder);
		cell.setPhrase(new Phrase(""+total,font));
		table.addCell(cell);

		/*VAT RATE*/
		noBorder.setPhrase(null);
		noBorder.setBorder(0);
		table.addCell(noBorder);
		table.addCell(noBorder);
		table.addCell(noBorder);
		table.addCell(noBorder);

		noBorder.setPhrase(new Phrase("Vat rate",font));
		table.addCell(noBorder);
		cell.setPhrase(new Phrase(""+total,font));
		table.addCell(cell);

		/*VAT AMOUNT*/
		noBorder.setPhrase(null);
		noBorder.setBorder(0);
		table.addCell(noBorder);
		table.addCell(noBorder);
		table.addCell(noBorder);
		table.addCell(noBorder);

		noBorder.setPhrase(new Phrase("Vat amount",font));
		table.addCell(noBorder);
		cell.setPhrase(new Phrase(""+total,font));
		table.addCell(cell);

		/*TOTAL*/
		noBorder.setPhrase(null);
		noBorder.setBorder(0);
		table.addCell(noBorder);
		table.addCell(noBorder);
		table.addCell(noBorder);
		table.addCell(noBorder);

		noBorder.setPhrase(new Phrase("TOTAL",fonth));
		table.addCell(noBorder);
		cell.setPhrase(new Phrase(""+total,fonth));
		table.addCell(cell);
		
		return table;
	}
	
	private static void createFont() throws DocumentException, IOException {
		KARLA_FONT_REGULAR = BaseFont.createFont("fonts/Karla/Karla-Regular.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
		KARLA_FONT_BOLD = BaseFont.createFont("fonts/Karla/Karla-Bold.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
		KARLA_FONT_ITALIC = BaseFont.createFont("fonts/Karla/Karla-Italic.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
		KARLA_FONT_BOLD_ITALIC = BaseFont.createFont("fonts/Karla/Karla-BoldItalic.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
	}

}
