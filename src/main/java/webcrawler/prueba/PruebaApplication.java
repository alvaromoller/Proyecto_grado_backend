package webcrawler.prueba;
//

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
//

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import webcrawler.prueba.service.ParseComputerPage;
import webcrawler.prueba.service.ParsePage;

@SpringBootApplication
class PruebaApplication {

	private HashSet<String> links;
	private List<List<String>> articles;

	//Extractor
	public PruebaApplication() {
		links = new HashSet<>();
		articles = new ArrayList<>();
	}

//1. primer ejemplo

	public void getPageLinks(String URL) {
		//4. Check if you have already crawled the URLs
		//(we are intentionally not checking for duplicate content in this example)
		if (!links.contains(URL)) {
			try {
				//4. (i) If not add it to the index
				if (links.add(URL)) {
					System.out.println(URL);
				}

				//2. Fetch the HTML code
				Document document = Jsoup.connect(URL).get();
				//3. Parse the HTML to extract links to other URLs
				Elements linksOnPage = document.select("a[href]");

 				//5. For each extracted URL... go back to Step 4.
				for (Element page : linksOnPage) {
					getPageLinks(page.attr("abs:href"));
				}
			} catch (IOException e) {
				System.err.println("For '" + URL + "': " + e.getMessage());
			}
		}
	}


	public static void main(String[] args) throws IOException {
		//SpringApplication.run(PruebaApplication.class, args);

		//1. extraer el html
		//2. Analizar el HTML y extraer enlaces de la pagina
		//3. con un FOR recorremos todos los links y los extraemos
		// tambien se puede elegir etiquetas especificas y buscarlas en la pagina web, ejemplo etiqueta <H2>

		//PruebaApplication bwc = new PruebaApplication();
		//bwc.getPageLinks("http://www.mkyong.com");
		//bwc.getArticles();
		//bwc.writeToFile("Java 8 Articles");

		//ParsePage
		//ParsePage parser = new ParsePage();
		//String url = "http://elfreneticoinformatico.com";
		//parser.listAllLinks(url);

		//ParseComputerPage
		ParseComputerPage compu = new ParseComputerPage();
		String urltwo = "https://mkyong.com/";
		compu.listAllPages(urltwo);

	}




}
