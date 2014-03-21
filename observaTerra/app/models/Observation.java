package models;

import java.util.List;

import play.db.ebean.*;
import play.data.validation.Constraints.*;
import javax.persistence.*;


@Entity
public class Observation extends Model {
    
  @Id
  public Long id;
  
  @Required
  public Double obsValue;
  
  public Observation(Double value, Country country, Indicator indicator) {
	  this.obsValue = value ;
	  this.country = country;
	  this.indicator=indicator;
  }
  
  public Observation(Double value, Long countryId, Long indicatorId) {
	  this.obsValue = value ;
	  this.country = Country.find.byId(countryId);
	  this.indicator=Indicator.find.byId(indicatorId);
  }

  @ManyToOne
  public Country country;

  @ManyToOne
  public Indicator indicator;
  
  public static Finder<Long,Observation> find = new Finder(Long.class, Observation.class);
  
  public static List<Observation> all() {
    return find.all();
  }
  
  public static void create(Observation obs) {
	obs.save();
  }
  
  public static void delete(Long id) {
	find.ref(id).delete();
  }
  
}