package es.uniovi.asw.observaTerra ;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class ExcelReader {
	
	public ObservationList read(InputStream input) throws IOException {
	  ObservationList obsList = new ObservationList();
	  
	  XSSFWorkbook workbook = new XSSFWorkbook(input);
	  XSSFSheet sheet = workbook.getSheetAt(0);
	  for (Row row : sheet) {
			String countryName = "";
			Double value = 0.0;

			for (Cell cell : row) {
				if (cell.getColumnIndex() == 0) {
					countryName = cell.getStringCellValue();
				}
				if (cell.getColumnIndex() == 1) {
					value = cell.getNumericCellValue();
				}
			}
			if (!countryName.equals(""))
				obsList.addObservation(new Country(countryName), value);
		}

	  return obsList;
	}
}