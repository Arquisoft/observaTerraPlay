package models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.*;

import play.test.FakeApplication;
import play.test.Helpers;

public class ObservationTest {
	  
	public static FakeApplication app;
	  
	@BeforeClass
	public static void startApp() {
	  app = Helpers.fakeApplication(Helpers.inMemoryDatabase());
	  Helpers.start(app);
	}
	  
	@Test
	public void saveObservation() {
		Observation obs = new Observation(2.3,"Spain","hdi");
		obs.save();
		assertEquals(Country.findByName("Spain").size(),1);
		assertEquals(Indicator.findByName("hdi").size(),1);
		assertEquals(Observation.all().length(),1);
		assertThat(Observation.all().average()).isEqualTo(2.3);
	  }

	@AfterClass
	  public static void stopApp() {
	    Helpers.stop(app);
	}	
}
