package pdfcreator;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Jose G.
 */
public class Membrete extends  PdfPageEventHelper{
    
    private String titulo;
    
    public Membrete(String titulo) {
        this.titulo = titulo;
    }
   
    @Override
    public void onStartPage(PdfWriter writer, Document document){
        Font titleFont = FontFactory.getFont(BaseFont.HELVETICA_BOLD, 24, BaseColor.BLACK);
        Font headerFont = FontFactory.getFont(BaseFont.TIMES_ITALIC, 14, BaseColor.BLACK);
        
        Paragraph header = new Paragraph("Creado con: Libreria iText", headerFont);
        Paragraph numPage = new Paragraph( "Pág " + (String.valueOf(document.getPageNumber())), headerFont);
        Paragraph title = new Paragraph(titulo, titleFont);
        
        
        Image imagen = null;
        try {
            imagen = Image.getInstance("images/book.png");
            imagen.scalePercent(5, 5);
            imagen.setAbsolutePosition(535, 775);
        } catch (BadElementException | IOException ex) {
            Logger.getLogger(Membrete.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        DottedLineSeparator dottedline = new DottedLineSeparator();
        dottedline.setOffset(-5);
        dottedline.setGap(3f);
        title.add(dottedline);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        
        title.add(dottedline);
        numPage.setAlignment(Paragraph.ALIGN_CENTER);
        
        try {
            document.add(numPage);
            document.add(Chunk.NEWLINE);
            document.add(imagen);
            document.add(header);
            document.add(Chunk.NEWLINE);
            document.add(title);
        } catch (DocumentException ex) {
            Logger.getLogger(Membrete.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     
    }
    
    
}
