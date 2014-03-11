package es.uniovi.asw.observaTerra;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.*;

import java.io.InputStream;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;


public class ExcelSteps {

private final ExcelReader reader = new ExcelReader();

private InputStream input;
private ObservationList obsList;

@Dado("^que obtengo la hoja excel (.+)$")
public void que_obtengo_la_hoja_excel(String xlsFile) throws Throwable {
	input = new ExcelSteps().getClass().getClassLoader()
			.getResourceAsStream(xlsFile);
}

@Cuando("^leo las observaciones$")
public void leo_las_observaciones() throws Throwable {
	obsList = reader.read(input);
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