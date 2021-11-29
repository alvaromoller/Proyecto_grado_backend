package webcrawler.prueba;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import webcrawler.prueba.dto.BrandDto;
import webcrawler.prueba.model.Transaction;
import webcrawler.prueba.webCrawler.ParseComputerPage;

@SpringBootApplication
class PruebaApplication {

	public static void main(String[] args) throws IOException {
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

/**
		//EJEMPLO 1 tutorial
		//ParseComputerPage  PRUEBA
		ParseComputerPage compu = new ParseComputerPage();
		String urltwo = "https://www.intecsa.com.bo/";
		try {
			compu.listAllPages(urltwo);
		} catch (IOException e) {
			e.printStackTrace();
		}

*/

	}
}
