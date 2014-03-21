package models;

import java.util.Arrays;
import java.util.List;

public class Country {
	
	public final String name;
	
	public Country(String name) {
		this.name = name;
	}

	public static List<Country> all() {
		Country spain 	= new Country("Spain");
    	Country france  = new Country("France");
    	Country italy	= new Country("Italy");
    	return Arrays.asList(spain,france,italy);
	}
}
