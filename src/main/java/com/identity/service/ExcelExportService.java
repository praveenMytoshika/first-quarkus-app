package com.identity.service;

import com.identity.model.response.excel_record.ExcelRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExportService {

    private final SXSSFWorkbook workbook;
    private SXSSFSheet sheet;
    private final List<ExcelRecord> excelRecords;

    public ExcelExportService(List<ExcelRecord> excelRecords) {
        super();
        this.excelRecords = excelRecords;
        workbook = new SXSSFWorkbook();
    }

    private void writeHeaderRow() {
        sheet = workbook.createSheet("ExcelRecordReport");
        Row row = sheet.createRow(0);
        CellStyle style = createHeaderStyle();

        String[] headers = { "record_id", "record_name", "record_type" };

        for (int i = 0; i < headers.length; i++) {
            createCell(row, i, headers[i], style);
        }

        sheet.trackAllColumnsForAutoSizing();
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataRows() {
        int rowCount = 1;
        CellStyle style = createDataStyle();

        for (ExcelRecord excelRecord : excelRecords) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, excelRecord.getRecordId(), style);
            createCell(row, columnCount++, excelRecord.getRecordName(), style);
            createCell(row, columnCount++, excelRecord.getRecordType(), style);
        }
    }

    public byte[] export() throws IOException {
        writeHeaderRow();
        writeDataRows();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.dispose();

        outputStream.close();
        return outputStream.toByteArray();
    }

    private CellStyle createHeaderStyle() {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        return style;
    }

    private CellStyle createDataStyle() {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        return style;
    }
}
