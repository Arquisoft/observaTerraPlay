package utils;

import java.io.InputStream;

import models.Observation;
import models.ObservationList;

import org.junit.*;

import static org.assertj.core.api.Assertions.*;
import play.Logger;
import play.test.FakeApplication;
import play.test.Helpers;


public class ExcelReaderTest {
	  
	public static FakeApplication app;
	  
	@BeforeClass
	public static void startApp() {
	  app = Helpers.fakeApplication(Helpers.inMemoryDatabase());
	  Helpers.start(app);
	}
	  
	@Test
	public void readExcelFile() throws Throwable {
	  String xlsFile = "test.xlsx";
	  InputStream input = new ExcelReaderTest().getClass().getClassLoader()
						  .getResourceAsStream(xlsFile);
	  ExcelReader excelReader = new ExcelReader();
	  ObservationList obsList = excelReader.read(input);
	  obsList.save();
	  assertThat(Observation.all().average()).isEqualTo(3.325,offset(0.001));
	  for (Observation obs: Observation.find.all()) {
		  Logger.info("Observation: " + obs);
	  }
	}
	
	@AfterClass
	  public static void stopApp() {
	    Helpers.stop(app);
	  }
}
