package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelMethods {

    File file;
    FileInputStream inputStream;
    Workbook Workbook;
    Sheet sheet;
    FormulaEvaluator evaluator;
    DataFormatter formatter;
    FileOutputStream outputStream ;

    public ExcelMethods(String FilePath, String FileName) throws IOException {

	this.file = new File(FilePath + FileName);
	excelinputstreamopen();

    }

    public void excelinputstreamopen() throws IOException {
	this.inputStream = new FileInputStream(file);
	this.Workbook = new XSSFWorkbook(inputStream);
	this.evaluator = Workbook.getCreationHelper().createFormulaEvaluator();
	this.formatter = new DataFormatter();
    }

    public String getinputcellvalues(String SheetName, int i, int j) throws IOException {

	sheet = Workbook.getSheet(SheetName);
	try {
	    Row row = sheet.getRow(i);
	    org.apache.poi.ss.usermodel.Cell cell = row.getCell(j);

	    for (int m = 0; m < sheet.getNumMergedRegions(); m++) {
		CellRangeAddress region = sheet.getMergedRegion(m);
		int colIndex = region.getFirstColumn(); //number of columns merged
		int rowNum = region.getFirstRow();
		int rowlast = region.getLastRow();
		int collast = region.getLastColumn();

		if (i >= rowNum && i <= rowlast && j >= colIndex && j <= collast) {
		    cell = sheet.getRow(rowNum).getCell(colIndex);
		}
	    }

	    if (cell.getCellType() == 2 && cell.getCellFormula().contains(":")) {
		String concattemp = "=";

		concattemp = concattemp.concat(cell.getCellFormula());
		return concattemp;
	    }

	    else if (cell.getCellType() == 2 && cell.getCellFormula().contains(",")) {
		String concattemp = "=";

		concattemp = concattemp.concat(cell.getCellFormula());
		return concattemp;
	    }

	    else {
		return formatter.formatCellValue(cell, evaluator);
	    }
	}

	catch (java.lang.NullPointerException exc) {
	    return "";
	}
    }

    public void excelinputstreamclose() throws IOException {
	inputStream.close();
    }

    public int noofcellsinarow(String SheetName, int rownumber) {
	sheet = Workbook.getSheet(SheetName);

	return sheet.getRow(rownumber).getLastCellNum();

    }

    public List<String> getDatafromColumn(String SheetName, int ColumnNumber, boolean hasheader) {

	List<String> data = new ArrayList<String>();

	int noofrows = lastcellcontaingvalueinacolumn(SheetName, ColumnNumber);

	int i;
	if (hasheader) {
	    i = 1;
	} else {
	    i = 0;
	}

	for (; i <= noofrows; i++) {
	    try {
		data.add(getinputcellvalues(SheetName, i, ColumnNumber));
	    } catch (Exception e) {
		data.add("");
	    }
	}
	return data;
    }

    public List<String> getDatafromRow(String SheetName, int rowNumber) {

	List<String> data = new ArrayList<String>();

	int noofColumns = noofcellsinarow(SheetName, rowNumber);

	for (int i = 0; i <= noofColumns; i++) {
	    try {
		data.add(getinputcellvalues(SheetName, rowNumber, i));
	    } catch (Exception e) {
		data.add("");
	    }
	}
	return data;
    }

    public int lastcellcontaingvalueinacolumn(String SheetName, int columnnumber) {

	sheet = Workbook.getSheet(SheetName);

	int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
	int count = -1;

	for (int i = rowCount; i >= 0; i--) {
	    try {
		Row row = sheet.getRow(i);
		org.apache.poi.ss.usermodel.Cell cell = row.getCell(columnnumber);

		if ((cell) == null || (formatter.formatCellValue(cell) == "")) {

		}

		else {
		    count = i;
		    break;
		}
	    } catch (Exception ex) {

	    }

	}

	return count;
    }
    

    /*
     * OUTPUT STREAM Methods
     */


    public void exceloutputstreamopen() throws IOException {
    	this.inputStream = new FileInputStream(file);
    	this.Workbook = new XSSFWorkbook(inputStream);
        }

        public void exceloutputwriteHeaders(String sheetName, Object[] dataToWrite) throws IOException {
    	Sheet Sheet = Workbook.getSheet(sheetName);
    	Row r = Sheet.createRow(0);
    	for (int x = 0; x < dataToWrite.length; x++) {
    	    Cell c = r.createCell(x);
    	    c.setCellValue(dataToWrite[x].toString());
    	}

        }

        public void exceloutputwrite(String sheetName, Object[] dataToWrite) throws IOException {
    	Sheet sheet = Workbook.getSheet(sheetName);

    	int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

    	Row newRow = sheet.createRow(rowCount + 1);
    	for (int j = 0; j < dataToWrite.length; j++) {
    	    Cell cell = newRow.createCell(j);
    	    cell.setCellValue(dataToWrite[j].toString());
    	}

        }

        public void exceloutputcreatenewSheet(String sheetName) throws IOException {

    	Workbook.createSheet(sheetName);

        }

        public void exceloutputstreamclose() throws IOException {
    	inputStream.close();
    	FileOutputStream outputStream = new FileOutputStream(file);
    	Workbook.write(outputStream);
    	outputStream.close();
        }

        public static void CreateExcelFile(String Path, String FileName, String Sheetname) throws IOException {

    	File Outputfile = new File(Path + FileName);
    	Workbook wb = new XSSFWorkbook();
    	FileOutputStream fileOut = new FileOutputStream(Outputfile);

    	wb.createSheet(Sheetname);

    	//	Sheet Sheet = wb.getSheet(Sheetname);
    	//
    	//	for (String[] data : datatowrite) {
    	//	    setValuesToLastRow(Sheet, data);
    	//	}

    	wb.write(fileOut);
    	fileOut.close();

        }

        public void setValuesToLastRow(String SheetName, Object[] dataToWrite) {

    	Sheet Sheet = this.Workbook.getSheet(SheetName);
    	int lastrow = Sheet.getPhysicalNumberOfRows();

    	Row newRow = Sheet.createRow(lastrow);
    	for (int j = 0; j < dataToWrite.length; j++) {
    	    String CellValue = dataToWrite[j].toString();
    	    Cell cell = newRow.createCell(j);

    	    if (CellValue.matches("^[0-9]+$")) {
    		try {

    		    cell.setCellValue(Integer.parseInt(CellValue));
    		} catch (java.lang.NumberFormatException e) {

    		    cell.setCellValue(CellValue);
    		}
    	    } else {
    		cell.setCellValue(CellValue);
    	    }

    	}
        }


}
