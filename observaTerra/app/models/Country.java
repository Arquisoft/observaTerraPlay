package models;

import java.util.List;

import play.db.ebean.*;
import play.data.validation.Constraints.*;
import javax.persistence.*;

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
	country.save();
  }
  
  public static void delete(Long id) {
	find.ref(id).delete();
  }
  
}