package ManagerClasses;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;


import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Abdul Rehman
 * */

public class ProduceReportsOnDocx {

    public void ProduceReports(String UserName, String UserID, String Title, String FileName, ResultSet ResultSet) {

        try {
            XWPFDocument doc = new XWPFDocument();

            // Set the page layout to landscape
            CTDocument1 ctDocument = doc.getDocument();
            CTBody ctBody = ctDocument.getBody();
            CTSectPr ctSectPr = ctBody.addNewSectPr();
            CTPageSz ctPageSz = ctSectPr.addNewPgSz();
            ctPageSz.setOrient(STPageOrientation.LANDSCAPE);
            ctPageSz.setW(BigInteger.valueOf(842 * 20)); // Set the width to A4 landscape

            // create paragraph for the title
            XWPFParagraph para = doc.createParagraph();
            para.setAlignment(ParagraphAlignment.CENTER);
            para.setVerticalAlignment(TextAlignment.CENTER);
            para.setSpacingAfter(200);
            // create run for the title
            XWPFRun run = para.createRun();
            run.setFontSize(44);
            run.setText(Title);

            // create paragraph for the user name, ID, and date
            XWPFParagraph para2 = doc.createParagraph();
            para2.setAlignment(ParagraphAlignment.CENTER);
            para2.setVerticalAlignment(TextAlignment.CENTER);
            para2.setSpacingAfter(200);

            // create run for the user name and ID
            XWPFRun run2 = para2.createRun();
            run2.setText("Username: " + UserName + "     ID: " + UserID);

            // create run for the current date
            XWPFRun run3 = para2.createRun();
            run3.setText("     Date: ");
            

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = dateFormat.format(new Date());
            XWPFRun run4 = para2.createRun();
            run4.setText(currentDate);

            // create table
            if (ResultSet != null && ResultSet.getMetaData() != null) {
                int numColumns = ResultSet.getMetaData().getColumnCount();
                // create table with one row for header
                XWPFTable table = doc.createTable();

                // Set table alignment to center
                CTTblPr tblPr = table.getCTTbl().addNewTblPr();
                CTJc jc = tblPr.addNewJc();
                jc.setVal(STJc.CENTER);

                XWPFTableRow headerRow = table.getRow(0);
                if (headerRow == null) {
                    headerRow = table.createRow();
                }
                // set column names in the first row
                for (int i = 1; i <= numColumns; i++) {
                    String columnName = ResultSet.getMetaData().getColumnName(i);
                    XWPFTableCell headerCell = headerRow.getCell(i - 1);
                    if (headerCell == null) {
                        headerCell = headerRow.createCell();
                    }
                    headerCell.setText(columnName);

                    headerCell.setColor("0072c6");
                    XWPFParagraph paragraph = headerCell.getParagraphs().get(0);
                    paragraph.setAlignment(ParagraphAlignment.CENTER);
                }
                // create table rows
                while (ResultSet.next()) {
                    XWPFTableRow row = table.createRow();
                    // create cells for each column
                    ResultSetMetaData metaData = ResultSet.getMetaData();
                    for (int i = 1; i <= metaData.getColumnCount(); i++) {
                        String columnName = metaData.getColumnName(i);
                        String value = ResultSet.getString(i);
                        XWPFTableCell cell = row.getCell(i - 1);
                        if (cell == null) {
                            cell = row.createCell();
                        }
                        cell.setText(value);

                        cell.setColor("d9edf7");

                        XWPFParagraph paragraph = cell.getParagraphs().get(0);
                        paragraph.setAlignment(ParagraphAlignment.CENTER);
                    }
                }
            }



            // write the document to file
            FileOutputStream out = new FileOutputStream(new File("reports/" + FileName + ".docx"));
            doc.write(out);
            out.close();
            System.out.println("Done");

            ConvertTOPDF(FileName);
            OpenReport(FileName);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private double getColumnWidth(int columnType, String value) {
        double width = 0.0;

        switch (columnType) {
            case Types.INTEGER:
            case Types.BIGINT:
                width = 10.0;
                break;

            case Types.FLOAT:
            case Types.DOUBLE:
                width = 15.0;
                break;

            case Types.DATE:
            case Types.TIME:
            case Types.TIMESTAMP:
                width = 20.0;
                break;

            default:
                if (value.length() <= 20) {
                    width = 20.0;
                } else if (value.length() <= 50) {
                    width = 30.0;
                } else if (value.length() <= 100) {
                    width = 40.0;
                } else {
                    width = 50.0;
                }
                break;
        }

        return width;
    }





    public void ConvertTOPDF(String FileName){
        try {
            com.aspose.words.Document doc = new com.aspose.words.Document("reports/" + FileName + ".docx");
            doc.save("reports/" + FileName + ".pdf");
            System.out.println("Docx Convert To Pdf Successfull!");
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void OpenReport(String FileName){
        try {
            Desktop.getDesktop().open(new File("reports/" + FileName + ".pdf"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
