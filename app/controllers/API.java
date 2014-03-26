package controllers;

import java.util.List;

import models.*;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.node.*;

public class API extends Controller {

    public static Result countries() {
    	List<Country> countries = Country.all();
    	JsonNodeFactory factory = JsonNodeFactory.instance;
        ObjectNode result = new ObjectNode(factory);
        ArrayNode array = new ArrayNode(factory);
    	for (Country country : countries) {
    		ObjectNode obj = Json.newObject();
        	obj.put("code", country.code);
        	obj.put("name", country.name);
        	array.add(obj);
    	}
    	result.put("countries",array);
    	return ok(result);
    }

    public static Result observations() {
    	List<Observation> obsList = Observation.all();
    	JsonNodeFactory factory = JsonNodeFactory.instance;
        ObjectNode result = new ObjectNode(factory);
        ArrayNode array = new ArrayNode(factory);
    	for (Observation obs : obsList) {
    		ObjectNode obj = Json.newObject();
        	obj.put("value", obs.obsValue);
        	obj.put("countryCode", obs.country.code);
        	obj.put("countryName", obs.country.name);
        	obj.put("indicatorCode", obs.indicator.code);
        	obj.put("indicatorName", obs.indicator.name);
        	array.add(obj);
    	}
    	result.put("observations",array);
    	return ok(result);
    }

    public static Result observationsByIndicator(String indicator) {
    	List<Observation> obsList = Observation.findByIndicatorName(indicator);
    	JsonNodeFactory factory = JsonNodeFactory.instance;
        ObjectNode result = new ObjectNode(factory);
        ArrayNode array = new ArrayNode(factory);
    	for (Observation obs : obsList) {
    		ObjectNode obj = Json.newObject();
        	obj.put("value", obs.obsValue);
        	obj.put("countryCode", obs.country.code);
        	obj.put("countryName", obs.country.name);
        	array.add(obj);
    	}
    	return ok(array);
    }

}
