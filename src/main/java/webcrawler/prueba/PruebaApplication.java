package webcrawler.prueba;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import webcrawler.prueba.webCrawler.*;

import javax.annotation.PostConstruct;

@SpringBootApplication
class PruebaApplication {

	@Autowired
	private SimpMessagingTemplate template;	//con este template podems enviar mensajes al topic,enviamos a computerpageone

	@PostConstruct		//
	public void newThread(){
		System.out.println("imprimiendo TEMPLATE");
		System.out.println(template);

		ComputerPageOne hilo = new ComputerPageOne(
				template,
				"https://www.dismac.com.bo/o85pd.html",
				"https://www.dismac.com.bo/o85pd.html",
				"https://www.dismac.com.bo/3g573lt-abm.html",
				"https://www.dismac.com.bo/82a2007lm.html",
				"https://compucenter.store/product/2502-equipo-asus-laptop-f512j-vivobook",
				"https://compucenter.store/product/2283-equipo-dell-laptop-inspiron-5593",
				"https://compucenter.store/product/2530-equipo-hp-laptop-15-dy1003ca",
				"https://compucenter.store/product/2520-equipo-hp-laptop-15-gw0007la",
				"https://compucenter.store/product/2548-equipo-hp-laptop-348-g7",
				"https://compucenter.store/product/2504-equipo-hp-laptop-14-dk1025wm",
				"https://www.dismac.com.bo/310h7la-abm.html",
				"https://www.dismac.com.bo/310h7la-abm.html",
				"https://www.dismac.com.bo/2t6a007fj.html",
				"https://www.dismac.com.bo/nbb-wah9-gray.html",
				"https://www.dismac.com.bo/nbb-wai9.html",
				"https://www.dismac.com.bo/machc-wah9lp.html",
				"https://www.dismac.com.bo/jh94x.html");
		hilo.start();
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(PruebaApplication.class, args);
		//Inicianlizando el Hilo
		//ComputerPageOne hilo1 = new ComputerPageOne();
		//hilo1.start();



		//Hilos
		/**
		Proceso1 hilo1 = new Proceso1();
		Proceso2 hilo2 = new Proceso2();

		//Hilo 1
		hilo1.start();
		try{
			hilo1.sleep(3000);
		}catch (InterruptedException e){
			System.out.println("Error en Hilo 1"+ e);
		}

		//Hilo 2
		hilo2.start();
		try {
			hilo2.sleep(3000);
		}catch (InterruptedException e){
			System.out.println("Error en Hilo 2" + e);
		}
		*/


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

/**
        //TIENDA 1
		 ComputerPageOne compu = new ComputerPageOne();

		//URLs
		 String shopUrl = "https://www.dismac.com.bo/empresa.html";
		 String url = "https://www.dismac.com.bo/o85pd.html";
		String url2 = "https://www.dismac.com.bo/3g573lt-abm.html";
		 String url3 = "https://www.dismac.com.bo/82a2007lm.html";

		try {
			 //product1
				//compu.extractShop(shopUrl);
				//compu.extractBrand(url);
				//compu.extractProductType(url);
				//compu.extractProduct(url);
				//compu.extractDetail(url);
			//product2
				 //compu.extractBrand2(url2);
				 //compu.extractProductType2(url2);
				//compu.extractProduct2(url2);
				//compu.extractDetail2(url2);
			 //product3
			 //compu.extractBrand3(url3);
			 //compu.extractProductType3(url3);
			 //compu.extractProduct3(url3);
			 //compu.extractDetail3(url3);


 		} catch (IOException e) {
					e.printStackTrace();
				 }
*/

/**
        //TIENDA 2
        ComputerPageTwo compu = new ComputerPageTwo();
        //URLs
        String shopUrl = "https://compucenter.store/about";  // img y ubicacion, description

        String url = "https://compucenter.store/product/2548-equipo-hp-laptop-348-g7";
        String url2 = "https://compucenter.store/product/2504-equipo-hp-laptop-14-dk1025wm";
        String url3 = "https://compucenter.store/product/2520-equipo-hp-laptop-15-gw0007la";

        try {
            //product1
            //compu.extractShop(shopUrl);
            //compu.extractBrand(url);
            //compu.extractProductType(url);
            //compu.extractProduct(url);
            //compu.extractDetail(url);
            //product2
            //compu.extractBrand2(url2);
            //compu.extractProductType2(url2);
            //compu.extractProduct2(url2);
            //compu.extractDetail2(url2);
            //product3
            //compu.extractBrand3(url3);
            //compu.extractProductType3(url3);
            //compu.extractProduct3(url3);
            //compu.extractDetail3(url3);


        } catch (IOException e) {
            e.printStackTrace();
        }
*/   //

/**/
        /**
		 //TIENDA 3
		 ComputerPageThree compu = new ComputerPageThree();
		 //URLs
		 String shopUrl = "https://www.multilaptops.net/acerca";  // img, descripcion y ubicacion

		 String url = "https://www.multilaptops.net/store2/191";
		 String url2 = "https://www.multilaptops.net/store2/194";
		 String url3 = "https://www.multilaptops.net/store2/181";

		 try {
		 //product1
		 //compu.extractShop(shopUrl);
		 //compu.extractBrand(url);
		 //compu.extractProductType(url);
		 //compu.extractProduct(url);
		 //compu.extractDetail(url);
		 //product2
		 //ompu.extractBrand2(url2);
		 //compu.extractProductType2(url2);
		 //compu.extractProduct2(url2);
		 //compu.extractDetail2(url2);
		 //product3
		 //compu.extractBrand3(url3);
		 //compu.extractProductType3(url3);
		 //compu.extractProduct3(url3);
		 compu.extractDetail3(url3);


		 } catch (IOException e) {
		 e.printStackTrace();
		 }
	*/	 //


/**
		 //PRUEBA
		 ComputerPageTwo compu = new ComputerPageTwo();
		 //URLs
		 String shopUrl = "https://stackblitz.com/edit/angular-ivy-oudv5d?file=src%2Fapp%2Fapp.component.html";  // img, descripcion y ubicacion

		 String url = "https://compucenter.store/product/2049-gamer-dell-portatil-inspiron-gaming-g3-3500";
		 String url2 = "https://www.multilaptops.net/store2/194";
		 String url3 = "https://www.multilaptops.net/store2/181";

		 try {
		 //product1
		 compu.extractProductList(url);

		 } catch (IOException e) {
		 e.printStackTrace();
		 }
*/ //


	}
}
