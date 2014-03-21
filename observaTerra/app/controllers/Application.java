package controllers;

import java.util.Arrays;
import java.util.List;

import models.Country;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result showCountries() {
    	return ok(countries.render(Country.all()));
    }
}
