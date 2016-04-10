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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class PdfView extends AbstractPdfView {

    private char[] frame = {9484, 9488, 9492, 9496, 9472, 9474};

    private char[][][] digits;

    public PdfView() {
        digits = new char[10][15][18];
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ClassPathResource("digits.txt").getInputStream()))) {
            for (int digit = 0; digit < 10; digit++) {
                for (int y = 0; y < 15; y++) {
                    String buffer = bufferedReader.readLine();
                    for (int x = 0; x < 18; x++) {
                        digits[digit][y][x] = buffer.charAt(x);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        int year = (int) map.get("year");
        byte[][][] calendar = (byte[][][]) map.get("calendar");
        String[] dowNames = (String[]) map.get("dowNames");
        String[] monthNames = (String[]) map.get("monthNames");
        String[] rows = getCalendarMatrix(year, calendar, dowNames, monthNames, "2x6");
        BaseFont baseFont = BaseFont.createFont("courier-new.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(baseFont, 10);
        for (String row: rows) {
            Paragraph paragraph = new Paragraph(row, font);
            paragraph.setLeading(10);
            document.add(paragraph);
        }
    }

    private String[] getCalendarMatrix(int year, byte[][][] calendar, String[] dowNames, String[] monthNames, String layout) {
        char[][] matrix, submatrix;
        int offsetX, offsetY, columnCount;
        int[] yearDigits = {year / 1000 % 10, year / 100 % 10, year / 10 % 10, year % 10};
        switch (layout.toLowerCase()) {
            case "4x3":
                matrix = new char[45][120];
                columnCount = 4;
                break;
            case "3x4":
                matrix = new char[55][90];
                columnCount = 3;
                break;
            default:
                matrix = new char[60][78];
                columnCount = 2;
        }
        for (int i = 0; i < 4; i++) {
            submatrix = digits[yearDigits[i]];
            offsetX = (2 == columnCount) ? 60 : ((3 == columnCount) ? 9 : 24) + i * 18;
            offsetY = (2 == columnCount) ? i * 15 : 0;
            for (int y = 0; y < 15; y++) {
                System.arraycopy(submatrix[y], 0, matrix[offsetY + y], offsetX, 18);
            }
        }
        if (2 != columnCount) {
            int l = (3 == columnCount) ? 9 : 24;
            for (int y = 0; y < 15; y++) {
                for (int x = 0; x < l; x++) {
                    matrix[y][x] = '.';
                    matrix[y][l + 72 + x] = '.';
                }
            }
        }
        submatrix = new char[10][30];
        submatrix[1][0] = frame[0];
        submatrix[1][29] = frame[1];
        submatrix[9][0] = frame[2];
        submatrix[9][29] = frame[3];
        for (int x = 1; x < 29; x++) {
            submatrix[1][x] = frame[4];
            submatrix[9][x] = frame[4];
        }
        for (int y = 2; y < 9; y++) {
            submatrix[y][0] = frame[5];
            submatrix[y][29] = frame[5];
        }
        for (int i = 0; i < 7; i++) {
            System.arraycopy(String.format("%4s", dowNames[i]).toCharArray(), 0, submatrix[2], i * 4 + 1, 4);
        }
        for (int month = 0; month < 12; month++) {
            System.arraycopy(String.format("%-30s", monthNames[month]).toCharArray(), 0, submatrix[0], 0, 30);
            for (int y = 0; y < 6; y++) {
                for (int x = 0; x < 7; x++) {
                    System.arraycopy(((0 != calendar[month][y][x]) ? String.format("%4d", calendar[month][y][x]) : "    ").toCharArray(), 0, submatrix[y + 3], x * 4 + 1, 4);
                }
            }
            offsetX = (month % columnCount) * 30;
            offsetY = ((2 != columnCount) ? 15 : 0) + (month / columnCount) * 10;
            for (int y = 0; y < 10; y++) {
                System.arraycopy(submatrix[y], 0, matrix[offsetY + y], offsetX, 30);
            }
        }
        String[] result = new String[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            result[i] = String.valueOf(matrix[i]);
        }
        return result;
    }

}
