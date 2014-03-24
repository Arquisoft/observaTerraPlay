package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import models.Country;
import models.Indicator;
import models.Observation;
import models.ObservationList;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import utils.ExcelReader;

public class Application extends Controller {

    public static Result index() {
        return ok(views.html.index.render());
    }

    public static Result showCountries() {
    	return ok(views.html.country.render(Country.all(),countryForm));
    }
    
    public static Result newCountry() {
      Form<Country> form = countryForm.bindFromRequest();
  	  if(form.hasErrors()) {
  	    return badRequest(
  	      views.html.country.render(Country.all(),countryForm)
  	    );
  	  } else {
  		Country countryToAdd = form.get();
  	    Country.create(countryToAdd);
  	    return redirect(routes.Application.showCountries());  
  	  }    
    }
    
    public static Result deleteCountry(Long id) {
        Country.delete(id);
        return redirect(routes.Application.showCountries());
    }
    
    
    public static Result showIndicators() {
    	return ok(views.html.indicator.render(Indicator.all(),indicatorForm));
    }
    
    public static Result newIndicator() {
      Form<Indicator> form = indicatorForm.bindFromRequest();
  	  if(form.hasErrors()) {
  	    return badRequest(
  	      views.html.indicator.render(Indicator.all(),indicatorForm)
  	    );
  	  } else {
  		Indicator ind = form.get();
  	    Indicator.create(ind);
  	    return redirect(routes.Application.showIndicators());  
  	  }    
    }
    
    public static Result deleteIndicator(Long id) {
        Indicator.delete(id);
        return redirect(routes.Application.showIndicators());
    }

    public static Result showObservations() {
    	return ok(views.html.observation.render(Observation.find.all(),Country.all(),Indicator.all(),observationForm));
    }
    
    public static Result getObs(String name, Integer id) {
    	return ok("Show obs " + name + " - " + id );
    }

    public static Result postObs() {
    	DynamicForm requestData = Form.form().bindFromRequest();
    	return ok("Request data: " + requestData);
    }

    public static Result newObservation() {
      DynamicForm requestData = Form.form().bindFromRequest();
      Double value = Double.parseDouble(requestData.get("value"));
      Long countryId = Long.parseLong(requestData.get("countryId"));
      Long indicatorId = Long.parseLong(requestData.get("indicatorId"));
      Observation obs = new Observation(value,countryId,indicatorId);
	  obs.save();
  	  return redirect(routes.Application.showObservations());  
    }

    public static Result deleteObservation(Long id) {
        Observation.delete(id);
        return redirect(routes.Application.showObservations());
    }
    
    public static Result uploadExcel() {
    try {
      MultipartFormData body = request().body().asMultipartFormData();
      FilePart excel = body.getFile("excel");
      if (excel != null) {
    	    String fileName = excel.getFilename();
    	    String contentType = excel.getContentType(); 
    	    File file = excel.getFile();
    	    ExcelReader reader = new ExcelReader();
    	    ObservationList obsList = reader.read(new FileInputStream(file));
    	    obsList.save();
    	    Logger.info("Excel file uploaded with " + obsList.length() + " observations");
    	    flash(Messages.get("file.uploaded", obsList.length()));
    	    return redirect(routes.Application.index());
       } else {
    	    flash("error", "Missing file");
    	    return redirect(routes.Application.index());    
       } 
      }
    catch (IOException e) {
      return(badRequest(Messages.get("read.excel.error") + "." + e.getLocalizedMessage()));	
    }
    }

    static Form<Country>  	  countryForm     = Form.form(Country.class);
    static Form<Indicator>    indicatorForm   = Form.form(Indicator.class);
    static Form<Observation>  observationForm = Form.form(Observation.class);

}
