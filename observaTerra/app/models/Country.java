package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PersistenceException;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Country extends Model {
    
  @Id
  public Long id;
  
  @Required
  public String name;
  
  public Country(String name) {
	  this.name = name;
  }
   
  public static Finder<Long,Country> find = new Finder(Long.class, Country.class);
  
  public static List<Country> all() {
    return find.all();
  }
  
  public static void create(Country country) {
	if (Country.findByName(country.name).isEmpty()) { 
		country.save();
	} else
		throw new CountryExistsException();
  }
  
  public static void delete(Long id) {
	find.ref(id).delete();
  }
  
  public static List<Country> findByName(String name) {
	  return find.where().eq("name", name).findList();
  }
  
  public static Country getOrCreate(String name) {
	  List<Country> foundCountries = Country.findByName(name);
	  if (foundCountries.isEmpty()) {
		  Country country = new Country(name);
		  country.save();
		  return country;
	  } else {
		  return foundCountries.get(0);
	  }
  }
  
}

class CountryExistsException extends PersistenceException {};