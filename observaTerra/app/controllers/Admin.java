package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import models.Country;
import models.Indicator;
import models.Observation;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import utils.ExcelReader;

public class Admin extends Controller {

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
    
    public static Result deleteCountry(String code) {
        Country.remove(code);
        return redirect(routes.Application.showCountries());
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
    
    public static Result deleteIndicator(String code) {
        Indicator.remove(code);
        return redirect(routes.Application.showIndicators());
    }

    public static Result newObservation() {
      DynamicForm requestData = Form.form().bindFromRequest();
      String countryId = requestData.get("countryId");
      String indicatorId = requestData.get("indicatorId");
      Double value = Double.parseDouble(requestData.get("value"));
      Observation obs = new Observation(countryId,indicatorId,value);
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
    	    List<Observation> obsList = reader.read(new FileInputStream(file));
    	    for (Observation obs: obsList) {
    	    	obs.save();
    	    }
    	    Logger.info("Excel file uploaded with " + obsList.size() + " observations");
    	    return redirect(routes.Application.index());
       } else {
   	    	Logger.error("Missing file to upload ");
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
