package webcrawler.prueba;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import webcrawler.prueba.service.ParseComputerPage;
import webcrawler.prueba.service.ParsePage;

import  webcrawler.prueba.service.Example1;

@SpringBootApplication
class PruebaApplication {


	public static void main(String[] args) {
		SpringApplication.run(PruebaApplication.class, args);

		//1. extraer el html
		//2. Analizar el HTML y extraer enlaces de la pagina
		//3. con un FOR recorremos todos los links y los extraemos
		// tambien se puede elegir etiquetas especificas y buscarlas en la pagina web, ejemplo etiqueta <H2>

		//EJEMPLO 1
		/**
		Example1 example1 = new Example1();
		example1.obtenerEnlacesExtraidos("http://www.mkyong.com");
		example1.obtenerArticulos();
		example1.escribirArchivo("Java 8 Articles");
*/
		//EJEMPLO 2

		//ParsePage
		//ParsePage parser = new ParsePage();
		//String url = "http://elfreneticoinformatico.com";
		//parser.listAllLinks(url);

		//EJEMPLO 1 tutorial
		//ParseComputerPage  PRUEBA
		ParseComputerPage compu = new ParseComputerPage();
		String urltwo = "https://www.intecsa.com.bo/";
		try {
			compu.listAllPages(urltwo);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//EJEMPLO 2 tutorial
		ParseComputerPage compu2 = new ParseComputerPage();
		String urltwo2 = "https://www.imdb.com/chart/top";
		try {
			compu2.listAllPagesExampleTwo(urltwo2);
		} catch (IOException e) {
			e.printStackTrace();
		}


	}




}
