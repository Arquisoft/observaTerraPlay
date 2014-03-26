/* Dibujar gráfico de barras 
Se requiere la librería D3.js
 */

var ancho = 600, 
	alturaBarra = 20, 
	despX = 20;

var x = d3.scale.linear().range([ 0, ancho - despX ]);

// Selecciona elemento de la página con el gráfico
var grafico = d3.select(".chart")
				.attr("width", ancho);

d3.json("/api/observation/indicator/hdi", drawBars);

function drawBars(error, data) {
	console.log("Before loading data...");
	if (error) {
		console.log("Error loading data!" + error)
	} else {
		console.log("Data loaded ok with " + data.length + " entries");

	x.domain([ 0, despX + d3.max(data, function(d) { return d.value; }) ]);
	grafico.attr("width", ancho)
	grafico.attr("height", alturaBarra * data.length);

	// Crea un grupo por cada país desplazado a la posición
	// que le corresponda
	var bar = grafico.selectAll("g").data(data).enter().append("g").attr(
			"transform",
			function(d, i) {
				return "translate(" + 3 * despX + "," + i * alturaBarra + ")";
			});

	// Escribe el código del país
	bar.append("text")
	   .attr("class", "countryCode")
	   .attr("x", -5)
	   .attr("y",alturaBarra / 2)
	   .attr("dy", ".35em")
	   .text(function(d) {
			return d.countryCode;
		});

		// Dibuja el rectángulo con la dimensión proporcional al score
		bar.append("rect")
		   .attr("width", function(d) {	return x(d.value);  })
		   .attr("height", alturaBarra - 1);

		// Escribe texto con el score
		bar.append("text")
		   .attr("x", function(d) { return x(d.value) + 2 * despX; })
		   .attr("y", alturaBarra / 2)
		   .attr("dy", ".35em")
		   .text(function(d) { return d.value.toFixed(2); });
	}
}
