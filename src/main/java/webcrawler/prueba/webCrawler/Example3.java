package webcrawler.prueba.webCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class Example3 {

    //ejemplo1
     public void listAllPages(String url) throws IOException {
        System.out.println("Ejemplo 1, obteniendo links de paginas de computadoras " + url + "...");

        Document doc = Jsoup.connect(url).timeout(10000).get();
        Elements body = doc.select("ul.products");

        print("\nBody: (%d)", body.size());
         for (Element e : body.select("li"))
         {
             String ranking = e.select("span.nxowoo-box a").attr("href");
             System.out.println(ranking);
         }
     }

     private static void print(String msg, Object... args) {
     System.out.println(String.format(msg, args));
     }


     //ejemplo 2
     public void listAllPagesExampleTwo(String url) throws IOException {
         System.out.println("Ejemplo 2, obteniendo links de paginas de computadoras " + url + "...");
         Document doc = Jsoup.connect(url).timeout(6000).get();
         Elements body = doc.select("tbody.lister-list");

         int cnt = 0;
         for (Element e : body.select("tr"))
         {
             String ranking = e.select("td.posterColumn span").attr("data-value");
             String img  = e.select("td.posterColumn img").attr("src");
             String titulo = e.select("td.posterColumn img").attr("alt");
             String anio = e.select("td.titleColumn span.secondaryInfo").text();
             System.out.println(titulo);
             System.out.println(ranking);
             System.out.println(anio.replaceAll( "[^\\d]", "" ));
         }

     }



}
