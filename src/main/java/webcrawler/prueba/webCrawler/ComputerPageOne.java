package webcrawler.prueba.webCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.*;
import webcrawler.prueba.dto.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ComputerPageOne extends Thread {

    //BrandDao
    private BrandDao brandDao;
    private TransactionDao transactionDao;
    //ProductTypeDao
    private ProductTypeDao productTypeDao;
    //ShopDao
    private ShopDao shopDao;
    //ProductDao
    private ProductDao productDao;
    //ProductDetailDao
    private ProductDetailDao productDetailDao;  //precio
    //Obtencion de direcciones Ips
    private DirectionIp directionIp;
    //CompuCenter
    private String urlCompuCenter;          //CompuCenter
    private String urlCompuCenterGamer;
    private String urlTechStore;            //TechStore
    private String urlCreativoComputacion;  //CreativoComputacion
    private String urlDismac;               //Dismac
    //SimpMessagingTemplate
    private SimpMessagingTemplate template;


    @Autowired
    public ComputerPageOne(DirectionIp directionIp, BrandDao brandDao, ProductDao productDao, ProductTypeDao productTypeDao, ShopDao shopDao, ProductDetailDao productDetailDao, TransactionDao transactionDao) {
        this.brandDao = brandDao;
        this.productTypeDao = productTypeDao;
        this.shopDao = shopDao;
        this.productDao = productDao;
        this.productDetailDao = productDetailDao;
        this.transactionDao = transactionDao;
        this.directionIp = directionIp;
    }

    public ComputerPageOne(
            SimpMessagingTemplate template,
            String urlCompuCenter,
            String urlCompuCenterGamer,
            String urlTechStore,
            String urlCreativoComputacion,
            String urlDismac
            //String url6
    ) {
        this.template = template;
        this.urlCompuCenter = urlCompuCenter;
        this.urlCompuCenterGamer = urlCompuCenterGamer;
        this.urlTechStore = urlTechStore;
        this.urlCreativoComputacion = urlCreativoComputacion;
        this.urlDismac = urlDismac;
    }

    public ComputerPageOne() {
    }


//TIENDAS CompuCenter, Pc.com

//LISTADO de PRODUCTOS, extraccion de Producto  sin  BD

    //tienda , CompuCenter
    //Producto 6
    //List<ProductDto>
    public List<ProductDto> extractProductList6(String url) throws IOException {
        //System.out.println("Computadoras, Página CompuCenter url" + url );
        Document doc1 = Jsoup.connect(url).get();    //.timeout(10000).get();
        Elements productName = doc1.select(" div.w-full");
        Elements imgProduct = doc1.select("div.w-full");  //extraccion de imagen
        Elements productDescription = doc1.select("div.w-full.pt-4"); //extracion de detalle del PC
        Elements price = doc1.select(" div.w-full");

        //extracion del PC
        String name = "";
        String name2 = "";
        String img = "";
        String description = "";
        String description2 = "";
        String brand = "HP";
        String ram = "8GB";
        String processor = "Intel";
        String storage = "512GB";
        String tarjetaGrafica = "Tarjeta gráfica integrada";

        //productName2
        for (Element e : productName.select("div.mb-10")) {
            name2 = e.select("h1").text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre del PC: " + name);

        //productName //opcional
        for (Element e : productName.select("div.flex.text-black.cursor-pointer.pb-1")) {
            name = e.select("span").text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre2 opcional del PC: " + name2);


        //imgProduct
        for (Element e : imgProduct.select("div.relative.border-4.border-red-100.rounded-lg")) {
            img = e.select("  img ").attr("src"); //Obtener src, img del PC
            //System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("div.text-base")) {
            description = e.select(" div ").text(); //Obtener descripcion del PC
            //System.out.println("Descripción: \n" + description);
        }


        String precio = "";
        for (Element e : price.select("div.inline-block.align-bottom.mr-5")) {
            precio = e.select("span  ").text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double" );

        }
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        /**
         String precio2 = precio.replace("Bs" , "");
         System.out.println("Precio 2: " + precio2 + " , se quito exitosamente el Bs para convertir el precio en Double" );
         //se quito la coma
         String precio3 = precio2.replace(",", "");
         System.out.println("Precio 3: " + precio3 + " ,  se quito la coma ");
         Double precioConvertido = Double.parseDouble(precio3);
         System.out.println("precio Convertido a Double " + precioConvertido );
         */
        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        ProductDto productDto = new ProductDto();

        productDto.setProductId(200);
        productDto.setName(name);
        productDto.setName2(name2);
        productDto.setShopName("CompuCenter");
        productDto.setDescription(description);
        productDto.setDescription2(description2);
        productDto.setImg(img);
        productDto.setPrice(precio);
        productDto.setBrand(brand);
        productDto.setRam(ram);
        productDto.setProcessor(processor);
        productDto.setStorage(storage);
        productDto.setTarjetaGrafica(tarjetaGrafica);
        //llaves foraneas
        productDto.setShopId(2);

        productDtos.add(productDto);
        System.out.println("productDtos 6: " + productDtos);
        return productDtos;
    }


    //////////////////////////////////////////////////////////////////////
    //NUEVOS PRODUCTOS
    //CompuCenter, 24 PRODUCTOS
    //https://compucenter.store/category/23-equipo/77-laptop
    public List<ProductDto> compuCenterLaptops(String url) throws IOException {
        System.out.println("CompuCenter, Equipos Laptos, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(11000).get();
        Elements body = doc.select("section.flex.flex-wrap.justify-center.items-center");

        int contadorId=0;  //para tener los Ids

        //Lista para guardar los productos que recorrera el for
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        System.out.println("-------Inicio------------");
        for (Element e : body.select("div.flex.items-center.p-5.relative.w-full")) {
            String nombre1 = e.select(" div.flex.flex-col.mb-2 strong  ").text();
            String nombre2 = e.select(" h1.font-semibold.text-lg ").text();
            String descripcion = e.select(" div.text-base.text-justify ").text();
            String descripcion2 = e.select("  a ").attr("abs:href");
            String precio = e.select(" div.inline-block.align-bottom.mr-5 span ").text();
            String imagen = e.select(" div.relative.border-4.border-red-100 img  ").attr("src");

            //for() productDto CompuCenter:

            /**
             System.out.println("- nombre1(Marca): "+ nombre1);
             System.out.println("  nombre2:  "+ nombre2);
             System.out.println("  descripcion:  "+ descripcion);
             System.out.println("  descripcion2:  "+ descripcion2);
             System.out.println("  precio:  "+ precio);
             System.out.println("  Imagen:   "+ imagen);
             System.out.println("");
             */
            //Marca, procesador, memoria, almacenamiento, tarjeta grafica Opcional,
            //Marca
            String marca = e.select(" div.flex.flex-col.mb-2 strong").text().toUpperCase();   //extraccion de marca

            //Regular expression to find digits
            String regexMarca = "\\b(HP|LENOVO|ASUS|DELL|APPLE|MSI)\\b";
            //Compiling the regular expression
            Pattern patternMarca = Pattern.compile(regexMarca);
            //Retrieving the matcher object
            Matcher matcherMarca = patternMarca.matcher(marca);                         //identificacion de coincidencia de marca con el texto extraido
            if (matcherMarca.find()) {
                System.out.println("Match found Marca:" + matcherMarca.group());   //eliminar espacios vacios
                //System.out.println("- nombre1(Marca): "+ nombre1);
            } else {
                System.out.println("Match not found, no se encontro MARCA en nombre1:  "+ marca);
            }

            /////////////////////////////////////////////////////////////////////////
            //Memoria Ram
            String ram = e.select(" div.text-base.text-justify ").text().toUpperCase();

            //Regular expression to find digits
            String regexRam = "\\b(8\\s?GB|16\\s?GB|4\\s?GB|12\\s?GB |32\\s?GB\\,?)\\b";    //  Encontrar 8 GB con espacio opcional = 8\\s?GB
            //Compiling the regular expression
            Pattern patternRam = Pattern.compile(regexRam);
            //Retrieving the matcher object
            Matcher matcherRam = patternRam.matcher(ram);
            if (matcherRam.find()) {
                System.out.println("Match found Ram:" + matcherRam.group().replaceAll("[\\ \\\\]", ""));   //quitando espacio (16 GB) en medio de Ram
            } else {
                System.out.println("Match not found Ram en: " + ram);
            }

            /////////////////////////////////////////////////////////////////////////
            //Procesador
            String procesador = e.select(" div.text-base.text-justify ").text().toUpperCase();

            //Regular expression to find digits
            String regexProcesador = "\\b(INTEL|RYZEN|AMD)\\b";
            //Compiling the regular expression
            Pattern patternProcesador = Pattern.compile(regexProcesador);
            //Retrieving the matcher object
            Matcher matcherProcesador = patternProcesador.matcher(procesador);
            if (matcherProcesador.find()) {
                System.out.println("Match found procesador2:  " + matcherProcesador.group());
            } else {
                System.out.println("Match not found proccesor: " + procesador);
            }

            /////////////////////////////////////////////////////////////////////////
            //Almacenamiento
            String almacenamiento = e.select(" div.text-base.text-justify ").text().toUpperCase();

            //Regular expression to find digits
            String regexAlmacenamiento = "\\b(128\\s?GB |240\\s?GB |256\\s?GB\\,? |480\\s?GB |500\\s?GB |512\\s?GB |512GB\\,?|1000\\s?GB |\\d+\\s?TB|\\d+.?\\d+?\\s?TB)\\b";    //  Encontrar 8 GB con espacio opcional = 8\\s?GB
            //Compiling the regular expression
            Pattern patternAlmacenamiento = Pattern.compile(regexAlmacenamiento);
            //Retrieving the matcher object
            Matcher matcherAlmacenamiento = patternAlmacenamiento.matcher(almacenamiento);
            if (matcherAlmacenamiento.find()) {
                System.out.println("Match found Almacenamiento:  " + matcherAlmacenamiento.group().replaceAll("[\\ \\\\ ]", "").replaceAll("\\p{Punct}", "")  );   //quitando espacio (16 GB) en medio de Ram
            } else {
                System.out.println("Match not found Almacenamiento, no se encontro en almacenamiento :"+ almacenamiento);
                almacenamiento = e.select(" a h1.font-semibold.text-lg ").text().toUpperCase(); //nueva busqueda por nombre2
                System.out.println("nueva busqueda por nombre2: "+ almacenamiento);

                //Regular expression to find digits
                regexAlmacenamiento = "\\b(128\\s?GB |240\\s?GB |250\\s?GB |256\\s?GB\\,? |480\\s?GB |500\\s?GB |512\\s?GB |1000\\s?GB |\\d+\\s?TB|\\d+.?\\d+?\\s?TB)\\b";    //  Encontrar 8 GB con espacio opcional = 8\\s?GB
                //Compiling the regular expression
                patternAlmacenamiento = Pattern.compile(regexAlmacenamiento);
                //Retrieving the matcher object
                matcherAlmacenamiento = patternAlmacenamiento.matcher(almacenamiento);
                if (matcherAlmacenamiento.find()) {
                    System.out.println("Match found Almacenamiento:  " + matcherProcesador.group());
                } else {
                    System.out.println("Match not found ");
                }
            }


            /////////////////////////////////////////////////////////////////////////
            contadorId = contadorId + 1;    //para el Id
            //PRUEBA de ARRAY SIN BASE DE DATOS
            //List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
            ProductDto productDto = new ProductDto();
            productDto.setProductId(contadorId);//
            productDto.setName(nombre1);
            productDto.setName2(nombre2);
            productDto.setShopName("CompuCenter");
            productDto.setDescription(descripcion);
            productDto.setDescription2(descripcion2);
            productDto.setImg(imagen);
            productDto.setPrice(precio);
            //Usando expresiones regulares para buscar coincidencias
            productDto.setBrand(matcherMarca.group());  //matcherMarca.group()
            productDto.setRam(matcherRam.group().replaceAll("[\\ \\\\]", ""));
            productDto.setProcessor(matcherProcesador.group());
            productDto.setStorage(matcherAlmacenamiento.group().replaceAll("[\\ \\\\ ]", "").replaceAll("\\p{Punct}", "") ); //.replaceAll("\\p{Punct}", "") sirve para quitar todos los signos y puntuacicon
            //productDto.setTarjetaGrafica(tarjetaGrafica);
            //llaves foraneas
            productDto.setShopId(2);

            productDtos.add(productDto);
            System.out.println("for() productDto CompuCenter: " + productDto);   //muestra las listas de los productos por separado

        }

        System.out.println("-------Fin------------");
        System.out.println("CompuCenter productDtos: " + productDtos);  //muestra  el conjunto de listas de productos en una sola lista
        return productDtos;
    }

    //CompuCenter, 10 PRODUCTOS
    //https://compucenter.store/category/23-equipo/238-gaming#
    public List<ProductDto> compuCenterGamer(String url) throws IOException {
        System.out.println("CompuCenter, Gamer Laptos, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(11000).get();
        Elements body = doc.select("section.flex.flex-wrap.justify-center.items-center");

        int contadorId=25;  //para los IDs
        //Lista para guardar los productos que recorrera el for
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        System.out.println("-------Inicio------------");
        for (Element e : body.select("div.flex.items-center.p-5.relative.w-full")) {
            String nombre1 = e.select(" div.flex.flex-col.mb-2 strong  ").text();
            String nombre2 = e.select(" h1.font-semibold.text-lg ").text();
            String descripcion = e.select(" div.text-base.text-justify ").text();
            String descripcion2 = e.select("  a ").attr("abs:href");
            String precio = e.select(" div.inline-block.align-bottom.mr-5 span ").text();
            String imagen = e.select(" div.relative.border-4.border-red-100 img  ").attr("src");

            /**
             System.out.println("- nombre1(Marca): "+ nombre1);
             System.out.println("  nombre2:  "+ nombre2);
             System.out.println("  descripcion:  "+ descripcion);
             System.out.println("  precio:  "+ precio);
             System.out.println("  Imagen:   "+ imagen);
             System.out.println("");
             */
            //Marca, procesador, memoria, almacenamiento, tarjeta grafica Opcional,
            //Marca
            String marca = e.select(" div.flex.flex-col.mb-2 strong").text().toUpperCase();   //extraccion de marca

            //Regular expression to find digits
            String regexMarca = "\\b(HP|LENOVO|ASUS|DELL|APPLE|MSI)\\b";
            //Compiling the regular expression
            Pattern patternMarca = Pattern.compile(regexMarca);
            //Retrieving the matcher object
            Matcher matcherMarca = patternMarca.matcher(marca);                         //identificacion de coincidencia de marca con el texto extraido
            if (matcherMarca.find()) {
                System.out.println("Match found Marca:" + matcherMarca.group());   //eliminar espacios vacios
                //System.out.println("- nombre1(Marca): "+ nombre1);
            } else {
                System.out.println("Match not found");
                //System.out.println("- nombre1(Marca): "+ nombre1);
            }

            /////////////////////////////////////////////////////////////////////////
            //Memoria Ram
            String ram = e.select(" div.text-base.text-justify ").text().toUpperCase();

            //Regular expression to find digits
            String regexRam = "\\b(8\\s?GB|16\\s?GB|4\\s?GB|12\\s?GB)\\b";    //  Encontrar 8 GB con espacio opcional = 8\\s?GB
            //Compiling the regular expression
            Pattern patternRam = Pattern.compile(regexRam);
            //Retrieving the matcher object
            Matcher matcherRam = patternRam.matcher(ram);
            if (matcherRam.find()) {
                System.out.println("Match found Ram:" + matcherRam.group().replaceAll("[\\ \\\\]", ""));   //quitando espacio (16 GB) en medio de Ram
            } else {
                System.out.println("Match not found Ram");
                System.out.println("memoria ram no encontrada en: " + ram);
            }

            /////////////////////////////////////////////////////////////////////////
            //Procesador
            String procesador = e.select(" div.text-base.text-justify ").text().toUpperCase();    //busqueda en descripcion

            //Regular expression to find digits
            String regexProcesador = "\\b(INTEL|RYZEN)\\b";
            //Compiling the regular expression
            Pattern patternProcesador = Pattern.compile(regexProcesador);
            //Retrieving the matcher object
            Matcher matcherProcesador = patternProcesador.matcher(procesador);
            if (matcherProcesador.find()) {
                System.out.println("Match found procesador:  " + matcherProcesador.group());
            } else {
                System.out.println("Match not found procesador, no se encontro en :"+ procesador);

                procesador = e.select(" h1.font-semibold.text-lg ").text().toUpperCase(); //nueva busqueda por nombre2
                System.out.println("nueva busqueda por nombre2:"+procesador);

                //Regular expression to find digits
                regexProcesador = "\\b(INTEL|RYZEN)\\b";
                //Compiling the regular expression
                patternProcesador = Pattern.compile(regexProcesador);
                //Retrieving the matcher object
                matcherProcesador = patternProcesador.matcher(procesador);
                if (matcherProcesador.find()) {
                    System.out.println("Match found procesador2:  " + matcherProcesador.group());
                } else {
                    System.out.println("Match not found");
                }

            }

            /////////////////////////////////////////////////////////////////////////
            //Almacenamiento
            String almacenamiento = e.select(" div.text-base.text-justify ").text().toUpperCase();

            //Regular expression to find digits
            String regexAlmacenamiento = "\\b(128\\s?GB|240\\s?GB|256\\s?GB|500\\s?GB|512\\s?GB\\,?|\\d+\\s?TB|\\d+.?\\d+?\\s?TB)\\b";    //  Encontrar 8 GB con espacio opcional = 8\\s?GB
            //Compiling the regular expression
            Pattern patternAlmacenamiento = Pattern.compile(regexAlmacenamiento);
            //Retrieving the matcher object
            Matcher matcherAlmacenamiento = patternAlmacenamiento.matcher(almacenamiento);
            if (matcherAlmacenamiento.find()) {
                System.out.println("Match found Almacenamiento:  " + matcherAlmacenamiento.group().replaceAll("[\\ \\\\]", ""));   //quitando espacio (16 GB) en medio de Ram
            } else {
                System.out.println("Match not found Almacenamiento, no se encontro en :"+ almacenamiento);
                almacenamiento = e.select(" h1.font-semibold.text-lg ").text().toUpperCase();//nueva busqueda de almac. por nombre2
                System.out.println("nueva busqueda por nombre2:"+ almacenamiento);

                //Regular expression to find digits
                regexAlmacenamiento = "\\b(128\\s?GB |240\\s?GB |256\\s?GB |480\\s?GB |500\\s?GB |512\\s?GB |1000\\s?GB |\\d+\\s?TB|\\d+.?\\d+?\\s?TB)\\b";    //  Encontrar 8 GB con espacio opcional = 8\\s?GB
                //Compiling the regular expression
                patternAlmacenamiento = Pattern.compile(regexAlmacenamiento);
                //Retrieving the matcher object
                matcherAlmacenamiento = patternAlmacenamiento.matcher(almacenamiento);
                if (matcherAlmacenamiento.find()) {
                    System.out.println("Match found Almacenamiento en nombre 2:  " + matcherProcesador.group());
                } else {
                    System.out.println("Match not found en nombre 2");
                }

            }

            /////////////////////////////////////////////////////////////////////////
            contadorId=contadorId+1;

            //PRUEBA de ARRAY SIN BASE DE DATOS
            //List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
            ProductDto productDto = new ProductDto();
            productDto.setProductId(contadorId);//nombre1 + ""+ shop
            productDto.setName(nombre1);
            productDto.setName2(nombre2);
            productDto.setShopName("CompuCenter");
            productDto.setDescription(descripcion);
            productDto.setDescription2(descripcion2);
            productDto.setImg(imagen);
            productDto.setPrice(precio);
            //Usando expresiones regulares para buscar coincidencias
            productDto.setBrand(matcherMarca.group());  //matcherMarca.group()
            productDto.setRam(matcherRam.group().replaceAll("[\\ \\\\]", ""));
            productDto.setProcessor(matcherProcesador.group());
            productDto.setStorage(matcherAlmacenamiento.group().replaceAll("[\\ \\\\]", ""));
            //productDto.setTarjetaGrafica(tarjetaGrafica);
            //llaves foraneas
            productDto.setShopId(2);

            productDtos.add(productDto);
            System.out.println("for() productDto CompuCenter: " + productDto);   //muestra las listas de los productos por separado

        }

        System.out.println("-------Fin------------");
        System.out.println("CompuCenter Gamer productDtos: " + productDtos);  //muestra  el conjunto de listas de productos en una sola lista
        return productDtos;
    }

    //Techstore, 6 PRODUCTOS
    //https://techstore.bo/product-category/mac/macbool-air/
    public List<ProductDto> techStoreLaptops(String url) throws IOException {
        System.out.println("Techstore,  Laptos, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(11000).get();
        Elements body = doc.select("ul.products.columns-4.hongo-shop-standard");

        int contadorId=36;  //para los Ids
        //Lista para guardar los productos que recorrera el for
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        System.out.println("-------Inicio------------");
        for (Element e : body.select("li.hongo-alternate-image-wrap.product.type-product ")) {
            String nombre1 = e.select(" div.product-title-price-wrap a h2  ").text();
            String nombre2 ="";         //no tiene
            String descripcion = e.select(" div.product-title-price-wrap a h2").text();
            String descripcion2 = e.select(" div.product-title-price-wrap a ").attr("href");
            String precio = e.select(" span.price ").text();
            String imagen = e.select("a img.attachment-woocommerce_thumbnail  ").attr("src");

            /**
             System.out.println("- nombre1(Marca): "+ nombre1);
             System.out.println("  nombre2:  "+ nombre2);
             System.out.println("  descripcion:  "+ descripcion);
             System.out.println(" Techstore descripcion2:  "+ descripcion2);
             System.out.println("  precio:  "+ precio);
             System.out.println("  Imagen:   "+ imagen);
             System.out.println("");
             */
            //Marca, procesador, memoria, almacenamiento, tarjeta grafica Opcional,
            //Marca
            String marca = e.select(" div.product-title-price-wrap  ").text().toUpperCase(); //extraccion de marca

            //Regular expression to find digits
            String regexMarca = "\\b(HP|LENOVO|ASUS|DELL|APPLE|MSI|MACBOOK)\\b";
            //Compiling the regular expression
            Pattern patternMarca = Pattern.compile(regexMarca);
            //Retrieving the matcher object
            Matcher matcherMarca = patternMarca.matcher(marca);                         //identificacion de coincidencia de marca con el texto extraido
            if (matcherMarca.find()) {
                System.out.println("Match found Marca:" + matcherMarca.group());   //eliminar espacios vacios
                //System.out.println("- nombre1(Marca): "+ nombre1);
            } else {
                System.out.println("Match not found");
                //System.out.println("- nombre1(Marca): "+ nombre1);
            }

            /////////////////////////////////////////////////////////////////////////
            //Memoria Ram
            String ram = e.select(" div.product-title-price-wrap  ").text().toUpperCase(); //extraccion de Ram

            //Regular expression to find digits
            String regexRam = "\\b(8\\s?GB|16\\s?GB|4\\s?GB|12\\s?GB)\\b";    //  Encontrar 8 GB con espacio opcional = 8\\s?GB
            //Compiling the regular expression
            Pattern patternRam = Pattern.compile(regexRam);
            //Retrieving the matcher object
            Matcher matcherRam = patternRam.matcher(ram);
            if (matcherRam.find()) {
                System.out.println("Match found Ram:" + matcherRam.group().replaceAll("[\\ \\\\]", ""));   //quitando espacio (16 GB) en medio de Ram
            } else {
                System.out.println("Match not found Ram");
                System.out.println("memoria ram no encontrada en: " + ram);
            }

            /////////////////////////////////////////////////////////////////////////
            //Procesador
            String procesador = ("INTEL, RYZEN"); //extraccion de Procesador

            //Regular expression to find digits
            String regexProcesador = "\\b(INTEL|RYZEN)\\b";
            //Compiling the regular expression
            Pattern patternProcesador = Pattern.compile(regexProcesador);
            //Retrieving the matcher object
            Matcher matcherProcesador = patternProcesador.matcher(procesador);
            if (matcherProcesador.find()) {
                System.out.println("Match found procesador:  " + matcherProcesador.group());
            } else {
                System.out.println("Match not found");
            }

            /////////////////////////////////////////////////////////////////////////
            //Almacenamiento
            String almacenamiento = e.select(" div.product-title-price-wrap  ").text().toUpperCase(); //extraccion de Almacenamiento

            //Regular expression to find digits
            String regexAlmacenamiento = "\\b(128|256|128\\s?GB|240\\s?GB|256\\s?GB|500\\s?GB|512\\s?GB|\\d+\\s?TB|\\d+.?\\d+?\\s?TB)\\b";    //  Encontrar 8 GB con espacio opcional = 8\\s?GB
            //Compiling the regular expression
            Pattern patternAlmacenamiento = Pattern.compile(regexAlmacenamiento);
            //Retrieving the matcher object
            Matcher matcherAlmacenamiento = patternAlmacenamiento.matcher(almacenamiento);
            if (matcherAlmacenamiento.find()) {
                System.out.println("Match found Almacenamiento:  " + matcherAlmacenamiento.group().replaceAll("[\\ \\\\]", "").concat("GB"));   //quitando espacio (16 GB) en medio de Ram
            } else {
                System.out.println("Match not Almacenamiento: ");
                System.out.println("Almacenamiento no encontrado en: " + almacenamiento);

            }


            /////////////////////////////////////////////////////////////////////////
            contadorId = contadorId + 1;
            //PRUEBA de ARRAY SIN BASE DE DATOS
            //List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
            ProductDto productDto = new ProductDto();
            productDto.setProductId(contadorId);//nombre1 + ""+ shop
            productDto.setName(nombre1);
            productDto.setName2(nombre2);
            productDto.setShopName("TechStore");
            productDto.setDescription(descripcion);
            productDto.setDescription2(descripcion2);
            productDto.setImg(imagen);
            productDto.setPrice(precio);
            //Usando expresiones regulares para buscar coincidencias
            productDto.setBrand(matcherMarca.group());  //matcherMarca.group()
            productDto.setRam(matcherRam.group().replaceAll("[\\ \\\\]", ""));
            productDto.setProcessor(matcherProcesador.group());
            productDto.setStorage(matcherAlmacenamiento.group().replaceAll("[\\ \\\\]", "").concat("GB"));
            //productDto.setTarjetaGrafica(tarjetaGrafica);
            //llaves foraneas
            productDto.setShopId(3);

            productDtos.add(productDto);
            System.out.println("for() productDto Techstore: " + productDto);   //muestra las listas de los productos por separado

        }

        System.out.println("-------Fin------------");
        System.out.println("Techstore productDtos: " + productDtos);  //muestra  el conjunto de listas de productos en una sola lista
        return productDtos;
    }

    //Creativo Computacion NoteBooks
    //https://creativocomputacion.ecwid.com/Notebook-c10743110
    public List<ProductDto> creativoComputacion(String url) throws IOException {
        System.out.println("DISEÑO CREATIVO COMPUTACION, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(11000).get();
        Elements body = doc.select("div.grid__products ");
        String brand=" ";
        int contadorId = 43;    //Para Ids

        //Lista para guardar los productos que recorrera el for
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        System.out.println("-------Inicio------------");
        for (Element e : body.select("div.grid-product "))
        {
            String nombre1 = e.select(" a.grid-product__title ").text(); //:matches(Lenovo|HP|Acer|ASUS|Sony|Dell)
            String nombre2 = e.select(" div.grid-product__sku-inner ").text(); //Averiguar que es: REF DEC8CN8T?
            String descripcion = "Sin descripción previa"; //Sin descripción previa
            String precio = e.select(" div.grid-product__price ").text();
            String imagen = e.select(" div.grid-product__image-wrap img ").attr("src");

            String descripcion2 = e.select(" a.grid-product__title  ").attr("href");

            /**
             System.out.println("- nombre1(Marca): "+ nombre1);
             System.out.println("  nombre2(Procesador):  "+ nombre2 ); //con Precio Antiguo
             System.out.println("  descripcion:  "+ descripcion);
             System.out.println("  precio actual:  "+ precio);
             System.out.println("  Imagen:   "+ imagen);
             */
            //Marca, procesador, memoria, almacenamiento, tarjeta grafica Opcional,
            //Marca
            String marca = e.select(" a.grid-product__title ").text().toUpperCase();   //extraccion de marca

            //Regular expression to find digits
            String regexMarca = "\\b(HP|LENOVO|ASUS|DELL|APPLE|MSI|THINKPAD)\\b";  //ThinkPad es el modelo de la marca Lenovo
            //Compiling the regular expression
            Pattern patternMarca = Pattern.compile(regexMarca);
            //Retrieving the matcher object
            Matcher matcherMarca = patternMarca.matcher(marca);                         //identificacion de coincidencia de marca con el texto extraido
            if (matcherMarca.find()) {
                System.out.println("Match found Marca:" + matcherMarca.group());   //eliminar espacios vacios
                //System.out.println("- nombre1(Marca): "+ nombre1);
            } else {
                System.out.println("Match not found");
                System.out.println("- nombre1(Marca): " + marca);
            }

            /////////////////////////////////////////////////////////////////////////
            contadorId = contadorId + 1;
            //List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
            ProductDto productDto = new ProductDto();
            productDto.setProductId(contadorId);//nombre1 + ""+ shop
            productDto.setName(nombre1);
            productDto.setName2(nombre2);
            productDto.setShopName("Creativo Computación");
            productDto.setDescription(descripcion);
            productDto.setDescription2(descripcion2);
            productDto.setImg(imagen);
            productDto.setPrice(precio);
            //Usando expresiones regulares para buscar coincidencias
            productDto.setBrand(matcherMarca.group());
            productDto.setRam("ram");
            productDto.setProcessor("processor");
            productDto.setStorage("storage");
            //productDto.setTarjetaGrafica(tarjetaGrafica);
            //llaves foraneas
            productDto.setShopId(5);

            productDtos.add( productDto);
            System.out.println(" Creativo for(): " + productDto);   //muestra las listas de los productos por separado
        }
        System.out.println("-------Fin------------");
        System.out.println("Creativo productDtos: " + productDtos);  //muestra  el conjunto de listas de productos en una sola lista
        return  productDtos;
    }

    //Creativo 2
    //https://creativocomputacion.ecwid.com/Notebook-c10743110
    public List<ProductDto> creativoComputacion2(String url) throws IOException {
        System.out.println("DISEÑO CREATIVO COMPUTACION 2, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(11000).get();
        Elements body = doc.select("div.grid__products ");
        Elements body2 = doc.select("div.grid__products ");     //2da busqueda, por producto individual

        String brand=" ";
        int contadorId = 43;    //Para Ids

        //Lista para guardar los productos que recorrera el for
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        System.out.println("-------Inicio------------");
        for (Element e : body.select("div.grid-product "))
        {
            String descripcion2 = e.select(" a.grid-product__title  ").attr("href"); //se obtiene la direccion del PC
             body2 = doc.select("div.grid__products ");

        }

        for (Element e : body.select("div.grid-product "))
        {
            String nombre1 = e.select(" a.grid-product__title ").text(); //:matches(Lenovo|HP|Acer|ASUS|Sony|Dell)
            String nombre2 = e.select(" div.grid-product__sku-inner ").text(); //Averiguar que es: REF DEC8CN8T?
            String descripcion = "Sin descripción previa"; //Sin descripción previa
            String precio = e.select(" div.grid-product__price ").text();
            String imagen = e.select(" div.grid-product__image-wrap img ").attr("src");

            String descripcion2 = e.select(" a.grid-product__title  ").attr("href");

            /**
             System.out.println("- nombre1(Marca): "+ nombre1);
             */
            //Marca, procesador, memoria, almacenamiento, tarjeta grafica Opcional,
            //Marca
            String marca = e.select(" a.grid-product__title ").text().toUpperCase();   //extraccion de marca

            //Regular expression to find digits
            String regexMarca = "\\b(HP|LENOVO|ASUS|DELL|APPLE|MSI|THINKPAD)\\b";  //ThinkPad es el modelo de la marca Lenovo
            //Compiling the regular expression
            Pattern patternMarca = Pattern.compile(regexMarca);
            //Retrieving the matcher object
            Matcher matcherMarca = patternMarca.matcher(marca);                         //identificacion de coincidencia de marca con el texto extraido
            if (matcherMarca.find()) {
                System.out.println("Match found Marca:" + matcherMarca.group());   //eliminar espacios vacios
                //System.out.println("- nombre1(Marca): "+ nombre1);
            } else {
                System.out.println("Match not found");
                System.out.println("- nombre1(Marca): " + marca);
            }

            /////////////////////////////////////////////////////////////////////////
            contadorId = contadorId + 1;
            //List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
            ProductDto productDto = new ProductDto();
            productDto.setProductId(contadorId);//nombre1 + ""+ shop
            productDto.setName(nombre1);
            productDto.setName2(nombre2);
            productDto.setShopName("Creativo Computación");
            productDto.setDescription(descripcion);
            productDto.setDescription2(descripcion2);
            productDto.setImg(imagen);
            productDto.setPrice(precio);
            //Usando expresiones regulares para buscar coincidencias
            productDto.setBrand(matcherMarca.group());
            productDto.setRam("ram");
            productDto.setProcessor("processor");
            productDto.setStorage("storage");
            //productDto.setTarjetaGrafica(tarjetaGrafica);
            //llaves foraneas
            productDto.setShopId(5);

            productDtos.add( productDto);
            System.out.println(" Creativo for(): " + productDto);   //muestra las listas de los productos por separado
        }
        System.out.println("-------Fin------------");
        System.out.println("Creativo productDtos: " + productDtos);  //muestra  el conjunto de listas de productos en una sola lista
        return  productDtos;
    }

    //Dismac
    //https://www.dismac.com.bo/categorias/54-electronica/computacion/computadoras.html
    public List<ProductDto> dismacComputadoras(String url) throws IOException {
        System.out.println("Dismac Computadoras, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(11000).get();
        Elements body = doc.select("div.recent-slider.products-category ");
        String brand=" ";
        int contadorId = 76;    //Para Ids

        //Lista para guardar los productos que recorrera el for
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        System.out.println("-------Inicio Dismac------------");
        for (Element e : body.select("div.recent-container.list-view "))
        {
            String nombre1 = e.select(" strong.product-item-name a ").text(); //:matches(Lenovo|HP|Acer|ASUS|Sony|Dell)
            String nombre2 = e.select(" div.sku-container.sku span ").text(); //Averiguar que es: REF DEC8CN8T?
            String descripcion = "Sin descripción previa"; //Sin descripción previa
            String precio = e.select(" div.price-now ").text();
            String imagen = e.select(" a.img-product img ").attr("src");

            String descripcion2 = e.select(" strong.product-item-name a  ").attr("href");

            /**
             System.out.println("- nombre1(Marca): "+ nombre1);
             System.out.println("  nombre2(Procesador):  "+ nombre2 ); //con Precio Antiguo
             System.out.println("  descripcion:  "+ descripcion);
             System.out.println("  precio actual:  "+ precio);
             System.out.println("  Imagen:   "+ imagen);
             */
            //Marca, procesador, memoria, almacenamiento, tarjeta grafica Opcional,
            //Marca
            String marca = e.select(" strong.product-item-name a ").text().toUpperCase();   //extraccion de marca

            //Regular expression to find digits
            String regexMarca = "\\b(HP|HPCORE|LENOVO|ASUS|DELL|APPLE|MSI|HUAWEI|MICROSOFT|SAMSUNG)\\b";  //ThinkPad es el modelo de la marca Lenovo
            //Compiling the regular expression
            Pattern patternMarca = Pattern.compile(regexMarca);
            //Retrieving the matcher object
            Matcher matcherMarca = patternMarca.matcher(marca);                         //identificacion de coincidencia de marca con el texto extraido
            if (matcherMarca.find()) {
                System.out.println("Match found Marca:" + matcherMarca.group());   //eliminar espacios vacios
                //System.out.println("- nombre1(Marca): "+ nombre1);
            } else {
                System.out.println("Match not found, no se encontro marca en nombre1:  "+ marca);
            }

            /////////////////////////////////////////////////////////////////////////
            contadorId = contadorId + 1;
            //List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
            ProductDto productDto = new ProductDto();
            productDto.setProductId(contadorId);//nombre1 + ""+ shop
            productDto.setName(nombre1);
            productDto.setName2(nombre2);
            productDto.setShopName("Dismac");
            productDto.setDescription(descripcion);
            productDto.setDescription2(descripcion2);
            productDto.setImg(imagen);
            productDto.setPrice(precio);
            //Usando expresiones regulares para buscar coincidencias
            productDto.setBrand("matcherMarca.group()");
            productDto.setRam("ram");
            productDto.setProcessor("processor");
            productDto.setStorage("storage");
            //productDto.setTarjetaGrafica(tarjetaGrafica);
            //llaves foraneas
            productDto.setShopId(1);

            productDtos.add( productDto);
            System.out.println(" Dismac for(): " + productDto);   //muestra las listas de los productos por separado
        }
        System.out.println("-------Fin------------");
        System.out.println("Dismac productDtos: " + productDtos);  //muestra  el conjunto de listas de productos en una sola lista
        return  productDtos;
    }

    public List<ProductDto> dismacComputadoras2(String url) throws IOException {
        System.out.println("Dismac Computadoras, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(11000).get();
        Elements body = doc.select("div.recent-slider.products-category ");
        //Elements body2 = doc.select("div.product.attribute.description");
        String brand=" ";
        //String descripcion2=""; // debe estar afuera para cada una de las PCs
        int contadorId = 76;    //Para Ids

        //Lista para guardar los productos que recorrera el for
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        System.out.println("-------Inicio Dismac------------");
        for (Element e : body.select("div.recent-container.list-view "))
        {
            /*
            String nombre1 = e.select(" strong.product-item-name a ").text(); //:matches(Lenovo|HP|Acer|ASUS|Sony|Dell)
            String nombre2 = e.select(" div.sku-container.sku span ").text(); //Averiguar que es: REF DEC8CN8T?
            String descripcion = "Sin descripción previa"; //Sin descripción previa
            String precio = e.select(" div.price-now ").text();
            String imagen = e.select(" a.img-product img ").attr("src");
            String descripcion2 = e.select(" strong.product-item-name a  ").attr("href");
            */
            /**
             System.out.println("- nombre1(Marca): "+ nombre1);
             System.out.println("  nombre2(Procesador):  "+ nombre2 ); //con Precio Antiguo
             System.out.println("  descripcion:  "+ descripcion);
             System.out.println("  precio actual:  "+ precio);
             System.out.println("  Imagen:   "+ imagen);
             */

            String descripcion2 = e.select(" strong.product-item-name a  ").attr("href");
            Elements body2 = doc.select(descripcion2);  //intento ingresar al link de la descripcion de la PC
            for (Element e2 : body2.select("div.recent-slider.products-category p.MsoNormal"))  //estructura de la descripcion de la Pc individual
            {
            //Marca, procesador, memoria, almacenamiento, tarjeta grafica Opcional,
            //Marca
            String marca = e.select(" span ").text().toUpperCase();   //extraccion de marca

            //Regular expression to find digits
            String regexMarca = "\\b(HP|HPCORE|LENOVO|ASUS|DELL|APPLE|MSI|HUAWEI|MICROSOFT|SAMSUNG)\\b";  //ThinkPad es el modelo de la marca Lenovo
            //Compiling the regular expression
            Pattern patternMarca = Pattern.compile(regexMarca);
            //Retrieving the matcher object
            Matcher matcherMarca = patternMarca.matcher(marca);                         //identificacion de coincidencia de marca con el texto extraido
            if (matcherMarca.find()) {
                System.out.println("Match found Marca:" + matcherMarca.group());   //eliminar espacios vacios
                //System.out.println("- nombre1(Marca): "+ nombre1);
            } else {
                System.out.println("Match not found, no se encontro marca en nombre1:  "+ marca);
            }

            /////////////////////////////////////////////////////////////////////////
            contadorId = contadorId + 1;
            //List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
            ProductDto productDto = new ProductDto();
            productDto.setProductId(contadorId);//nombre1 + ""+ shop
            productDto.setName("nombre1");
            productDto.setName2("nombre2");
            productDto.setShopName("Dismac");
            productDto.setDescription("descripcion");
            productDto.setDescription2(descripcion2);
            productDto.setImg("imagen");
            productDto.setPrice("precio");
            //Usando expresiones regulares para buscar coincidencias
            productDto.setBrand("matcherMarca.group()");
            productDto.setRam("ram");
            productDto.setProcessor("processor");
            productDto.setStorage("storage");
            //productDto.setTarjetaGrafica(tarjetaGrafica);
            //llaves foraneas
            productDto.setShopId(1);

            productDtos.add( productDto);
            System.out.println(" Dismac for(): " + productDto);   //muestra las listas de los productos por separado

            }
        }
        System.out.println("-------Fin------------");
        System.out.println("Dismac productDtos: " + productDtos);  //muestra  el conjunto de listas de productos en una sola lista
        return  productDtos;
    }


    //LISTADO DE TODOS LOS PRODUCTOS
    //Para el Api, productos para home y cuestionario
    //, String url6
    public List<ProductDto> productListAllPrueba(String urlCompuCenter, String urlCompuCenterGamer,
                                                 String urlTechStore,
                                                 String urlCreativoComputacion,
                                                 String urlDismac) throws IOException {
        //Creacion de arrayList
        List<ProductDto> productsCompuCenter = new ArrayList<ProductDto>();
        List<ProductDto> productsCompuCenterGamer = new ArrayList<ProductDto>();
        List<ProductDto> productsTechStore = new ArrayList<ProductDto>();
        List<ProductDto> productsCreativoComputacion = new ArrayList<ProductDto>();
        List<ProductDto> productsDismac = new ArrayList<ProductDto>();

        //llamamos a los metodos
        //CompuCenter
        productsCompuCenter = compuCenterLaptops(urlCompuCenter);
        //CompuCenter Gamer
        productsCompuCenterGamer = compuCenterGamer(urlCompuCenterGamer);
        //TechStore
        productsTechStore = techStoreLaptops(urlTechStore);
        // creativoComputacion
        productsCreativoComputacion = creativoComputacion(urlCreativoComputacion);
        //Dismac
        productsDismac = dismacComputadoras(urlDismac);

        //agregamos en una sola lista
        List<ProductDto> productAll = new ArrayList<ProductDto>(); // Lista para guardar todos los productos
        productAll.addAll(productsCompuCenter);
        productAll.addAll(productsCompuCenterGamer);
        productAll.addAll(productsTechStore);
        productAll.addAll(productsCreativoComputacion);
        productAll.addAll(productsDismac);

        //System.out.println("product all: " + productAll);

        return productAll;
    }

    //////////////////////////////////////////////////////////////////////
    //Nuevo Metodo PARA EL HILO
    public List<ProductDto> productListAllHilo() {
        //Creacion de arrayList
        List<ProductDto> productsCompuCenter = new ArrayList<ProductDto>();
        List<ProductDto> productsCompuCenterGamer = new ArrayList<ProductDto>();
        List<ProductDto> productsTechStore = new ArrayList<ProductDto>();
        List<ProductDto> productsCreativoComputacion = new ArrayList<ProductDto>();
        List<ProductDto> productsDismac = new ArrayList<ProductDto>();

        //List<ProductDto> productDtos6 = new ArrayList<ProductDto>();


        //Llamamos a los metodos
        try {
            //CompuCenter
            productsCompuCenter = compuCenterLaptops(urlCompuCenter);
            //CompuCenter Gamer
            productsCompuCenterGamer = compuCenterGamer(urlCompuCenterGamer);
            //TechStore
            productsTechStore = techStoreLaptops(urlTechStore);
            // creativoComputacion
            productsCreativoComputacion = creativoComputacion(urlCreativoComputacion);
            //Dismac
            productsDismac = dismacComputadoras(urlDismac);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //agregamos en una sola lista
        List<ProductDto> productAll = new ArrayList<ProductDto>(); // Lista para guardar todos los productos
        productAll.addAll(productsCompuCenter);
        productAll.addAll(productsCompuCenterGamer);
        productAll.addAll(productsTechStore);
        productAll.addAll(productsCreativoComputacion);
        productAll.addAll(productsDismac);
        //System.out.println("product all: " + productAll);

        return productAll;
    }

    //Creacion metodo para HILO con actualizacion
    public void run() {
        //int i2 = 0;
        while (true) {
            //Enviand mensaje de prueba a la consola en frontend
            //Greeting greeting = new Greeting("Hello word, " );
            //this.template.convertAndSend("/topic/greetings", greeting);

            //Compu Center
            //Prueba hilo1
            System.out.println("  Thread is running...");
            List<ProductDto> objHome = new ArrayList<ProductDto>();
            objHome = productListAllHilo();                                     //Se llama al metodo productListAllHilo() que contiene los productos
            this.template.convertAndSend("/topic/products", objHome);  //envio de productos extraidos a la consola en frontend
            System.out.println(" Fin Hilo objHome");

            try {
                //Thread, Wait for one sec so it doesn't print too fast
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //return obj2;
        }//fin while
    }

    //Prueba ,probando hilo, con un for
    public void run2() {
        //int i = 0;
        while (true) {
            //System.out.println(this.getName() + ": New Thread is running..." + i2++);
            System.out.println(" Thread is running...");
            for (int i = 0; i < 5; i++) {
                System.out.println("Proceso 1, " + i);
            }

            try {
                //Wait for one sec so it doesn't print too fast
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    //////////////////////////////////////////////////////////////////////
    //NUEVAS MARCAS
    //Creativo Computacion, 34 productos, Hp=6, Dell=26, Lenovo=2
    //Hp
    //https://creativocomputacion.ecwid.com/Notebooks-HP-c10840325
    public List<ProductDto> creativoHp(String url) throws IOException {
        System.out.println("DISEÑO CREATIVO Hp, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(11000).get();
        Elements body = doc.select("div.grid__products ");
        String brand="HP";
        int contadorId = 81;    //Para Ids

        //Lista para guardar los productos que recorrera el for
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        System.out.println("-------Inicio------------");
        for (Element e : body.select("div.grid-product "))
        {
            String nombre1 = e.select(" a.grid-product__title ").text(); //:matches(Lenovo|HP|Acer|ASUS|Sony|Dell)
            String nombre2 = e.select(" div.grid-product__sku-inner ").text(); //Averiguar que es: REF DEC8CN8T?
            String descripcion = "Sin descripción previa"; //Sin descripción previa
            String precio = e.select(" div.grid-product__price ").text();
            String imagen = e.select(" div.grid-product__image-wrap img ").attr("src");

            String descripcion2 = e.select(" a.grid-product__title  ").attr("href");

            /**
            System.out.println("- nombre1(Marca): "+ nombre1);
            System.out.println("  nombre2(Procesador):  "+ nombre2 ); //con Precio Antiguo
            System.out.println("  descripcion:  "+ descripcion);
            System.out.println("  precio actual:  "+ precio);
            System.out.println("  Imagen:   "+ imagen);
            */
            /////////////////////////////////////////////////////////////////////////
            contadorId = contadorId + 1;
            //PRUEBA de ARRAY SIN BASE DE DATOS
            //List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
            ProductDto productDto = new ProductDto();
            productDto.setProductId(contadorId);//nombre1 + ""+ shop
            productDto.setName(nombre1);
            productDto.setName2(nombre2);
            productDto.setShopName("Creativo Computación");
            productDto.setDescription(descripcion);
            productDto.setDescription2(descripcion2);
            productDto.setImg(imagen);
            productDto.setPrice(precio);
            productDto.setBrand(brand);
            //productDto.setRam(ram);
            //productDto.setProcessor(processor);
            //productDto.setStorage(storage);
            //productDto.setTarjetaGrafica(tarjetaGrafica);
            //llaves foraneas
            productDto.setShopId(5);

            productDtos.add( productDto);
            System.out.println(" Creativo Hp for(): " + productDto);   //muestra las listas de los productos por separado
        }
        System.out.println("-------Fin------------");
        System.out.println("Creativo Hp productDtos: " + productDtos);  //muestra  el conjunto de listas de productos en una sola lista
        return  productDtos;
    }
    //Dell
    //https://creativocomputacion.ecwid.com/Notebooks-Dell-c10840283
    public List<ProductDto> creativoDell(String url) throws IOException {
        System.out.println("DISEÑO CREATIVO Dell, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(11000).get();
        Elements body = doc.select("div.grid__products ");
        String brand="DELL";
        int contadorId = 88;    //Para Ids

        //Lista para guardar los productos que recorrera el for
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        System.out.println("-------Inicio------------");
        for (Element e : body.select("div.grid-product "))
        {
            String nombre1 = e.select(" a.grid-product__title ").text(); //:matches(Lenovo|HP|Acer|ASUS|Sony|Dell)
            String nombre2 = e.select(" div.grid-product__sku-inner ").text(); //Averiguar que es: REF DEC8CN8T?
            String descripcion = "Sin descripción previa";  //Sin descripción previa
            String precio = e.select(" div.grid-product__price ").text();
            String imagen = e.select(" div.grid-product__image-wrap img ").attr("src");

            String descripcion2 = e.select(" a.grid-product__title  ").attr("href");

            /**
            System.out.println("- nombre1(Marca): "+ nombre1);
            System.out.println("  nombre2(Procesador):  "+ nombre2 ); //con Precio Antiguo
            System.out.println("  descripcion:  "+ descripcion);
            System.out.println("  precio actual:  "+ precio);
            System.out.println("  Imagen:   "+ imagen);
             */
            /////////////////////////////////////////////////////////////////////////
            contadorId = contadorId + 1;
            //PRUEBA de ARRAY SIN BASE DE DATOS
            //List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
            ProductDto productDto = new ProductDto();
            productDto.setProductId(contadorId);//nombre1 + ""+ shop
            productDto.setName(nombre1);
            productDto.setName2(nombre2);
            productDto.setShopName("Creativo Computación");
            productDto.setDescription(descripcion);
            productDto.setDescription2(descripcion2);
            productDto.setImg(imagen);
            productDto.setPrice(precio);
            productDto.setBrand(brand);
            //productDto.setRam(ram);
            //productDto.setProcessor(processor);
            //productDto.setStorage(storage);
            //productDto.setTarjetaGrafica(tarjetaGrafica);
            //llaves foraneas
            productDto.setShopId(5);

            productDtos.add( productDto);
            System.out.println(" Creativo Dell for(): " + productDto);   //muestra las listas de los productos por separado
        }
        System.out.println("-------Fin------------");
        System.out.println("Creativo Dell productDtos: " + productDtos);  //muestra  el conjunto de listas de productos en una sola lista
        return  productDtos;
    }
    //Lenovo
    //https://creativocomputacion.ecwid.com/Lenovo-Notebooks-c20149225
    public List<ProductDto> creativoLenovo(String url) throws IOException {
        System.out.println("DISEÑO CREATIVO Lenovo, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(11000).get();
        Elements body = doc.select("div.grid__products ");
        String brand="Lenovo";
        int contadorId = 114;    //Para Ids

        //Lista para guardar los productos que recorrera el for
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        System.out.println("-------Inicio------------");
        for (Element e : body.select("div.grid-product "))
        {
            String nombre1 = e.select(" a.grid-product__title ").text(); //:matches(Lenovo|HP|Acer|ASUS|Sony|Dell)
            String nombre2 = e.select(" div.grid-product__sku-inner ").text(); //Averiguar que es: REF DEC8CN8T?
            String descripcion = "Sin descripción previa";  //Sin descripción previa
            String precio = e.select(" div.grid-product__price ").text();
            String imagen = e.select(" div.grid-product__image-wrap img ").attr("src");

            String descripcion2 = e.select(" a.grid-product__title  ").attr("href");

            /**
            System.out.println("- nombre1(Marca): "+ nombre1);
            System.out.println("  nombre2(Procesador):  "+ nombre2 ); //con Precio Antiguo
            System.out.println("  descripcion:  "+ descripcion);
            System.out.println("  precio actual:  "+ precio);
            System.out.println("  Imagen:   "+ imagen);
            */
            /////////////////////////////////////////////////////////////////////////
            //PRUEBA de ARRAY SIN BASE DE DATOS
            contadorId = contadorId + 1;
            //List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
            ProductDto productDto = new ProductDto();
            productDto.setProductId(contadorId);//nombre1 + ""+ shop
            productDto.setName(nombre1);
            productDto.setName2(nombre2);
            productDto.setShopName("Creativo Computación");
            productDto.setDescription(descripcion);
            productDto.setDescription2(descripcion2);
            productDto.setImg(imagen);
            productDto.setPrice(precio);
            productDto.setBrand(brand);
            //productDto.setRam(ram);
            //productDto.setProcessor(processor);
            //productDto.setStorage(storage);
            //productDto.setTarjetaGrafica(tarjetaGrafica);
            //llaves foraneas
            productDto.setShopId(5);

            productDtos.add( productDto);
            System.out.println(" Creativo Lenovo for(): " + productDto);   //muestra las listas de los productos por separado
        }
        System.out.println("-------Fin------------");
        System.out.println("Creativo Lenovo productDtos: " + productDtos);  //muestra  el conjunto de listas de productos en una sola lista
        return  productDtos;
    }

    //////////////////////////////////////////////////////////////////////
    //NUEVO, metodo para el Api para marcas
    /**
    public List<ProductDto> productListAllMarcas( String urlCreativoHp, String urlCreativoDell, String urlCreativoLenovo) throws IOException{
        //Creacion de listas

        List<ProductDto> productsCreativoHp = new ArrayList<ProductDto>();
        List<ProductDto> productsCreativoDell = new ArrayList<ProductDto>();
        List<ProductDto> productsCreativoLenovo = new ArrayList<ProductDto>();

        // Guardado de metodos en las listas
        productsCreativoHp = creativoHp(urlCreativoHp);
        productsCreativoDell = creativoDell(urlCreativoDell);
        productsCreativoLenovo = creativoLenovo(urlCreativoLenovo);

        // Guardar el conjunto de listas en una sola lista
        List<ProductDto> productAll = new ArrayList<ProductDto>(); // Lista para guardar todos los productos
        productAll.addAll(productsCreativoHp);
        productAll.addAll(productsCreativoDell);
        productAll.addAll(productsCreativoLenovo);
        //System.out.println("product all: " + productAll);

        return  productAll;
    }
    */

    //////////////////////////////////////////////////////////////////////
    //Probando Seleccionar productos por tienda

    //LISTADO ProductStore  por ShopId
    //llamando al metodo productListAllPrueba, productListAllMarcas
    //String urlHp, String urlDell, String urlLenovo, String urlAsus,
    public List<ProductDto> selectProductsByStore(Integer shopId,  String urlCompuCenter, String urlCompuCenterGamer, String urlTechStore, String urlCreativoComputacion, String urlDismac,
                                                  //String urlCreativoHp, String urlCreativoDell, String urlCreativoLenovo,
                                                  String urlTiendaHpHogar,   String urlCompuCenterGamer2,  String urlTiendaHpEmpresas )throws IOException{

        List<ProductDto> array1 = productListAllPrueba( urlCompuCenter, urlCompuCenterGamer,  urlTechStore, urlCreativoComputacion, urlDismac);                                // array1 guardara el metodo productListAllPrueba, tiendas CompuCenter y Techstore
        //List<ProductDto> array2 = productListAllMarcas( urlCreativoHp, urlCreativoDell, urlCreativoLenovo);                                 // array2 guardara el metodo productListAllMarcas, Tiendas PC.COM y creativo computacion
        ComputerPageTwo computerPageTwo = new ComputerPageTwo();
        List<ProductDto> array3 = computerPageTwo.productListAll( urlTiendaHpHogar, urlCompuCenterGamer2,  urlTiendaHpEmpresas);            // array3 guardara el metodo productListAll, de ComputerPageTwo, tiendas CompuCenter y Hp

        List<ProductDto> productAux = new ArrayList<ProductDto>();      //En productAux se guarda la lista productDtosFor, return productAux

        for(int i=0; i < array1.size(); i++) {
            ProductDto product = array1.get(i);
            ProductDto productDto = new ProductDto();
            if(product.getShopId()  == shopId ){            //si product.getCategoryId() es == al parametro Integer categoryId
                //System.out.println("cumple con la condicion, parametro introducido: " +shopId +", es igual a getShopId del producto: "+ product.getShopId());
                productDto.setProductId(product.getProductId());
                productDto.setName(product.getName());
                productDto.setName2(product.getName2());
                productDto.setDescription(product.getDescription());
                productDto.setDescription2(product.getDescription2());
                productDto.setImg(product.getImg());
                productDto.setPrice(product.getPrice());
                productDto.setStock(product.getStock());
                productDto.setCategoryId(product.getCategoryId());

                //llaves foraneas
                //productDto.setBrandId(product.getBrandId());
                productDto.setShopId(product.getShopId());
                productDto.setShopName(product.getShopName());
                //productDto.setProductTypeId(product.getProductTypeId());

                productAux.add( productDto);
                //System.out.println("productAux: " +i +", "+ productAux);    //muestra los arreglos de los productos que cumplen con la condicion
            }//if
        }//Primer for()
        //System.out.println("primer for: "+ productAux);
/**
        for(int i=0; i < array2.size(); i++) {
            ProductDto product = array2.get(i);
            ProductDto productDto = new ProductDto();
            if(product.getShopId()  == shopId ){            //si product.getCategoryId() es == al parametro Integer categoryId
                //System.out.println("cumple con la condicion, parametro introducido: " +shopId +", es igual a getShopId del producto: "+ product.getShopId());
                productDto.setProductId(product.getProductId());
                productDto.setName(product.getName());
                productDto.setName2(product.getName2());
                productDto.setDescription(product.getDescription());
                productDto.setDescription2(product.getDescription2());
                productDto.setImg(product.getImg());
                productDto.setPrice(product.getPrice());
                productDto.setStock(product.getStock());
                productDto.setCategoryId(product.getCategoryId());

                //llaves foraneas
                //productDto.setBrandId(product.getBrandId());
                productDto.setShopId(product.getShopId());
                productDto.setShopName(product.getShopName());
                //productDto.setProductTypeId(product.getProductTypeId());

                productAux.add( productDto);
                //System.out.println("productAux: " +i +", "+ productAux);    //muestra los arreglos de los productos que cumplen con la condicion
            }//if
        }//Segundo for()
        //System.out.println("Segundo for: "+ productAux);
*/
        for(int i=0; i < array3.size(); i++) {
            ProductDto product = array3.get(i);
            ProductDto productDto = new ProductDto();
            if(product.getShopId()  == shopId ){            //si product.getCategoryId() es == al parametro Integer categoryId
                //System.out.println("cumple con la condicion, parametro introducido: " +shopId +", es igual a getShopId del producto: "+ product.getShopId());
                productDto.setProductId(product.getProductId());
                productDto.setName(product.getName());
                productDto.setName2(product.getName2());
                productDto.setDescription(product.getDescription());
                productDto.setDescription2(product.getDescription2());
                productDto.setImg(product.getImg());
                productDto.setPrice(product.getPrice());
                productDto.setStock(product.getStock());
                productDto.setCategoryId(product.getCategoryId());

                //llaves foraneas
                //productDto.setBrandId(product.getBrandId());
                productDto.setShopId(product.getShopId());
                productDto.setShopName(product.getShopName());
                //productDto.setProductTypeId(product.getProductTypeId());

                productAux.add( productDto);
            }//if
        }//Tercer for()
        //System.out.println("Tercer for: "+ productAux);

        return  productAux;
    }


    //Prueba, guardado de 2 listas en un array en diferentes for()
    /**
    public List<String> Arrays(){
        List<String> array1 = new ArrayList<String>();
        array1.add("1");
        array1.add("2");
        array1.add("3");
        array1.add("4");
        List<String> array2 = new ArrayList<String>();
        array2.add("5");
        array2.add("6");
        array2.add("7");
        array2.add("8");

        List<String> arrayAux = new ArrayList<String>();    //en arrayAux guardar array1 y array2
        for (int i=0; i < array1.size(); i++){
            String Aux = array1.get(i);                     //en Aux guardar todo el recorrido de array1
            arrayAux.add(Aux);
            //System.out.println("i: "+ Aux);
        }
        System.out.println("Guardado de Array1 en ArrayAux : "+ arrayAux);

        for (int i=0; i < array2.size(); i++){
            String Aux = array2.get(i);                     //en Aux guardar todo el recorrido de array2
            arrayAux.add(Aux);
            //System.out.println("i: "+ Aux);
        }
        System.out.println("Guardado de Array2 en ArrayAux : "+ arrayAux);

        return arrayAux;
    }
    */




    //////////////////////////////////////////////////////////////////////
    //ANTIGUO
    //LISTADO DE TODOS LOS PRODUCTOS, ANTIGUO
    public List<ProductDto> productListAll(String url6) throws IOException{
        //Productos Extraidos
        List<ProductDto> productDtos6 = new ArrayList<ProductDto>();
        //llamo al metodo prueba
        productDtos6 = extractProductList6(url6);

        List<ProductDto> productAll = new ArrayList<ProductDto>(); // Lista para guardar todos los productos
        productAll.addAll(productDtos6);
        //System.out.println("product all: " + productAll);

        return  productAll;
    }

    //PRODUCTO POR ID
    //String urlCompuCenter,
    public ProductDto findProductById(Integer productId, String url6)throws IOException {
        List<ProductDto> productDtosFor = productListAll(url6); //se crea para el for y para llamar al metodo productListAll
        ProductDto productAux = new ProductDto();                          // para el return, para guardar el listado final

        for(int i=0; i < productDtosFor.size(); i++) {
            ProductDto product = productDtosFor.get(i);      // product para guardar el recorrido del for
            ProductDto productDto = new ProductDto();

            if(product.getProductId() == productId){                 //listado: product.getProductId() ==  parametro introducido: Integer productId
                System.out.println("product.getProductId() desde el for: "+ product.getProductId() + ", es igual a parametro productId? "+ productId);
                productDto.setProductId(product.getProductId());
                productDto.setName(product.getName());
                productDto.setDescription(product.getDescription());
                productDto.setImg(product.getImg());
                productDto.setPrice(product.getPrice());

                productAux = productDto;
            }//fin IF
        }//fin FOR
        //System.out.println("findShopById: "+ productAux);
        return  productAux;
    }
    //////////////////////////////////////////////////////////////////////













/** Con base de datos
    //Tienda 1: Dismac que contiene productos 1, 2 y 3
    public ShopDto extractShop(String url, ShopDto shopDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo inf. de Tienda 1, página DISMAC " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements descriptionPage = doc.select(" div.first"); // buscando por clase, <div class = first >
        Elements locationPage = doc.select("div.full");
        Elements imgPage = doc.select(" div.category-view");

        String description="";
        String location ="";
        String img="";
        //Description
        for (Element e : descriptionPage.select("div.full"))
        {
            description = e.select("div.span-text" ).text();
            System.out.println("Descripcion: " + description);
        }
        //location
        for (Element e : locationPage.select("div.span-text"))
        {
            location = e.select("span.text" ).text();
        }
            System.out.println("Location: " + location);

        //img
        for (Element e : imgPage.select("div.empresa"))
        {
            img = e.select("div.banner.desktop img").attr("src"); //Obtener src, img del PC
            System.out.println("Logo de la tienda 1: " + img);
        }

        //ShopBl
        Shop shop = new Shop();
        shop.setName("Dismac");
        shop.setDescription(description);
        shop.setLocation(location);
        shop.setImg(img);
        //transaction
        shop.setTxId(transaction.getTxId());
        shop.setTxHost(transaction.getTxHost());
        shop.setTxUserId(transaction.getTxUserId());
        shop.setTxDate(transaction.getTxDate());
        shop.setStatus(1);
        shopDao.create(shop);
        Integer getLastId = transactionDao.getLastInsertId();
        shopDto.setShopId(getLastId);
        return  shopDto;
    }

    //Producto 1
    //MARCA, extraccion de marca y guardado en la BD
    public BrandDto extractBrand(String url, BrandDto brandDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo Marca de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements product = doc.select(" div.data.item.content");

        String marca="";
        for (Element e : product.select("div.product.attribute.description"))
        {
            marca = e.select("div.value  " ).text(); //Obtener marca del PC
            System.out.println("Marca: " + marca);
        }

        //brandBl
        Brand brand = new Brand();
        brand.setName("Dell");
        //transaction
        brand.setTxId(transaction.getTxId());
        brand.setTxHost(transaction.getTxHost());
        brand.setTxUserId(transaction.getTxUserId());
        brand.setTxDate(transaction.getTxDate());
        brand.setStatus(1);
        brandDao.create(brand);
        Integer getLastId = transactionDao.getLastInsertId();
        brandDto.setBrandId(getLastId);
        return  brandDto;
    }

    //Tipo de Producto, extraccion de Tipo de Producto y guardado en la BD
    public ProductTypeDto extractProductType(String url,ProductTypeDto productTypeDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo tipo de producto de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements producto = doc.select(" div.columns");

        String tipoProducto="";
        for (Element e : producto.select("div.page-title-wrapper.product"))
        {
            tipoProducto = e.select("h1 span" ).text(); //Obtener tipo de PC
            System.out.println("Tipo de PC: " + tipoProducto);
        }

        //ProductTypeBl
        ProductType productType = new ProductType();
        productType.setName(tipoProducto);
        //transaction
        productType.setTxId(transaction.getTxId());
        productType.setTxHost(transaction.getTxHost());
        productType.setTxUserId(transaction.getTxUserId());
        productType.setTxDate(transaction.getTxDate());
        productType.setStatus(1);
        productTypeDao.create(productType);
        Integer getLastId = transactionDao.getLastInsertId();
        productTypeDto.setProductTypeId(getLastId);
        return productTypeDto;

    }

    //PRODUCTO, extraccion de Producto y guardado en la BD
    public ProductDto extractProduct(String url, ProductDto productDto, Transaction transaction) throws IOException {
        System.out.println("Computadoras, Página Dismac url1" + url + "...");
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.columns");
        Elements imgProduct = doc1.select("div.gallery-placeholder");  //extraccion de imagen
        Elements productDescription = doc1.select("div.data.item.content"); //extracion de detalle del PC
        Elements price = doc1.select(" div.product-info-price");


        //extracion del PC
        String name="";
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.page-title-wrapper.product"))
        {
            name = e.select("h1 span" ).text(); //Obtener tipo de PC
            System.out.println("Nombre del PC: " + name);
        }
        //imgProduct
        for (Element e : imgProduct.select("div"))
        {
            img = e.select("div img").attr("src"); //Obtener src, img del PC
            System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("div.product.attribute.description"))
        {
            description = e.select("div.value " ).text(); //Obtener marca del PC
            System.out.println("Descripción: \n" + description);
        }

        String precio="";
        for (Element e : price.select("div.price-box.price-final_price"))
        {
            precio = e.select("span.pixel-price " ).text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio + ".00");

        }
        //Convertir de String a Double
        Double precioConvertido = Double.parseDouble(precio.replace("," , "."));
        System.out.println("Precio: " + precioConvertido + ".00");


        //ProductBl, create
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setImg(img);
            product.setPrice(precio);
            //transaction
            product.setTxId(transaction.getTxId());
            product.setTxHost(transaction.getTxHost());
            product.setTxUserId(transaction.getTxUserId());
            product.setTxDate(transaction.getTxDate());
            product.setStatus(1);
            //create
            productDao.create(product);
            Integer getLastId = transactionDao.getLastInsertId();
            productDto.setProductId(getLastId);
            return  productDto;
    }

    //Detalle producto, precio y cantidad
    public ProductDetailDto extractDetail(String url, ProductDetailDto productDetailDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo Precio del producto " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements producto = doc.select(" div.product-info-price");

        String precio="";
        for (Element e : producto.select("div.price-box.price-final_price"))
        {
            precio = e.select("span.pixel-price " ).text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio + ".00");

        }
        //Convertir de String a Double
        Double precioConvertido = Double.parseDouble(precio.replace("," , "."));
        System.out.println("Precio: " + precioConvertido + ".00");

        //brandBl, agregar precio, cantidad
        ProductDetail detail = new ProductDetail();
        detail.setDetail("no especificado");
        detail.setQuantity("no especificado");
        //transaction
        detail.setTxId(transaction.getTxId());
        detail.setTxHost(transaction.getTxHost());
        detail.setTxUserId(transaction.getTxUserId());
        detail.setTxDate(transaction.getTxDate());
        detail.setStatus(1);
        productDetailDao.create(detail);
        Integer getLastId = transactionDao.getLastInsertId();
        productDetailDto.setProductDetailId(getLastId);
        return  productDetailDto;
    }



//Producto 2
    //MARCA, extraccion de marca y guardado en la BD
    public BrandDto extractBrand2(String url, BrandDto brandDto, Transaction transaction) throws IOException {
    System.out.println("Extrayendo Marca de la página " + url + "...");
    Document doc = Jsoup.connect(url).timeout(8000).get();
    Elements product = doc.select(" div.columns");

    String marca="";
    for (Element e : product.select("div.page-title-wrapper.product"))
    {
        marca = e.select("h1 span " ).text(); //Obtener marca del PC
        System.out.println("Marca: " + marca);
    }
     //brandBl
     Brand brand = new Brand();
     brand.setName("Hp");
     //transaction
     brand.setTxId(transaction.getTxId());
     brand.setTxHost(transaction.getTxHost());
     brand.setTxUserId(transaction.getTxUserId());
     brand.setTxDate(transaction.getTxDate());
     brand.setStatus(1);
     brandDao.create(brand);
     Integer getLastId = transactionDao.getLastInsertId();
     brandDto.setBrandId(getLastId);
     return  brandDto;
}

    //Tipo de Producto, extraccion de Tipo de Producto y guardado en la BD
    public ProductTypeDto extractProductType2(String url, ProductTypeDto productTypeDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo tipo de producto de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements producto = doc.select(" div.columns");

        String tipoProducto="";
        for (Element e : producto.select("div.page-title-wrapper.product"))
        {
            tipoProducto = e.select("h1 span" ).text(); //Obtener tipo de PC
            System.out.println("Tipo de PC: " + tipoProducto);
        }
         //ProductTypeBl
         ProductType productType = new ProductType();
         productType.setName(tipoProducto);
         //transaction
         productType.setTxId(transaction.getTxId());
         productType.setTxHost(transaction.getTxHost());
         productType.setTxUserId(transaction.getTxUserId());
         productType.setTxDate(transaction.getTxDate());
         productType.setStatus(1);
         productTypeDao.create(productType);
         Integer getLastId = transactionDao.getLastInsertId();
         productTypeDto.setProductTypeId(getLastId);
         return productTypeDto;
    }

    //PRODUCTO, extraccion de Producto y guardado en la BD
    public ProductDto extractProduct2(String url, ProductDto productDto, Transaction transaction) throws IOException {
        System.out.println("Computadoras, Página Dismac url1" + url + "...");
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.columns");
        Elements imgProduct = doc1.select("div.gallery-placeholder");  //extraccion de imagen
        Elements productDescription = doc1.select("div.data.item.content"); //extracion de detalle del PC
        Elements price = doc1.select(" div.product-info-price");

        //extracion del PC
        String name="";
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.page-title-wrapper.product"))
        {
            name = e.select("h1 span" ).text(); //Obtener tipo de PC
            System.out.println("Nombre del PC: " + name);
        }
        //imgProduct
        for (Element e : imgProduct.select("div"))
        {
            img = e.select("div img").attr("src"); //Obtener src, img del PC
            System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("div.product.attribute.description"))
        {
            description = e.select("div.value " ).text(); //Obtener marca del PC
            System.out.println("Descripción: \n" + description);
        }

        String precio="";
        for (Element e : price.select("span.pixel-price"))
        {
            precio = e.select("span.pixel-price " ).text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio + ".00");

        }
        //Convertir de String a Double
        Double precioConvertido = Double.parseDouble(precio.replace("," , "."));
        System.out.println("Precio: " + precioConvertido + ".00");

            //ProductBl, create
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setImg(img);
            product.setPrice(precio);
            //transaction
            product.setTxId(transaction.getTxId());
            product.setTxHost(transaction.getTxHost());
            product.setTxUserId(transaction.getTxUserId());
            product.setTxDate(transaction.getTxDate());
            product.setStatus(1);
            //create
            productDao.create(product);
            Integer getLastId = transactionDao.getLastInsertId();
            productDto.setProductId(getLastId);
            return  productDto;
    }

    //Detalle producto, precio y cantidad
    public ProductDetailDto extractDetail2(String url, ProductDetailDto productDetailDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo Precio del producto " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements producto = doc.select(" div.product-info-price");

        String precio="";
        for (Element e : producto.select("span.pixel-price"))
        {
            precio = e.select("span.pixel-price " ).text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio + ".00");

        }
        //Convertir de String a Double
        Double precioConvertido = Double.parseDouble(precio.replace("," , "."));
        System.out.println("Precio: " + precioConvertido + ".00");

         //brandBl, agregar precio, cantidad
         ProductDetail detail = new ProductDetail();
         detail.setDetail("no especificado");
         detail.setQuantity("no especificado");
         //transaction
         detail.setTxId(transaction.getTxId());
         detail.setTxHost(transaction.getTxHost());
         detail.setTxUserId(transaction.getTxUserId());
         detail.setTxDate(transaction.getTxDate());
         detail.setStatus(1);
         productDetailDao.create(detail);
         Integer getLastId = transactionDao.getLastInsertId();
         productDetailDto.setProductDetailId(getLastId);
         return  productDetailDto;
    }


//Producto 3
    //MARCA, extraccion de marca y guardado en la BD
    public BrandDto extractBrand3(String url, BrandDto brandDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo Marca de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements product = doc.select(" div.columns");

        String marca="";
        for (Element e : product.select("div.page-title-wrapper.product"))
        {
            marca = e.select("h1 span " ).text(); //Obtener marca del PC
            System.out.println("Marca: " + marca);
        }
        //brandBl
        Brand brand = new Brand();
        brand.setName("Lenovo");
        //transaction
        brand.setTxId(transaction.getTxId());
        brand.setTxHost(transaction.getTxHost());
        brand.setTxUserId(transaction.getTxUserId());
        brand.setTxDate(transaction.getTxDate());
        brand.setStatus(1);
        brandDao.create(brand);
        Integer getLastId = transactionDao.getLastInsertId();
        brandDto.setBrandId(getLastId);
        return  brandDto;
    }

    //Tipo de Producto, extraccion de Tipo de Producto y guardado en la BD
    public ProductTypeDto extractProductType3(String url, ProductTypeDto productTypeDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo tipo de producto de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements producto = doc.select(" div.columns");

        String tipoProducto="";
        for (Element e : producto.select("div.page-title-wrapper.product"))
        {
            tipoProducto = e.select("h1 span" ).text(); //Obtener tipo de PC
            System.out.println("Tipo de PC: " + tipoProducto);
        }
        //ProductTypeBl
        ProductType productType = new ProductType();
        productType.setName(tipoProducto);
        //transaction
        productType.setTxId(transaction.getTxId());
        productType.setTxHost(transaction.getTxHost());
        productType.setTxUserId(transaction.getTxUserId());
        productType.setTxDate(transaction.getTxDate());
        productType.setStatus(1);
        productTypeDao.create(productType);
        Integer getLastId = transactionDao.getLastInsertId();
        productTypeDto.setProductTypeId(getLastId);
        return productTypeDto;
    }

    //PRODUCTO, extraccion de Producto y guardado en la BD
    public ProductDto extractProduct3(String url, ProductDto productDto, Transaction transaction) throws IOException {
        System.out.println("Computadoras, Página Dismac url1" + url + "...");
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.columns");
        Elements imgProduct = doc1.select("div.gallery-placeholder");  //extraccion de imagen
        Elements productDescription = doc1.select("div.data.item.content"); //extracion de detalle del PC
        Elements price = doc1.select(" div.product-info-price");

        //extracion del PC
        String name="";
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.page-title-wrapper.product"))
        {
            name = e.select("h1 span" ).text(); //Obtener tipo de PC
            System.out.println("Nombre del PC: " + name);
        }
        //imgProduct
        for (Element e : imgProduct.select("div"))
        {
            img = e.select("div img").attr("src"); //Obtener src, img del PC
            System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("div.product.attribute.description"))
        {
            description = e.select("div.value " ).text(); //Obtener marca del PC
            System.out.println("Descripción: \n" + description);
        }

        String precio="";
        for (Element e : price.select("span.pixel-price"))
        {
            precio = e.select("span.pixel-price " ).text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio + ".00");

        }
        //Convertir de String a Double
        Double precioConvertido = Double.parseDouble(precio.replace("," , "."));
        System.out.println("Precio: " + precioConvertido + ".00");

        //ProductBl, create
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setImg(img);
        product.setPrice(precio);
        //transaction
        product.setTxId(transaction.getTxId());
        product.setTxHost(transaction.getTxHost());
        product.setTxUserId(transaction.getTxUserId());
        product.setTxDate(transaction.getTxDate());
        product.setStatus(1);
        //create
        productDao.create(product);
        Integer getLastId = transactionDao.getLastInsertId();
        productDto.setProductId(getLastId);
        return  productDto;
    }

    //Detalle producto, precio y cantidad
    public ProductDetailDto extractDetail3(String url, ProductDetailDto productDetailDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo Precio del producto " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements producto = doc.select(" div.product-info-price");

        String precio="";
        for (Element e : producto.select("span.pixel-price"))
        {
            precio = e.select("span.pixel-price " ).text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio + ".00");

        }
        //Convertir de String a Double
        Double precioConvertido = Double.parseDouble(precio.replace("," , "."));
        System.out.println("Precio: " + precioConvertido + ".00");

        //brandBl, agregar precio, cantidad
        ProductDetail detail = new ProductDetail();
        detail.setDetail("no especificado");
        detail.setQuantity("no especificado");
        //transaction
        detail.setTxId(transaction.getTxId());
        detail.setTxHost(transaction.getTxHost());
        detail.setTxUserId(transaction.getTxUserId());
        detail.setTxDate(transaction.getTxDate());
        detail.setStatus(1);
        productDetailDao.create(detail);
        Integer getLastId = transactionDao.getLastInsertId();
        productDetailDto.setProductDetailId(getLastId);
        return  productDetailDto;
    }





//ACTUALIZACIONES
//Tienda 1: Dismac que contiene productos 1, 2 y 3
    public ShopDto updateShop(String url, ShopDto shopDto, Transaction transaction) throws IOException {
        System.out.println("## Actualizando inf. de Tienda 1: ## " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements descriptionPage = doc.select(" div.first"); // buscando por clase, <div class = first >
        Elements locationPage = doc.select("div.full");
        Elements imgPage = doc.select(" div.category-view");

        String description="";
        String location ="";
        String img="";
        //Description
        for (Element e : descriptionPage.select("div.full"))
        {
            description = e.select("div.span-text" ).text();
            System.out.println("Descripcion: " + description);
        }
        //location
        for (Element e : locationPage.select("div.span-text"))
        {
            location = e.select("span.text" ).text();
        }
        System.out.println("Location: " + location);

        //img
        for (Element e : imgPage.select("div.empresa"))
        {
            img = e.select("div.banner.desktop img").attr("src"); //Obtener src, img del PC
            System.out.println("Logo de la tienda 1: " + img);
        }
        //ShopBl Update
        Shop shop= new Shop();
        shop.setShopId(shopDto.getShopId());
        shop.setName(" Dismac "); //"DISMAC UP DATE"
        shop.setDescription(description);
        shop.setLocation(location);
        shop.setImg(img);
        //transaction
        shop.setTxId(transaction.getTxId());
        shop.setTxUserId(transaction.getTxUserId());
        shop.setTxHost(transaction.getTxHost());
        shop.setTxDate(transaction.getTxDate());
        shop.setStatus(1);
        shopDao.update(shop);
        Integer getLastId = transactionDao.getLastInsertId();
        shopDto.setShopId(getLastId);
        return shopDto;
    }

//PRODUCTO 1, update
    public ProductDto updateProduct(String url, ProductDto productDto, Transaction transaction) throws IOException {
        System.out.println("## Actualizando Producto ##: " + url + "...");
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.columns");
        Elements imgProduct = doc1.select("div.gallery-placeholder");  //extraccion de imagen
        Elements productDescription = doc1.select("div.data.item.content"); //extracion de detalle del PC
        Elements price = doc1.select(" div.product-info-price");

        //extracion del PC
        String name="";
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.page-title-wrapper.product"))
        {
            name = e.select("h1 span" ).text(); //Obtener tipo de PC
            System.out.println("Nombre del PC: " + name);
        }
        //imgProduct
        for (Element e : imgProduct.select("div"))
        {
            img = e.select("div img").attr("src"); //Obtener src, img del PC
            System.out.println("imagen : " + img);
        }
        //pruductDescription
        for (Element e : productDescription.select("div.product.attribute.description"))
        {
            description = e.select("div.value " ).text(); //Obtener marca del PC
            System.out.println("Descripción: \n" + description);
        }

        String precio="";
        for (Element e : price.select("div.price-box.price-final_price"))
        {
            precio = e.select("span.pixel-price " ).text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio + ".00");

        }
        //Convertir de String a Double
        Double precioConvertido = Double.parseDouble(precio.replace("," , "."));
        System.out.println("Precio: " + precioConvertido + ".00");

        //ProductBl, update
        Product product= new Product();
        product.setProductId(productDto.getProductId());
        product.setName(name);
        product.setDescription(description);
        product.setImg(img);
        product.setPrice(precio);
        //transaction
        product.setTxId(transaction.getTxId());
        product.setTxUserId(transaction.getTxUserId());
        product.setTxHost(transaction.getTxHost());
        product.setTxDate(transaction.getTxDate());
        product.setStatus(1);
        productDao.update(product);
        return productDto;
    }


//PRODUCTO 2, update
    public ProductDto updateProduct2(String url, ProductDto productDto, Transaction transaction) throws IOException {
        System.out.println("Computadoras, Página Dismac url1" + url + "...");
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.columns");
        Elements imgProduct = doc1.select("div.gallery-placeholder");  //extraccion de imagen
        Elements productDescription = doc1.select("div.data.item.content"); //extracion de detalle del PC
        Elements price = doc1.select(" div.product-info-price");

        //extracion del PC
        String name="";
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.page-title-wrapper.product"))
        {
            name = e.select("h1 span" ).text(); //Obtener tipo de PC
            System.out.println("Nombre del PC: " + name);
        }
        //imgProduct
        for (Element e : imgProduct.select("div"))
        {
            img = e.select("div img").attr("src"); //Obtener src, img del PC
            System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("div.product.attribute.description"))
        {
            description = e.select("div.value " ).text(); //Obtener marca del PC
            System.out.println("Descripción: \n" + description);
        }

        String precio="";
        for (Element e : price.select("span.pixel-price"))
        {
            precio = e.select("span.pixel-price " ).text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio + ".00");

        }
        //Convertir de String a Double
        Double precioConvertido = Double.parseDouble(precio.replace("," , "."));
        System.out.println("Precio: " + precioConvertido + ".00");

        //ProductBl, update
        Product product= new Product();
        product.setProductId(productDto.getProductId());
        product.setName(name);
        product.setDescription(description);
        product.setImg(img);
        product.setPrice(precio);
        //transaction
        product.setTxId(transaction.getTxId());
        product.setTxUserId(transaction.getTxUserId());
        product.setTxHost(transaction.getTxHost());
        product.setTxDate(transaction.getTxDate());
        product.setStatus(1);
        productDao.update(product);
        return productDto;
    }


//PRODUCTO 3, update
    public ProductDto updateProduct3(String url, ProductDto productDto, Transaction transaction) throws IOException {
        System.out.println("Computadoras, Página Dismac url1" + url + "...");
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.columns");
        Elements imgProduct = doc1.select("div.gallery-placeholder");  //extraccion de imagen
        Elements productDescription = doc1.select("div.data.item.content"); //extracion de detalle del PC
        Elements price = doc1.select(" div.product-info-price");

        //extracion del PC
        String name="";
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.page-title-wrapper.product"))
        {
            name = e.select("h1 span" ).text(); //Obtener tipo de PC
            System.out.println("Nombre del PC: " + name);
        }
        //imgProduct
        for (Element e : imgProduct.select("div"))
        {
            img = e.select("div img").attr("src"); //Obtener src, img del PC
            System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("div.product.attribute.description"))
        {
            description = e.select("div.value " ).text(); //Obtener marca del PC
            System.out.println("Descripción: \n" + description);
        }

        String precio="";
        for (Element e : price.select("span.pixel-price"))
        {
            precio = e.select("span.pixel-price " ).text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio + ".00");

        }
        //Convertir de String a Double
        Double precioConvertido = Double.parseDouble(precio.replace("," , "."));
        System.out.println("Precio: " + precioConvertido + ".00");

        //ProductBl, update
        Product product= new Product();
        product.setProductId(productDto.getProductId());
        product.setName(name);
        product.setDescription(description);
        product.setImg(img);
        product.setPrice(precio);
        //transaction
        product.setTxId(transaction.getTxId());
        product.setTxUserId(transaction.getTxUserId());
        product.setTxHost(transaction.getTxHost());
        product.setTxDate(transaction.getTxDate());
        product.setStatus(1);
        productDao.update(product);
        return productDto;
    }

*/





    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }



}
