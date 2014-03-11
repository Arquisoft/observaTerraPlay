package es.uniovi.asw.observaTerra;

import static org.assertj.core.api.Assertions.*;

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
		obsList.addObservation(new Country("Spain"),value);
	    assertThat(obsList.average()).isEqualTo(value);
	  }

	@Test
	public void average_ObservationList_three_elements() {
		ObservationList obsList = new ObservationList();
		obsList.addObservation(new Country("Spain"),4.5);
		obsList.addObservation(new Country("Italy"),3.2);
		obsList.addObservation(new Country("France"),2.3);
	    assertThat(obsList.average()).isEqualTo(3.33,offset(0.01));
	  }
}
