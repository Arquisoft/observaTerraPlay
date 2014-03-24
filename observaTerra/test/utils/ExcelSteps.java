package utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

import java.io.InputStream;

import models.ObservationList;
import cucumber.api.PendingException;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;


public class ExcelSteps {

private final ExcelReader reader = new ExcelReader();

private InputStream input;
private ObservationList obsList;

@Dado("^que obtengo el fichero excel (.+)$")
public void que_obtengo_la_hoja_excel(String xlsFile) throws Throwable {
	input = new ExcelSteps().getClass().getClassLoader()
			.getResourceAsStream(xlsFile);
}

@Cuando("^leo las observaciones$")
public void leo_las_observaciones() throws Throwable {
	obsList = reader.read(input);
}

@Y("^no hay observaciones en la base de datos$")
public void no_hay_observaciones_en_la_base_de_datos() throws Throwable {
	throw new PendingException();
}

@Y("cargo el fichero a través del API")
public void cargo_el_fichero_a_traves_del_api() throws Throwable {
	throw new PendingException();
} 

@Entonces("^el número de observaciones es (\\d+)$")
public void el_número_de_observaciones_es(int expected) throws Throwable {
    assertThat(obsList.length()).isEqualTo(expected);
}

@Entonces("^el valor medio es (.+)$")
public void el_valor_medio_es_(Double expected) throws Throwable {
	assertThat(obsList.average()).isEqualTo(expected,offset(0.001));
}

}