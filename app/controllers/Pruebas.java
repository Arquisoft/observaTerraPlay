package controllers;

import play.libs.F;
import play.libs.F.Promise;
import play.libs.WS;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Pruebas extends Controller {

	public static Promise<Result> proxy(String url) {
		F.Promise<WS.Response> responsePromise = WS.url(url).get();
		return responsePromise.map(new F.Function<WS.Response,Result>(){
			public Result apply(WS.Response response) throws Throwable {
				return ok(response.getBody()).as("text/html");
			}
		});
	}
	
	public static Result ajax() {
		return ok(ajax.render());
	}
}
