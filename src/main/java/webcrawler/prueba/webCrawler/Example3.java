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
             System.out.println("año: "+ anio.replaceAll( "[^\\d]", "" ));
         }
     }


     // 35+ 38 + 67 = 140 productos

    //ejemplo 3, 24 productos
    //CompuCenter,Equipos Laptos
    //https://compucenter.store/category/23-equipo/77-laptop
    public void compuCenterLaptops(String url) throws IOException {
        System.out.println("Equipos Laptos, " + url + "...");
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

            System.out.println("- nombre1(Marca): "+ nombre1);
            System.out.println("  nombre2:  "+ nombre2);
            System.out.println("  descripcion:  "+ descripcion);
            System.out.println("  precio:  "+ precio);
            System.out.println("  Imagen:   "+ imagen);
            System.out.println("");
        }
        System.out.println("-------------------");
    }

    //ejemplo 4, 11 productos
    //CompuCenter, Equipos Gaming
    //https://compucenter.store/category/23-equipo/238-gaming
    public void compuCenterGaming(String url) throws IOException {
        System.out.println("Equipos Gaming, " + url + "...");
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

            System.out.println("- nombre1(Marca): "+ nombre1);
            System.out.println("  nombre2:  "+ nombre2);
            System.out.println("  descripcion:  "+ descripcion);
            System.out.println("  precio:  "+ precio);
            System.out.println("  Imagen:   "+ imagen);
            System.out.println("");
        }
        System.out.println("-------------------");
    }
