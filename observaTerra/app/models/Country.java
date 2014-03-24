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
  public String code;
  public String name;
  
  public Country(String code, String name) {
	  this.code = code;
	  this.name = name;
  }
   
  public static Finder<String,Country> find = new Finder(String.class, Country.class);
  
  public static List<Country> all() {
    return find.all();
  }

  public static void create(Country country) {
	if (Country.findByName(country.name) == null) { 
			country.save();
	}
  }

  public static void remove(String code) {
	find.ref(code).delete();
  }
  
  public static Country findByName(String name) {
	  return find.where().eq("name", name).findUnique();
  }

}

