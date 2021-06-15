# Mutantes

# Mutant API

### Stack
- Java 11
- H2
- Postgresql
- Apache Maven 3.6
- Spring Boot 2.5.1
- Jacoco  
- Junit 5


### Instrucciones para instalación local
- Descargar e instalar Java JDK 11
- Descargar e instalar [Apache Maven](https://maven.apache.org/download.cgi)
- Descargar e instalar [IntelliJ](https://www.jetbrains.com/idea/download/)
- Se utiliza una base de datos embebida H2 para el repositorio local
- Clonar [este repositorio](https://github.com/davidsar133/mutant)
- Abrir el projecto en intellij, esperar a que se ejecuten los procesos de maven.
- En la parte superior derecha, abrir donde dice Edit configurations.
- En el simbolo + de la parte superior izquierda, exoger la opcion maven
- En command line poner clean springboot:run
- Dar click en Aplicar y en OK
- Ejecutar con el boton de play.

#### Desde consola
- Dirigirse a la carpeta raiz del proyecto
- Ejecutar el comand mvn clean springboot:run

### Ejecución
Desde postman, crear un nuevo request de tipo POST con la url http://localhost:8080/mutant


Ejemplo de consumo
-----
#### mutant endpoint
A continuación se muestran ejemplos concretos de request y response con la API desde postman. La url es```localhost:8080/mutant```
```
POST localhost:8080/mutant
{
    "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
```
```
Response: 200 - OK
```

### Stats endpoint


```
GET localhost:8080/stats
```
```
Response 200 - OK 
{
    "count_mutant_dna": 2,
    "count_human_dna": 2,
    "ratio": 1
}
```
----
## Tests
### Unit Test
Para ejecutar los tests basta con ejecutar el comando ```mvn test``` desde la consola. 
El reporte incluido en la capeta target del proyecto dicta una cobertura del 91% del codigo.




