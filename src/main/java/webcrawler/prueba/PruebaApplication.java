package webcrawler.prueba;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import webcrawler.prueba.webCrawler.*;

import javax.annotation.PostConstruct;

@SpringBootApplication
class PruebaApplication {

	@Autowired
	private SimpMessagingTemplate template;	//con este template podems enviar mensajes al topic,enviamos a computerpageone

	@PostConstruct		//el PostConstruct se ejecuta despues del main
	public void newThread(){
		System.out.println("imprimiendo TEMPLATE");
		System.out.println(template);
		//hilo para home
		ComputerPageOne hilo = new ComputerPageOne(
				template,
				"https://compucenter.store/category/23-equipo/77-laptop",		//CompuCenter Laptops
				"https://compucenter.store/category/23-equipo/238-gaming#",					//CompuCenter Gamer
				"https://www.pc.com.bo/assets/html/notebooks-hp.html", 				//marca
				"https://compucenter.store/product/2530-equipo-hp-laptop-15-dy1003ca"

		);
		hilo.start();
	}

	public static void main(String[] args) throws IOException {
		//SpringApplication.run(PruebaApplication.class, args);


		ComputerPageOne obj2 = new ComputerPageOne();
		obj2.techStoreLaptops("https://techstore.bo/product-category/mac/macbool-air/");

        //ejemplo 1
        //Identificar:
        // marca, procesador, memoria,almacenamiento, tarjeta grafica Opcional,
		/**
        System.out.println("////////Marca////////////");
        String marca = "Hp - LATITUDE 3520 1 TB HHD 1 TB SSD 512 GB";
        //Regular expression to find digits
        String regexMarca = "\\b(Hp|Lenovo|Asus|Dell)\\b";
        //Compiling the regular expression
        Pattern patternMarca = Pattern.compile(regexMarca);
        //Retrieving the matcher object
        Matcher matcherMarca = patternMarca.matcher(marca);
        if(matcherMarca.find()) {
            System.out.println("Match found marca2:");
            System.out.println(matcherMarca.group());
        } else {
            System.out.println("Match not found");
        }
		*/




        //Ejemplo, nueva estructura
		/**
		Example3 example3 = new Example3();
		//EJEMPLO
		String url = "https://www.imdb.com/chart/top";
		//Compucenter
		String url2 ="https://compucenter.store/category/23-equipo/77-laptop";
		String url3 = "https://compucenter.store/category/23-equipo/238-gaming";
		//Pc.com
		String url4 ="https://www.pc.com.bo/assets/html/notebooks-hp.html";
		String url5 = "https://www.pc.com.bo/assets/html/notebooks-asus.html";
		String url6 = "https://www.pc.com.bo/assets/html/notebooks-lenovo.html";
		String url7 = "https://www.pc.com.bo/assets/html/notebooks-dell.html";
		String url8 = "https://www.pc.com.bo/assets/html/notebooks-acer.html";
		String url9 = "https://www.pc.com.bo/assets/html/notebooks-msi.html";
		//creativo Computacion
		String url10 = "https://creativocomputacion.ecwid.com/Notebooks-c10743110";
		String url11 = "https://creativocomputacion.ecwid.com/Notebooks-HP-c10840325";
		String url12= "https://creativocomputacion.ecwid.com/Notebooks-Dell-c10840283";
		String url13 = "https://creativocomputacion.ecwid.com/Lenovo-Notebooks-c20149225";

		String urlStorePc ="https://www.pc.com.bo/index.html#";
		String urlStoreCreativo = "https://creativo.com.bo/";
		 */
		//example3.listAllPagesExampleTwo(url);
		//example3.compuCenterLaptops(url2);
		//example3.compuCenterGaming(url3);
		//example3.pcHp(url4);
		//example3.pcAsus(url5);
		//example3.pcLenovo(url6);
		//example3.pcDell(url7);
		//example3.pcAcer(url8);
		//example3.pcMsi(url9);
		//example3.creativoLaptops(url10);
		//example3.creativoHp(url11);
		//example3.creativoDell(url12);
		//example3.creativoLenovo(url13);
		//example3.pcTienda(urlStorePc);
		//example3.creativoTienda(urlStoreCreativo);


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






	}
}
