package models;

import java.util.List;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;


@Entity
public class Indicator extends Model {
    
  @Id
  public Long id;
  
  @Required
  public String name;
  
  public Indicator(String name) {
	  this.name = name;
  }
   
  public static Finder<Long,Indicator> find = new Finder(Long.class, Indicator.class);
  
  public static List<Indicator> all() {
    return find.all();
  }
  
  public static void create(Indicator indicator) {
	indicator.save();
  }
  
  public static void delete(Long id) {
	find.ref(id).delete();
  }

  public static List<Indicator> findByName(String name) {
	  return find.where().eq("name", name).findList();
  }

  public static Indicator getOrCreate(String name) {
	  List<Indicator> foundIndicators = Indicator.findByName(name);
	  if (foundIndicators.isEmpty()) {
		  Indicator indicator = new Indicator(name);
		  indicator.save();
		  return indicator;
	  } else {
		  return foundIndicators.get(0);
	  }
  }

}