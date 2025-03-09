package excelHandling;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	 public Map<String, String> getDataFromExcel(String filePath, String sheetName) {
	        Map<String, String> dataMap = new HashMap<>();
	        try (FileInputStream fis = new FileInputStream(filePath);
	             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
	            XSSFSheet sheet = workbook.getSheet(sheetName);
	            if (sheet == null) {
	                throw new IllegalArgumentException("Sheet not found: " + sheetName);
	            }

	            // Iterate over the rows
	            for (Row row : sheet) {
	                if (row.getRowNum() == 0) continue; // Skip header row
	                String key = getCellValueAsString(row.getCell(0)); // Assuming keys are in the first column
	                String value = getCellValueAsString(row.getCell(1)); // Assuming values are in the second column
	                dataMap.put(key, value);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return dataMap;
	    }
	    private String getCellValueAsString(Cell cell) {
	        if (cell == null) {
	            return "";
	        }
	        switch (cell.getCellType()) {
	            case STRING:
	                return cell.getStringCellValue();
	            case NUMERIC:
	                return String.valueOf((long) cell.getNumericCellValue()); // Convert numeric to String
	            case BOOLEAN:
	                return String.valueOf(cell.getBooleanCellValue());
	            case FORMULA:
	                return cell.getCellFormula(); // Handle formulas if necessary
	            case BLANK:
	            default:
	                return "";
	        }
	    }
}
