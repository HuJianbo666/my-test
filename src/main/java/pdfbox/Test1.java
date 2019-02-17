package pdfbox;

import com.itextpdf.text.pdf.PdfReader;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.fdf.FDFDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceCharacteristicsDictionary;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Test1 {

    public static void main(String[] args) throws IOException {
        PDDocument pdf = PDDocument.load(new File("D:\\projects\\my\\my-test\\src\\main\\resource\\pdfs\\签署合同模板一.pdf"));

        PDDocumentCatalog docCatalog = pdf.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();

        PDFont font = PDType0Font.load(pdf, new File("C:/WINDOWS/Fonts/SIMYOU.TTF"));
        PDResources resources = new PDResources();
        resources.put(COSName.getPDFName("签署合同模板一"), font);
        acroForm.setDefaultResources(resources);

        PDTextField field = (PDTextField)acroForm.getField("clientA.name");
        if (field != null) {
            String defaultAppearance = "/Helv 12 Tf 0 0 1 rg";
            field.setDefaultAppearance(defaultAppearance);
            field.setValue("多得多");
        }

        pdf.save("D:\\projects\\my\\my-test\\src\\main\\resource\\pdfs\\签署合同模板一-copy.pdf");

    }

    public static void main1(String[] args) throws Exception{
        PDDocument pdDocument = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        pdDocument.addPage(page);

        PDAcroForm form = new PDAcroForm(pdDocument);
        pdDocument.getDocumentCatalog().setAcroForm(form);

        PDFont font = PDType0Font.load(pdDocument, new File("C:/WINDOWS/Fonts/SIMYOU.TTF"));
        PDResources resources = new PDResources();
        resources.put(COSName.getPDFName("Helv"), font);
        form.setDefaultResources(resources);

        PDTextField textField = new PDTextField(form);
        textField.setPartialName("SampleField");

        String defaultAppearance = "/Helv 12 Tf 0 0 1 rg";
        textField.setDefaultAppearance(defaultAppearance);

        form.getFields().add(textField);

        PDAnnotationWidget widget = textField.getWidgets().get(0);
        PDRectangle rect = new PDRectangle(50, 750, 200, 50);
        widget.setRectangle(rect);
        widget.setPage(page);

        PDAppearanceCharacteristicsDictionary fieldAppearance = new PDAppearanceCharacteristicsDictionary(new COSDictionary());
        fieldAppearance.setBorderColour(new PDColor(new float[]{0,1,0}, PDDeviceRGB.INSTANCE));
        fieldAppearance.setBackground(new PDColor(new float[]{1,1,0}, PDDeviceRGB.INSTANCE));
        widget.setAppearanceCharacteristics(fieldAppearance);

        widget.setPrinted(true);

        page.getAnnotations().add(widget);

        textField.setValue("收拾收拾");

        pdDocument.save("d:/temp/sample_form.pdf");
        pdDocument.close();
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
