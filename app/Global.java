import play.*;
import play.libs.*;

import java.util.*;

import com.avaje.ebean.*;

import models.*;

public class Global extends GlobalSettings {

	public void onStart(Application app) {
        InitialData.insert(app);
    }
    
	static class InitialData {
		public static void insert(Application app) {
			if (Country.all().isEmpty()) {
				Country 	spain = new Country("es","Spain");
				Country 	france= new Country("fr","France");
				Country		chile = new Country("cl","Chile");
				Country		italy = new Country("it","Italy");
				
				Indicator	hdi   = new Indicator("hdi","Human Development Index");
				Indicator	wi    = new Indicator("wi","WebIndex");

				spain.save();
				france.save();
				italy.save();
				chile.save();
				
				hdi.save();
				wi.save();
			}
		}
	}
}
