package models;

import java.util.List;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dada;
import cucumber.api.java.es.Entonces;
import models.Country;
import models.ObservationList;
import models.Indicator;
import models.Observation;
import static org.junit.Assert.assertEquals;

public class ObservacionesSteps {

	private ObservationList observationList; 
	private Double media;

	@Dada("^una lista de observaciones:$")
	public void una_lista_de_observaciones(final List<ObservationRow> observations) 
			throws Throwable {
		observationList = new ObservationList();
		for (ObservationRow obs : observations) {
			Country c = new Country(obs.country);
			Indicator i = new Indicator(obs.indicator);
			observationList.addObservation(new Observation(obs.value, new Country(obs.country), new Indicator(obs.indicator)));
        }
	}

	@Cuando("^selecciono las observaciones con el indicador (.+)")
	public void selecciono_las_observaciones_con_indicador(String indicator) throws Throwable {
		observationList = observationList.selectByIndicator(indicator);
	}

	@Cuando("^calculo la nota media$")
	public void calculo_la_nota_media() throws Throwable {
		media = observationList.average();
	}

	@Entonces("^obtengo el valor (.+)$") 
	public void obtengo_el_valor(Double expected) throws Throwable {
		assertEquals(expected,media,0.001);
	}

	@Dada("^una observación del país (.+) con indicador (.+) y valor (.+)$")
	public void una_observación_(String nombre, String indicator, Double value) throws Throwable {
		observationList = new ObservationList();
		observationList.addObservation(new Observation(value,new Country(nombre), new Indicator(indicator)));
	}

	// Esta clase se utiliza solamente para la conversión entre
	// los campos de la tabla y los valores de prueba
	public static class ObservationRow {
        private String country;
        private String indicator;
        private Double value;
    }	
}