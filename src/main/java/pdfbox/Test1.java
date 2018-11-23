package pdfbox;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.fdf.FDFDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Test1 {

    public static void main(String[] args) throws IOException{
        PDDocument pdf = PDDocument.load(new File("D:\\projects\\my\\my-test\\src\\main\\resource\\pdfs\\签署合同模板一.pdf"));
        PDDocumentCatalog docCatalog =
                pdf.getDocumentCatalog();
        PDAcroForm acroForm =
                docCatalog.getAcroForm();
        PDField field =
                acroForm.getField("clientA.name");
        if (field != null) {
            field.getWidgets().get(0).setHidden(false);
            field.setValue("1111");
        }
        pdf.save("D:\\projects\\my\\my-test\\src\\main\\resource\\pdfs\\签署合同模板一-copy.pdf");

//        PDDocument pdf =
//                PDDocument.load( new File("D:\\projects\\my\\my-test\\src\\main\\resource\\pdfs\\签署合同模板一-copy.pdf"));
//        PDDocumentCatalog docCatalog =
//                pdf.getDocumentCatalog();
//        PDAcroForm acroForm =
//                docCatalog.getAcroForm();
//        FDFDocument fdf = acroForm.exportFDF();
//        fdf.save( "D:\\projects\\my\\my-test\\src\\main\\resource\\pdfs\\签署合同模板一-copy2.pdf" );
    }

    public void createHelloPDF() {
        PDDocument doc = null;
        PDPage page = null;

        try {
            doc = new PDDocument();
            page = new PDPage();
            doc.addPage(page);
            PDFont font = PDType1Font.HELVETICA_BOLD;
            PDPageContentStream content = new PDPageContentStream(doc, page);
            content.beginText();
            content.setFont(font, 12);
            content.moveTextPositionByAmount(100, 700);
            content.drawString("hello");

            content.endText();
            content.close();
            doc.save("D:\\projects\\my\\my-test\\src\\main\\resource\\pdfs\\pdfbox.pdf");
            doc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void readPDF() {
        PDDocument helloDocument = null;
        try {
            helloDocument = PDDocument.load(new File(
                    "F:\\java56班\\eclipse-SDK-4.2-win32\\pdfwithText.pdf"));
            PDFTextStripper textStripper = new PDFTextStripper();
            System.out.println(textStripper.getText(helloDocument));

            helloDocument.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
