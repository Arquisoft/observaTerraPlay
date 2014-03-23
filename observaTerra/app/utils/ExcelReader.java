package utils ;

import java.io.IOException;
import java.io.InputStream;

import models.Country;
import models.Indicator;
import models.Observation;
import models.ObservationList;

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
		  if (row.getRowNum() == 0) { // skip headers
			  
		  } else {
			String countryName = "";
			String indicatorName = "";
			Double value = 0.0;

			for (Cell cell : row) {
				if (cell.getColumnIndex() == 0) {
					countryName = cell.getStringCellValue();
				}
				if (cell.getColumnIndex() == 1) {
					indicatorName = cell.getStringCellValue();
				}
				if (cell.getColumnIndex() == 2) {
					value = cell.getNumericCellValue();
				}
			}
			if (!countryName.equals("")) {
				Observation obs = new Observation(value, new Country(countryName), new Indicator(indicatorName));
				obsList.addObservation(obs);
			} 
		  }
		}

	  return obsList;
	}
}