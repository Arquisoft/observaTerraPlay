package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Country;

public class ObservationList {
	private List<Observation> obsList;

	public ObservationList() {
		this.obsList = new ArrayList<Observation>();
	}
	
	public ObservationList(List<Observation> obsList) {
		this.obsList = obsList;
	}

	public void addObservation(Observation obs) {
	    obsList.add(obs);
	}
	
	public Double average() {
		Double sum = 0.0;
		for (Observation obs : obsList) {
			sum += obs.obsValue;
		}
		return sum / length() ;
	}
	
	public int length() {
		return obsList.size() ;
	}
	
	public ObservationList selectByIndicator(String indicatorName) {
		ObservationList returnList = new ObservationList();
		for (Observation obs : obsList) {
			if (obs.indicator.name == indicatorName)
				returnList.addObservation(obs);
		}
		return returnList;
	}

	public void save() {
		for (Observation obs: obsList) {
			obs.save();
		}
	}
}
