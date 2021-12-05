package webcrawler.prueba;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import webcrawler.prueba.webCrawler.ComputerPageOne;
import webcrawler.prueba.webCrawler.Example3;

@SpringBootApplication
class PruebaApplication {

	public static void main(String[] args) throws IOException {
		//SpringApplication.run(PruebaApplication.class, args);

		//1. extraer el html
		//2. Analizar el HTML y extraer enlaces de la pagina
		//3. con un FOR recorremos todos los links y los extraemos
		// tambien se puede elegir etiquetas especificas y buscarlas en la pagina web, ejemplo etiqueta <H2>

		//EJEMPLO
		/**
		Example1 example1 = new Example1();
		example1.obtenerEnlacesExtraidos("http://www.mkyong.com");
		example1.obtenerArticulos();
		example1.escribirArchivo("Java 8 Articles");
		*/


		//EJEMPLO 1
		/***/
		//ParseComputerPage  PRUEBA
		ComputerPageOne compu = new ComputerPageOne();
		//String urltwo = "https://www.intecsa.com.bo/product/dell-latitude-3520-core-i5-2/";
		String shopUrl = "https://www.intecsa.com.bo/nosotros/";
		try {
			//compu.extractBrand(urltwo);
		    //compu.extractProductType(urltwo);
			//compu.extractShop(shopUrl);
			//compu.extractProduct(urltwo);
			compu.extractDetail();

            //compu.extractImg(urltwo); // img prueba
		} catch (IOException e) {
			e.printStackTrace();
		}



	}
}
