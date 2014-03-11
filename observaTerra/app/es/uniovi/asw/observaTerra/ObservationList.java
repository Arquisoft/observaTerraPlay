package es.uniovi.asw.observaTerra;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ObservationList {
	private SortedMap<Country, Double> obsMap = new TreeMap<Country, Double>();

	public void addObservation(Country country, Double value) {
	    obsMap.put(country,value);
	}
	
	public Double average() {
		Double sum = 0.0;
		for (Double d : obsMap.values()) {
			sum += d;
		}
		return sum / obsMap.size() ;
	}
	
	public int length() {
		return obsMap.size() ;
	}

	public String show() {
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<Country,Double> entry : obsMap.entrySet()) {		
			sb.append(entry.getKey().getName() + " -> " + entry.getValue() + "\n");
		}
		return sb.toString();
	}
}
