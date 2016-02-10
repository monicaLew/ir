package com;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class PdfView extends AbstractPdfView {

    char[][][] digits;
    char[] frame = {9484, 9488, 9492, 9496, 9472, 9474};

    public PdfView() {
        digits = new char[10][15][18];
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ClassPathResource("digits.txt").getInputStream()));
            for (int digit = 0; digit < 10; digit++) {
                for (int y = 0; y < 15; y++) {
                    String buffer = bufferedReader.readLine();
                    for (int x = 0; x < 18; x++) {
                        digits[digit][y][x] = buffer.charAt(x);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        int year = (Integer) map.get("year");
        int[][][] calendar = (int[][][]) map.get("calendar");
        String[] monthNames = (String[]) map.get("monthNames");
        String[] dowNames = (String[]) map.get("dowNames");
        // empty matrix
        char matrix[][] = new char[60][78];
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                matrix[y][x] = 32;
            }
        }
        // year
        int yearDigits[] = {year / 1000 % 10, year / 100 % 10, year / 10 % 10, year % 10};
        for (int i = 0; i < yearDigits.length; i++) {
            for (int y = 0; y < 15; y++) {
                for (int x = 0; x < 18; x++) {
                    matrix[i * 15 + y][60 + x] = digits[yearDigits[i]][y][x];
                }
            }
        }
        // months
        for (int month = 0; month < 12; month++) {
            // offset calculation for each month
            int xOffset = (month % 2) * 30;
            int yOffset = (month / 2) * 10;
            // month name
            for (int i = 0; i < monthNames[month].length(); i++) {
                matrix[yOffset][xOffset + i] = monthNames[month].charAt(i);
            }
            // month frame corners
            matrix[yOffset + 1][xOffset] = frame[0];
            matrix[yOffset + 1][xOffset + 29] = frame[1];
            matrix[yOffset + 9][xOffset] = frame[2];
            matrix[yOffset + 9][xOffset + 29] = frame[3];
            // month frame horizontal
            for (int x = 0; x < 28; x++) {
                matrix[yOffset + 1][xOffset + x + 1] = frame[4];
                matrix[yOffset + 9][xOffset + x + 1] = frame[4];
            }
            // month frame vertical
            for (int y = 0; y < 7; y++) {
                matrix[yOffset + 2 + y][xOffset] = frame[5];
                matrix[yOffset + 2 + y][xOffset + 29] = frame[5];
            }
            // days of week
            for (int x = 0; x < 7; x++) {
                int l = dowNames[x].length();
                for (int i = 0; i < l; i++) {
                    matrix[yOffset + 2][xOffset + (x + 1) * 4 - i] = dowNames[x].charAt(l - i - 1);
                }
            }
            // dates
            for (int y = 0; y < 6; y++) {
                for (int x = 0; x < 7; x++) {
                    if (0 != calendar[month][y][x]) {
                        String buffer = String.valueOf(Math.abs(calendar[month][y][x]));
                        int l = buffer.length();
                        for (int i = 0; i < l; i++) {
                            matrix[yOffset + y + 3][xOffset + (x + 1) * 4 - i] = buffer.charAt(l - i - 1);
                        }
                    }
                }
            }
        }
        // final output
        BaseFont baseFont = BaseFont.createFont("courier-new.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(baseFont, 10);
        for (int y = 0; y < matrix.length; y++) {
            Paragraph paragraph = new Paragraph(String.valueOf(matrix[y]), font);
            paragraph.setLeading(10);
            document.add(paragraph);
        }
    }

}
