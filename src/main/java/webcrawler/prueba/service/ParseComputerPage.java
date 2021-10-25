package webcrawler.prueba.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ParseComputerPage {

    public void listAllPages(String url) throws IOException {
        System.out.println("obteniendo links de paginas de computadoras " + url + "...");

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        Elements pages = doc.select("li.page-item a[href]");  //quiero obtener informacion de la primera pagina
        //<a class="page-numbers" href="/?product-page=1">1</a>
        // \"https://www.intecsa.com.bo/?product-page=1 \"
        // a[href^=\"http://www.mkyong.com/page/\"]

        print("\nPages: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img"))
                //print para las imagenes encontradas, extrae: 1.tagName=nombre de la etiqueta, 2.src=link de img,
                //3.width,height=tamaño de la img, 4. alt = nombre de la imagen
                print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));
            else
                //print para los script encontrados
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        }


        print("\nPages: (%d)", pages.size());
        for (Element page : pages) {

                print(" * a: <%s> (%s)",page.attr("abs:href"), trim(page.text(), 35));
        }
    }


    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }

}
