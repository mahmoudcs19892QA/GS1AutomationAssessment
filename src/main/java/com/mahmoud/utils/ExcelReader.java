package com.mahmoud.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static Object[][] getTestData(String filePath, String sheetName) {
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(file)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in workbook: " + filePath);
            }

            DataFormatter formatter = new DataFormatter();
            List<Object[]> records = new ArrayList<>();
            int lastRowNum = sheet.getLastRowNum();

            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }

                Cell cell = row.getCell(0);
                if (cell == null || cell.getCellType() == CellType.BLANK) {
                    continue;
                }

                String cellValue = formatter.formatCellValue(cell).trim();
                if (!cellValue.isEmpty()) {
                    try {
                        records.add(new Object[]{Integer.parseInt(cellValue)});
                    } catch (NumberFormatException e) {
                        records.add(new Object[]{cellValue});
                    }
                }
            }

            return records.toArray(new Object[0][]);

        } catch (IOException e) {
            throw new RuntimeException("Could not read Excel file: " + filePath, e);
        }
    }
}
