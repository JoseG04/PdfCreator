package pdfcreator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Jose G.
 */
public class PdfCreator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        CrearPag ventana = new CrearPag();
        ventana.setVisible(true);
    }
    
    
    public static void crearBlog(String titulo, String texto) throws IOException{
        try{
        Document doc = new Document();
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("docs/doc.pdf"));
        Membrete header = new Membrete(titulo);
        writer.setPageEvent(header);
        
        doc.open();
        
        Font bodyFont = FontFactory.getFont(BaseFont.HELVETICA, 16, BaseColor.BLACK);
        
       
        Paragraph body = new Paragraph(texto, bodyFont);

        
                   
        
        doc.add(Chunk.NEWLINE);
        doc.add(body);
        
        doc.close();
        
        
        }catch(DocumentException | java.io.FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
