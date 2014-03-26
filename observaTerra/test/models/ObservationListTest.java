package models;

import static org.assertj.core.api.Assertions.*;
import models.ObservationList;

import org.junit.Test;

public class ObservationListTest {
	
	@Test
	public void emptyObservationList_has_no_elements() {
		ObservationList obsList = new ObservationList();
	    assertThat(obsList.length()).isEqualTo(0);
	  }

	@Test
	public void averageObservationListWithOneElement_is_value() {
		ObservationList obsList = new ObservationList();
		Double value = 3.2 ;
		obsList.addObservation(new Observation(value, new Country("Spain"),new Indicator("hdi")));
	    assertThat(obsList.average()).isEqualTo(value);
	  }

	@Test
	public void average_ObservationList_three_elements() {
		ObservationList obsList = new ObservationList();
		obsList.addObservation(new Observation(4.5, new Country("Spain"),new Indicator("hdi")));
		obsList.addObservation(new Observation(3.2,new Country("Italy"),new Indicator("hdi")));
		obsList.addObservation(new Observation(2.3,new Country("France"),new Indicator("hdi")));
	    assertThat(obsList.average()).isEqualTo(3.33,offset(0.01));
	  }
}
