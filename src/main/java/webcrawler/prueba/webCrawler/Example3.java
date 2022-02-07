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
    //pagina, https://www.imdb.com/chart/top
     public void listAllPagesExampleTwo(String url) throws IOException {
         System.out.println("Ejemplo 2, obteniendo links de paginas de computadoras " + url + "...");
         Document doc = Jsoup.connect(url).timeout(10000).get();
         Elements body = doc.select("tbody.lister-list");

         int cnt = 0;
         for (Element e : body.select("tr"))
         {
             String ranking = e.select("td.posterColumn span").attr("data-value");
             String img  = e.select("td.posterColumn img").attr("src");
             String titulo = e.select("td.posterColumn img").attr("alt");
             String anio = e.select("td.titleColumn span.secondaryInfo").text();

             System.out.println("Titulo"+ titulo);
             System.out.println("Ranking"+ ranking);
             System.out.println("año"+ anio.replaceAll( "[^\\d]", "" ));
         }

     }


    //ejemplo 3
    //Dismac Pc Huawie,
    //https://www.dismac.com.bo/categorias/51-tecnologia/117-computacion/56-computadoras/huawei.html
    public void compuCenterDell(String url) throws IOException {
        System.out.println("Ejemplo 3, Categoria Dell " + url + "...");
        Document doc = Jsoup.connect(url).timeout(9000).get();
        Elements body = doc.select("section.flex.flex-wrap.justify-center.items-center");

        int cnt = 0;
        //nombre, imagen
        for (Element e : body.select("div.flex.items-center.p-5.relative.w-full"))
        {
            String nombre1 = e.select(" div.flex.flex-col.mb-2 strong  ").text();
            String nombre2 = e.select(" h1.font-semibold.text-lg ").text();
            String descripcion = e.select(" div.text-base.text-justify ").text();
            String precio = e.select(" div.inline-block.align-bottom.mr-5 span ").text();
            String imagen = e.select(" div.relative.border-4.border-red-100 img  ").attr("src");

            System.out.println("- nombre1: "+ nombre1);
            System.out.println("  nombre2:  "+ nombre2);
            System.out.println("  descripcion:  "+ descripcion);
            System.out.println("  precio:  "+ precio);
            System.out.println("  Imagen:   "+ imagen);
            System.out.println("-----");
        }



    }


}
