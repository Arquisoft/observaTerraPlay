package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

public class Application extends Controller {

    public static Result index() {
        return ok(views.html.index.render(Observation.all(),Country.all(),Indicator.all()));
    }

    public static Result showCountries() {
    	return ok(views.html.country.render(Country.all(),countryForm));
    }
    
    public static Result showIndicators() {
    	return ok(views.html.indicator.render(Indicator.all(),indicatorForm));
    }
    
    public static Result showObservations() {
    	return ok(views.html.observation.render(Observation.find.all(),Country.all(),Indicator.all(),observationForm));
    }
    
    static Form<Country>  	  countryForm     = Form.form(Country.class);
    static Form<Indicator>    indicatorForm   = Form.form(Indicator.class);
    static Form<Observation>  observationForm = Form.form(Observation.class);

}