/////////////////////////////////////////////////////////////////////////////////
    //pC.COM : HP, Asus, Lenovo, Dell, Acer, MSi

    //ejemplo 5, 14 productos
    //Pc.com, HP
    //https://www.pc.com.bo/assets/html/notebooks-hp.html
    public void pcHp(String url) throws IOException {
        System.out.println("Pc.com HP, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(9000).get();
        Elements body = doc.select("section.grid");

        //Salto de linea descriptionPage
        /**
        body.select("br").append("\\nl"); //append salto de linea despues de un elemento
        body.select("div").append("\\nl");
        body.select("p").prepend("\\nl"); //append salto de linea Antes de un elemento
        */

        //nombre, imagen
        for (Element e : body.select("div.item"))
        {
            String nombre1 = e.select(" div.textosobreimagen h2  ").text();
            String nombre2 = e.select(" div.textosobreimagen p:matches(Intel|AMD) ").text(); //.replaceAll("\\\\nl", "\n")
            String descripcion = e.select(" div.textosobreimagen a ").attr("href");
            String precio = e.select(" div.textosobreimagen b ").text();
            String imagen = e.select(" div.item-contenido img ").attr("src");

            System.out.println("- nombre1(Marca): "+ nombre1);
            System.out.println("  nombre2(Procesador):  "+ nombre2 ); //con Precio Antiguo
            System.out.println("  descripcion:  "+ descripcion.replace("..", "https://www.pc.com.bo/assets"));
            System.out.println("  precio actual:  "+ precio);
            System.out.println("  Imagen:   "+ imagen.replace("..","https://www.pc.com.bo/assets"));

            /***
             * var cadena="ab x)$MpÑ1235 carW";
             * var nums =cadena.replace("[/\\D/g]",""); //obtener solo letras
             * var letrs = cadena.replace("[/\\d/g]",""); //obtener solo numeros
             *
             */
            System.out.println("");
        }
        System.out.println("-------------------");
    }

    //ejemplo 6, 11 productos
    //Pc.com, ASUS
    //https://www.pc.com.bo/assets/html/notebooks-asus.html
    public void pcAsus(String url) throws IOException {
        System.out.println("Pc.com ASUS, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(9000).get();
        Elements body = doc.select("section.grid");

        //nombre, imagen
        for (Element e : body.select("div.item"))
        {
            String nombre1 = e.select(" div.textosobreimagen h2  ").text();
            String nombre2 = e.select(" div.textosobreimagen p:matches(Intel|AMD) ").text(); //.replaceAll("\\\\nl", "\n")
            String descripcion = e.select(" div.textosobreimagen a ").attr("href");
            String precio = e.select(" div.textosobreimagen b ").text();
            String imagen = e.select(" div.item-contenido img ").attr("src");

            System.out.println("- nombre1(Marca): "+ nombre1);
            System.out.println("  nombre2(Procesador):  "+ nombre2 ); //con Precio Antiguo
            System.out.println("  descripcion:  "+ descripcion.replace("..", "https://www.pc.com.bo/assets"));
            System.out.println("  precio actual:  "+ precio);
            System.out.println("  Imagen:   "+ imagen.replace("..","https://www.pc.com.bo/assets"));

            System.out.println("");
        }
        System.out.println("-------------------");
    }

    //ejemplo 7, 5 productos
    //Pc.com, Lenovo
    //https://www.pc.com.bo/assets/html/notebooks-lenovo.html
    public void pcLenovo(String url) throws IOException {
        System.out.println("Pc.com Lenovo, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(9000).get();
        Elements body = doc.select("section.impresoras");

        //nombre, imagen
        for (Element e : body.select("div.col-md-4.d-flex.justify-content-center"))
        {
            String nombre1 = e.select(" div.textosobreimagen h2  ").text();
            String nombre2 = e.select(" div.textosobreimagen p:matches(Intel|AMD) ").text(); //.replaceAll("\\\\nl", "\n")
            String descripcion = e.select(" div.textosobreimagen a ").attr("href");
            String precio = e.select(" div.textosobreimagen b ").text();
            String imagen = e.select("  img ").attr("src");

            System.out.println("- nombre1(Marca): "+ nombre1);
            System.out.println("  nombre2(Procesador):  "+ nombre2 ); //con Precio Antiguo
            System.out.println("  descripcion:  "+ descripcion.replace("..", "https://www.pc.com.bo/assets"));
            System.out.println("  precio actual:  "+ precio);
            System.out.println("  Imagen:   "+ imagen.replace("..","https://www.pc.com.bo/assets"));

            System.out.println("");
        }
        System.out.println("-------------------");
    }

    //ejemplo 8, 2 productos
    //Pc.com, DELL
    //https://www.pc.com.bo/assets/html/notebooks-dell.html
    public void pcDell(String url) throws IOException {
        System.out.println("Pc.com Dell, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(9000).get();
        Elements body = doc.select("section.grid");

        //nombre, imagen
        for (Element e : body.select("div.item"))
        {
            String nombre1 = e.select(" div.textosobreimagen h2  ").text();
            String nombre2 = e.select(" div.textosobreimagen p:matches(Intel|AMD) ").text(); //.replaceAll("\\\\nl", "\n")
            String descripcion = e.select(" div.textosobreimagen a ").attr("href");
            String precio = e.select(" div.textosobreimagen b ").text();
            String imagen = e.select(" div.item-contenido img ").attr("src");

            System.out.println("- nombre1(Marca): "+ nombre1);
            System.out.println("  nombre2(Procesador):  "+ nombre2 ); //con Precio Antiguo
            System.out.println("  descripcion:  "+ descripcion.replace("d", "https://www.pc.com.bo/assets/html/d"));
            System.out.println("  precio actual:  "+ precio);
            System.out.println("  Imagen:   "+ imagen.replace("..","https://www.pc.com.bo/assets"));

            System.out.println("");
        }
        System.out.println("-------------------");
    }

    //ejemplo 9, 3 productos
    //Pc.com, ACER
    //https://www.pc.com.bo/assets/html/notebooks-acer.html
    public void pcAcer(String url) throws IOException {
        System.out.println("Pc.com Acer, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(9000).get();
        Elements body = doc.select("div.container");

        //nombre, imagen
        for (Element e : body.select("div.col-md-4.d-flex.justify-content-center"))
        {
            String nombre1 = e.select(" div.textosobreimagen h2  ").text();
            String nombre2 = e.select(" div.textosobreimagen p:matches(Intel|AMD) ").text(); //.replaceAll("\\\\nl", "\n")
            String descripcion = e.select(" div.textosobreimagen a ").attr("href");
            String precio = e.select(" div.textosobreimagen b ").text();
            String imagen = e.select(" img ").attr("src");

            System.out.println("- nombre1(Marca): "+ nombre1);
            System.out.println("  nombre2(Procesador):  "+ nombre2 ); //con Precio Antiguo
            System.out.println("  descripcion:  "+ descripcion.replace("..", "https://www.pc.com.bo/assets"));
            System.out.println("  precio actual:  "+ precio);
            System.out.println("  Imagen:   "+ imagen.replace("..","https://www.pc.com.bo/assets"));

            System.out.println("");
        }
        System.out.println("-------------------");
    }

    //ejemplo 10, 3 productos
    //Pc.com, MSI
    //https://www.pc.com.bo/assets/html/notebooks-msi.html
    public void pcMsi(String url) throws IOException {
        System.out.println("Pc.com MSI, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(9000).get();
        Elements body = doc.select("section.grid");

        //nombre, imagen
        for (Element e : body.select("div.item"))
        {
            String nombre1 = e.select(" div.textosobreimagen h2  ").text();
            String nombre2 = e.select(" div.textosobreimagen p:matches(Intel|AMD) ").text(); //.replaceAll("\\\\nl", "\n")
            String descripcion = e.select(" div.textosobreimagen a ").attr("href");
            String precio = e.select(" div.textosobreimagen b ").text();
            String imagen = e.select(" img ").attr("src");

            System.out.println("- nombre1(Marca): "+ nombre1);
            System.out.println("  nombre2(Procesador):  "+ nombre2 ); //con Precio Antiguo
            System.out.println("  descripcion:  "+ descripcion.replace("..", "https://www.pc.com.bo/assets"));
            System.out.println("  precio actual:  "+ precio);
            System.out.println("  Imagen:   "+ imagen.replace("..","https://www.pc.com.bo/assets"));

            System.out.println("");
        }
        System.out.println("-------------------");
    }

    //Pc.com, datos de tienda
    //https://www.pc.com.bo/index.html#
    public void pcTienda(String url) throws IOException {
        System.out.println("Pc.com Tienda, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(9000).get();
        Elements body = doc.select("div.box-contact-info");

        //ubicacion, descripcion
        String nombre = "PC.COM";
        String imagen = "https://www.pc.com.bo/assets/img/logo.png";
        for (Element e : body.select("li"))
        {
            String descripcion = e.select(" div.textosobreimagen a ").text();   //no tiene descripcion
            String location = e.select(" li span  ").text();
            //System.out.println("- descripcion:  "+ descripcion);
            System.out.println("  Ubicación: "+ location);
        }
        System.out.println("  Logo: "+ imagen);

        System.out.println("-------------------");
    }


/////////////////////////////////////////////////////////////////////////////////
    //Marcas: HP, Dell, Lenovo

    //ejemplo 11, 33 productos
    //PAgina, DISEÑO CREATIVO computación
    //https://creativocomputacion.ecwid.com/Notebooks-c10743110
    public void creativoLaptops(String url) throws IOException {
        System.out.println("DISEÑO CREATIVO Laptops, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(9000).get();
        Elements body = doc.select("div.grid__products ");

        //nombre, imagen
        for (Element e : body.select("div.grid-product "))
        {
            String nombre1 = e.select(" a.grid-product__title ").text(); //:matches(Lenovo|HP|Acer|ASUS|Sony|Dell)
            String nombre2 = e.select(" div.grid-product__sku-inner ").text(); //Averiguar que es: REF DEC8CN8T?
            String descripcion = e.select(" a.grid-product__title  ").attr("href");
            String precio = e.select(" div.grid-product__price ").text();
            String imagen = e.select(" div.grid-product__image-wrap img ").attr("src");

            System.out.println("- nombre1(Marca): "+ nombre1);
            System.out.println("  nombre2(Procesador):  "+ nombre2 ); //con Precio Antiguo
            System.out.println("  descripcion:  "+ descripcion);
            System.out.println("  precio actual:  "+ precio);
            System.out.println("  Imagen:   "+ imagen);

            System.out.println("");
        }
        System.out.println("-------------------");
    }

    //ejemplo 12, 6 productos
    //Página, DISEÑO CREATIVO HP
    //https://creativocomputacion.ecwid.com/Notebooks-HP-c10840325
    public void creativoHp(String url) throws IOException {
        System.out.println("DISEÑO CREATIVO Hp, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(9000).get();
        Elements body = doc.select("div.grid__products ");

        //nombre, imagen
        for (Element e : body.select("div.grid-product "))
        {
            String nombre1 = e.select(" a.grid-product__title ").text(); //:matches(Lenovo|HP|Acer|ASUS|Sony|Dell)
            String nombre2 = e.select(" div.grid-product__sku-inner ").text(); //Averiguar que es: REF DEC8CN8T?
            String descripcion = e.select(" a.grid-product__title  ").attr("href");
            String precio = e.select(" div.grid-product__price ").text();
            String imagen = e.select(" div.grid-product__image-wrap img ").attr("src");

            System.out.println("- nombre1(Marca): "+ nombre1);
            System.out.println("  nombre2(Procesador):  "+ nombre2 ); //con Precio Antiguo
            System.out.println("  descripcion:  "+ descripcion);
            System.out.println("  precio actual:  "+ precio);
            System.out.println("  Imagen:   "+ imagen);

            System.out.println("");
        }
        System.out.println("-------------------");
    }

    //ejemplo 13, 26 productos
    //Página, DISEÑO CREATIVO Dell
    //https://creativocomputacion.ecwid.com/Notebooks-Dell-c10840283
    public void creativoDell(String url) throws IOException {
        System.out.println("DISEÑO CREATIVO Dell, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(9000).get();
        Elements body = doc.select("div.grid__products ");

        //nombre, imagen
        for (Element e : body.select("div.grid-product "))
        {
            String nombre1 = e.select(" a.grid-product__title ").text(); //:matches(Lenovo|HP|Acer|ASUS|Sony|Dell)
            String nombre2 = e.select(" div.grid-product__sku-inner ").text(); //Averiguar que es: REF DEC8CN8T?
            String descripcion = e.select(" a.grid-product__title  ").attr("href");
            String precio = e.select(" div.grid-product__price ").text();
            String imagen = e.select(" div.grid-product__image-wrap img ").attr("src");

            System.out.println("- nombre1(Marca): "+ nombre1);
            System.out.println("  nombre2(Procesador):  "+ nombre2 ); //con Precio Antiguo
            System.out.println("  descripcion:  "+ descripcion);
            System.out.println("  precio actual:  "+ precio);
            System.out.println("  Imagen:   "+ imagen);

            System.out.println("");
        }
        System.out.println("-------------------");
    }

    //ejemplo 14, 2 productos
    //Página, DISEÑO CREATIVO Lenovo
    //https://creativocomputacion.ecwid.com/Lenovo-Notebooks-c20149225
    public void creativoLenovo(String url) throws IOException {
        System.out.println("DISEÑO CREATIVO Lenovo, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(9000).get();
        Elements body = doc.select("div.grid__products ");

        //nombre, imagen
        for (Element e : body.select("div.grid-product "))
        {
            String nombre1 = e.select(" a.grid-product__title ").text(); //:matches(Lenovo|HP|Acer|ASUS|Sony|Dell)
            String nombre2 = e.select(" div.grid-product__sku-inner ").text(); //Averiguar que es: REF DEC8CN8T?
            String descripcion = e.select(" a.grid-product__title  ").attr("href");
            String precio = e.select(" div.grid-product__price ").text();
            String imagen = e.select(" div.grid-product__image-wrap img ").attr("src");

            System.out.println("- nombre1(Marca): "+ nombre1);
            System.out.println("  nombre2(Procesador):  "+ nombre2 ); //con Precio Antiguo
            System.out.println("  descripcion:  "+ descripcion);
            System.out.println("  precio actual:  "+ precio);
            System.out.println("  Imagen:   "+ imagen);

            System.out.println("");
        }
        System.out.println("-------------------");
    }

    //Diseño creativo Computacion, datos de tienda
    //https://www.pc.com.bo/index.html#
    public void creativoTienda(String url) throws IOException {
        System.out.println("Creativo computacion Tienda, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(9000).get();
        Elements body = doc.select("div.et_pb_text_inner");

        //ubicacion, descripcion
        String nombre = "Computación, diseño creativo";
        String imagen = "https://creativo.com.bo/wp-content/uploads/2021/08/DisenoAprobadoLogoCreativoLB.png";
        for (Element e : body.select("div.x-el.x-el-span"))
        {
            String descripcion = e.select(" div.textosobreimagen a ").text();   //no tiene descripcion
            String location = e.select(" div.x-el.x-el-span  ").text();
            //System.out.println("- descripcion:  "+ descripcion);
            System.out.println("  Ubicación: "+ location);
        }
        System.out.println("  Logo: "+ imagen);

        System.out.println("-------------------");
    }


}
