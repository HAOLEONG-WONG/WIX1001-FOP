package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PdfGenerator {

    public static String generateStudentPdf(String studentInfo) {
        String filePath = "student_info.pdf"; // PDF 文件路径
        PDDocument document = new PDDocument();

        try {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Student Information:");
            contentStream.setFont(PDType1Font.HELVETICA, 12);

            // 将学生信息按行输出
            String[] lines = studentInfo.split("\n");
            for (String line : lines) {
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText(line);
            }

            contentStream.endText();
            contentStream.close();

            document.save(filePath);
            System.out.println("PDF file generated successfully: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }

}
