# language: es
Característica: Analizar hojas excel

Escenario: Contar campos de hoja excel

    Dado que obtengo el fichero excel test.xlsx
    Cuando leo las observaciones
    Entonces el número de observaciones es 4

Escenario: Leer campos de hoja excel

    Dado que obtengo el fichero excel test.xlsx
    Cuando leo las observaciones
    Entonces el valor medio es 3,325 

Escenario: Subir una hoja excel y cargar el contenido

	Dado que obtengo el fichero excel test.xlsx
	Y no hay observaciones en la base de datos
	Cuando leo las observaciones 
	Y cargo el fichero a través del API
	Entonces las observaciones de la base de datos son las mismas que las que he leído     