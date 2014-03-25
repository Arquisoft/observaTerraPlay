package controllers;

import play.libs.F;
import play.libs.F.Promise;
import play.libs.WS;
import play.mvc.Controller;
import play.mvc.Result;

public class Proxy extends Controller {

	public static Promise<Result> index(String url) {
		F.Promise<WS.Response> responsePromise = WS.url(url).get();
		return responsePromise.map(new F.Function<WS.Response,Result>(){
			public Result apply(WS.Response response) throws Throwable {
				return ok(response.getBody()).as("text/html");
			}
		});
	}
}
