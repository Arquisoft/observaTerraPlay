package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class HolaMundo extends Controller {
 public static Result saluda() {
	 return ok("Hola mundo");
 }
}
