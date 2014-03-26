package models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static play.test.Helpers.*;
import play.test.*;

import org.junit.*;



public class ObservationTest extends WithApplication {
	  
	@Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }
	
	@Test
	public void saveObservation() {
		Observation obs = new Observation("es","hdi",2.3);
		obs.save();
		assertThat(Country.findByName("Spain").code).isEqualTo("es");
		assertEquals(Observation.all().size(),1);
		assertThat(Observation.average(Observation.all())).isEqualTo(2.3);
	  }

}
