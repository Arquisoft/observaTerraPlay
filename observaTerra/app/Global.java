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
				/*Country 	spain = new Country("Spain");
				Country 	france= new Country("France");
				Indicator	hdi   = new Indicator("hdi");
				Observation obs1 = new Observation(2.3,spain,hdi);
				Observation obs2 = new Observation(3.4,france,hdi);
				spain.save();
				france.save();
				hdi.save();
				obs1.save();
				obs2.save(); */
			}
		}
	}
}
