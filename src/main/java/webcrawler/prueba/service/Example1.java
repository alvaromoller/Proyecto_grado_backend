package webcrawler.prueba.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Example1 {


    private HashSet<String> enlaces;
    private List<List<String>> articulos;

    public Example1() {
        enlaces = new HashSet<>();
        articulos = new ArrayList<>();
    }

    //Find all URLs that start with "http://www.mkyong.com/page/" and add them to the HashSet
    //Encuentra todas las URL que comienzan con "http://www.mkyong.com/page/" y agrégalas al HashSet
    public void obtenerEnlacesExtraidos(String URL) {
        if (!enlaces.contains(URL)) {
            try {
                Document documento = Jsoup.connect(URL).get();
                Elements enlacesWeb = documento.select("a[href^=\"https://mkyong.com/page/\"]");
                for (Element enlace : enlacesWeb) {
                    if (enlaces.add(URL)) {
                        //Remove the comment from the line below if you want to see it running on your editor
                        //Elimina el comentario de la línea de abajo si quieres verlo ejecutándose en tu editor
                        System.out.println(URL);
                    }
                    obtenerEnlacesExtraidos(enlace.attr("abs:href"));
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    //Connect to each link saved in the article and find all the articles in the page
    //Conéctese a cada enlace guardado en el artículo y encuentre todos los artículos en la página
    public void obtenerArticulos() {
        enlaces.forEach(x -> {
            Document document;
            try {
                document = Jsoup.connect(x).get();
                Elements enlacesArticulos = document.select("h2 a[href^=\"https://mkyong.com/\"]");
                for (Element articulo : enlacesArticulos) {
                    //Only retrieve the titles of the articles that contain Java 8
                    //Recuperar solo los títulos de los artículos que contienen Java 8
                    if (articulo.text().matches("^.*?(Java|java|JAVA).*$")) {
                        ArrayList<String> temporal = new ArrayList<>();
                        temporal.add(articulo.text());
                        temporal.add(articulo.attr("abs:href"));
                        articulos.add(temporal);
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });
    }
    public void escribirArchivo(String nombreArchivo) {
        FileWriter archivo;
        try {
            archivo = new FileWriter(nombreArchivo);
            articulos.forEach(a -> {
                try {
                    String str = "- Title: " + a.get(0) + " (link: " + a.get(1) + ")\n";
                    //display to console
                    //mostrar a la consola
                    System.out.println(str);
                    //Guardar en archivo
                    archivo.write(str);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
            archivo.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



}
