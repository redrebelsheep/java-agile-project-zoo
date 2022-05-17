package de.volkswagen.f73.util;

import io.swagger.client.model.ZooAccount;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * The class "ExportUtil"
 *
 * @Author Marcel Westphal, David Druecke, Marian Kowall
 */
public abstract class ExportUtil {

    private static CellStyle headlineCellStyle;
    private static CellStyle dateCellStyle;
    private static CellStyle currencyCellStyle;
    private static CellStyle textCellStyle;

    /**
     *
     * util method for expanse to export the data to .xls excel
     *
     * @param path  Path where the file would be created
     * @param transactionsList   List with data for the file
     * @param sheetName Name of created sheet
     */
    public static void exportIncomesOrExpansesToExcel(String path, List<ZooAccount> transactionsList, String sheetName){
        // Creating file
        File file = new File(path);

        // Create excel workbook with sheet
        Workbook workbook = excelWorkbookWithPath(path);
        Sheet sheet = workbook.createSheet(sheetName);
        configureFormat(workbook);

        // Set the headline into the file
        String[] headlineStrings = {"Eintrag", "Datum", "Verwendungszweck", "Betrag", "Kontostand nach Buchung"};
        createHeadlineInSheet(sheet, headlineStrings);

        // Set the values into the file
        for(int i = 0; i < transactionsList.size(); i++){
            createRowWithData(transactionsList, sheet, i);
        }

        autoSizeColumnFromSheet(sheet, headlineStrings.length);

        // Write the file
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            workbook.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Workbook excelWorkbookWithPath(String path) {
        if(path.contains(".xlsx")){
            return new XSSFWorkbook();
        }else{
            return new HSSFWorkbook();
        }
    }

    private static void createRowWithData(List<ZooAccount> transactionsList, Sheet sheet, int rowNumber) {
        Row row = sheet.createRow(rowNumber +1);
        Cell idCell = row.createCell(0);
        idCell.setCellStyle(textCellStyle);
        idCell.setCellValue(rowNumber +1);
        Cell dateCell = row.createCell(1);
        dateCell.setCellStyle(dateCellStyle);
        LocalDate date = LocalDate.parse(transactionsList.get(rowNumber).getDate().toString());
        dateCell.setCellValue(date);
        Cell usageCell = row.createCell(2);
        usageCell.setCellStyle(textCellStyle);
        usageCell.setCellValue(transactionsList.get(rowNumber).getUsageOfBooking());
        Cell valueCell = row.createCell(3);
        valueCell.setCellStyle(currencyCellStyle);
        valueCell.setCellValue(transactionsList.get(rowNumber).getValueOfBooking().doubleValue());
        Cell balanceCell = row.createCell(4);
        balanceCell.setCellStyle(currencyCellStyle);
        balanceCell.setCellValue(transactionsList.get(rowNumber).getBankBalance().doubleValue());
    }

    private static void createHeadlineInSheet(Sheet sheet, String[] headlineStrings) {
        Row headlineRow = sheet.createRow(0);
        for(int i = 0; i< headlineStrings.length; i++){
            Cell cell = headlineRow.createCell(i);
            cell.setCellStyle(headlineCellStyle);
            cell.setCellValue(headlineStrings[i]);
        }
    }

    private static void configureFormat(Workbook workbook){
        headlineCellStyle = workbook.createCellStyle();
        headlineCellStyle.setBorderBottom(BorderStyle.MEDIUM);
        dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(workbook.createDataFormat().getFormat("dd.MM.YY"));
        currencyCellStyle = workbook.createCellStyle();
        currencyCellStyle.setDataFormat(workbook.createDataFormat().getFormat("#.#0 €;-#.#0 €"));
        textCellStyle = workbook.createCellStyle();
    }

    private static void autoSizeColumnFromSheet(Sheet sheet, int length) {
        for (int i = 0; i < length; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
