package webcrawler.prueba;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import webcrawler.prueba.webCrawler.ComputerPageOne;
import webcrawler.prueba.webCrawler.ComputerPageTwo;
import webcrawler.prueba.webCrawler.Example3;

@SpringBootApplication
class PruebaApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(PruebaApplication.class, args);

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
		/**
		//ParseComputerPage  PRUEBA
		ComputerPageOne compu = new ComputerPageOne();
		//String urltwo = "https://www.intecsa.com.bo/product/dell-latitude-3520-core-i5-2/";
		String shopUrl = "https://www.intecsa.com.bo/nosotros/";
		try {
		    //compu.extractBrand(urltwo);
		    //compu.extractProductType(urltwo);
			//compu.extractProduct(urltwo);
            //compu.extractImg(urltwo);
			//compu.extractShop(shopUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
/**
		//EJEMPLO 2
		 //ParseComputerPage  PRUEBA
		 ComputerPageTwo compu = new ComputerPageTwo();
		 String shopUrl = "https://www.dismac.com.bo/empresa.html";
		 String url = "https://www.dismac.com.bo/o85pd.html";
		 try {
		 	//compu.extractShop(shopUrl);
		 	//compu.extractBrand(url);
		 	//compu.extractProductType(url);
			 compu.extractDetail(url);
		 } catch (IOException e) {
		 	e.printStackTrace();
		 }
*/

	}
}
