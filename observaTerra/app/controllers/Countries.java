package controllers;

import play.libs.F;
import play.libs.WS;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.country;

public class Countries extends Controller {

    public static Result showCountry(String name) {
        return ok(country.render(name));
    }
    
    public static Promise<Result> showCountries() {
    	String geonamesService = "http://api.geonames.org/countryInfo?username=demo";
    	F.Promise<WS.Response> responsePromise = WS.url(geonamesService).get();
    	return responsePromise.map(
    			new F.Function<WS.Response,Result>(){
    				public Result apply(WS.Response response) throws Throwable {
    					return ok(response.getBody()).as("application/xml");
    				}
    			}
    	);
    }

}
