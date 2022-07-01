package webcrawler.prueba.webCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.*;
import webcrawler.prueba.dto.*;
import webcrawler.prueba.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ComputerPageTwo extends Thread {

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
    //CATEGORIAS
    //STUDY/HOME,  PC.COM y tienda oficial Hp
    private String urlTiendaHpHogar;
    //GAMER, CompuCenter
    private String urlCompuCenterGamer;
    //WORK,  tienda oficial Hp
    private String urlTiendaHpEmpresas;
    //ProductsByCategory
    private Integer categoryId;
    private SimpMessagingTemplate template;


    @Autowired
    public ComputerPageTwo(BrandDao brandDao, ProductDao productDao, ProductTypeDao productTypeDao, ShopDao shopDao, ProductDetailDao productDetailDao, TransactionDao transactionDao){
        this.brandDao = brandDao;
        this.productTypeDao = productTypeDao;
        this.shopDao = shopDao;
        this.productDao = productDao;
        this.productDetailDao = productDetailDao;
        this.transactionDao = transactionDao;
    }

    public ComputerPageTwo(
            SimpMessagingTemplate template,
            String urlTiendaHpHogar,
            String urlCompuCenterGamer,
            String urlTiendaHpEmpresas
            )
    {
        this.template = template;
        this.urlTiendaHpHogar = urlTiendaHpHogar;
        this.urlCompuCenterGamer = urlCompuCenterGamer;
        this.urlTiendaHpEmpresas = urlTiendaHpEmpresas;
    }

    public ComputerPageTwo(){}

    //CATEGORIAS

    //Categoria Hogar, Tienda Hp
    //PRODUCTOS:12
    //https://www.hp.com/cl-es/shop/notebooks.html?hp_facet_segment=Hogar&p=1
    public List<ProductDto> tiendaHpHogar(String url) throws IOException {
        System.out.println("Tienda Hp Laptops para el hogar, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(15000).get();
        Elements body = doc.select("div ol.products.list.items.product-items");
        //Salto de linea de descripcion
        //body.select("li").append("\\nl"); //append salto de linea despues de un elemento
        body.select("li").prepend("\\nl"); //append salto de linea Antes de un elemento

        int contadorId = 122;    //Para Ids
        //Lista para guardar los productos que recorrera el for
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        System.out.println("-------Inicio------------");
        for (Element e : body.select("li.item.product.product-item "))
        {
            String nombre1 = e.select(" strong a ").text();
            String nombre2 = e.select(" div.grid-product__sku-inner ").text();  //no tiene
            String descripcion = e.select(" div.product-desc-features ul li  ").text().replaceAll("\\\\nl", "\n");
            String descripcion2 = e.select(" strong a  ").attr("href");
            String precio = e.select(" div.price-box.price-final_price span.price ").text();
            String imagen = e.select(" div.product-item-photo-box img   ").attr("src");
            String stock = e.select("div.stock-messaging span").text();

            if( imagen.contains("https://d1pc5hp1w29h96.cloudfront.net/catalog/product/")) {
                imagen = e.select(" div.product-item-photo-box img   ").attr("src");
                System.out.println("imagen contiene src: "+ imagen);
            }else{
                imagen = e.select(" div.product-item-photo-box img   ").attr("data-src");
                System.out.println("imagen contiene data-src: "+ imagen);
            }

            /*
            System.out.println("- nombre1(Marca): "+ nombre1);
            System.out.println("  nombre2():  "+ nombre2 );
            System.out.println("  descripcion:  "+ descripcion);
            System.out.println("Tienda Hp Laptops para el hogar  descripcion2:  "+ descripcion2);
            System.out.println("  precio actual:  "+ precio);
            System.out.println("  Imagen:   "+ imagen);
             System.out.println(" Stock:   "+ stock);
            */

            /////////////////////////////////////////////////////////////////////////
            contadorId = contadorId + 1;
            //PRUEBA de ARRAY SIN BASE DE DATOS
            //List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
            ProductDto productDto = new ProductDto();
            productDto.setProductId(contadorId);//nombre1 + ""+ shop
            productDto.setName(nombre1);
            productDto.setName2(nombre2);
            productDto.setShopName("Tienda oficial HP");
            productDto.setDescription(descripcion);
            productDto.setDescription2(descripcion2);
            productDto.setImg(imagen);
            productDto.setStock(stock);
            productDto.setPrice(precio);
            //Usando expresiones regulares para buscar coincidencias
            /**
            productDto.setBrand(matcherMarca.group());  //matcherMarca.group()
            productDto.setRam(matcherRam.group().replaceAll("[\\ \\\\]", ""));
            productDto.setProcessor(matcherProcesador.group());
            productDto.setStorage(matcherAlmacenamiento.group().replaceAll("[\\ \\\\]", ""));
            */
            //productDto.setTarjetaGrafica(tarjetaGrafica);
            //llaves foraneas
            productDto.setShopId(6);
            productDto.setCategoryId(1);

            productDtos.add( productDto);
            System.out.println("for() productDto Tienda oficial HP: " + productDto);   //muestra las listas de los productos por separado

        }
        System.out.println("-------Fin------------");
        System.out.println("Tienda oficial HP productDtos: " + productDtos);  //muestra  el conjunto de listas de productos en una sola lista
        return  productDtos;
    }


    //Categoria Gamer, CompuCenter
    //Productos:10,
    //https://compucenter.store/category/23-equipo/238-gaming
    public List<ProductDto> compuCenterGaming(String url) throws IOException {
        System.out.println("CompuCenter Equipos Gaming, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(11000).get();
        Elements body = doc.select("section.flex.flex-wrap.justify-center.items-center");


        int contadorId = 135;    //Para Ids
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        System.out.println("-------Inicio Categoria gamer------------");
        for (Element e : body.select("div.flex.items-center.p-5.relative.w-full"))
        {
            String nombre1 = e.select(" div.flex.flex-col.mb-2 strong  ").text();
            String nombre2 = e.select(" h1.font-semibold.text-lg ").text();
            String descripcion = e.select(" div.text-base.text-justify ").text();
            String descripcion2 = e.select(" a ").attr("abs:href");
            String precio = e.select(" div.inline-block.align-bottom.mr-5 span ").text();
            String imagen = e.select(" div.relative.border-4.border-red-100 img  ").attr("src");

            /**
            System.out.println("- nombre1(Marca): "+ nombre1);
            System.out.println("  nombre2:  "+ nombre2);
            System.out.println("  descripcion:  "+ descripcion);
            System.out.println(" CompuCenter Equipos Gaming, descripcion2:  "+ descripcion2);
            System.out.println("  precio:  "+ precio);
            System.out.println("  Imagen:   "+ imagen);
            System.out.println("");
            */
            /////////////////////////////////////////////////////////////////////////
            contadorId = contadorId + 1;
            //PRUEBA de ARRAY SIN BASE DE DATOS
            ProductDto productDto = new ProductDto();
            productDto.setProductId(contadorId);
            productDto.setName(nombre1);
            productDto.setName2(nombre2);
            productDto.setShopName("CompuCenter");
            productDto.setDescription(descripcion);
            productDto.setDescription2(descripcion2);
            productDto.setImg(imagen);
            productDto.setPrice(precio);
            //llaves foraneas
            productDto.setShopId(2);
            productDto.setCategoryId(2);  //problema,

            productDtos.add( productDto);
            System.out.println("for() productDto categoria Gamer: " + productDto);
        }
        System.out.println("-------Fin------------");
        System.out.println("CompuCenter productDtos, categoria Gamer: " + productDtos);  //muestra  el conjunto de listas de productos en una sola lista
        return  productDtos;
    }


    ///////////////////////////////////////////////////////////////////////////////
    //Categoria Work, Tienda Hp
    //PRODUCTOS:12
    //https://www.hp.com/cl-es/shop/notebooks/notebooks-empresariales.html
    public List<ProductDto> tiendaHpEmpresas(String url) throws IOException {
        System.out.println("Tienda Hp Laptops para Empresas, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(20000).get();
        Elements body = doc.select("div ol.products.list.items.product-items");
        //Salto de linea de descripcion
        //body.select("li").append("\\nl"); //append salto de linea despues de un elemento
        body.select("li").prepend("\\nl"); //append salto de linea Antes de un elemento


        int contadorId = 148;    //Para Ids
        //Lista para guardar los productos que recorrera el for
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        System.out.println("-------Inicio------------");

        for (Element e : body.select("li.item.product.product-item "))
        {
            String nombre1 = e.select(" strong a ").text();
            String nombre2 = e.select(" div.grid-product__sku-inner ").text();
            String descripcion = e.select(" div.product-desc-features ul li  ").text().replaceAll("\\\\nl", "\n");
            String descripcion2 = e.select(" strong a  ").attr("href");
            String precio = e.select(" div.price-box.price-final_price span.price ").text();
            String imagen = e.select(" div.product-item-photo-box img   ").attr("src");
            String stock = e.select("div.stock-messaging span").text();
            //extraccion de imagen src y data-src
            if( imagen.contains("https://d1pc5hp1w29h96.cloudfront.net/catalog/product/")) {
                imagen = e.select(" div.product-item-photo-box img   ").attr("src");
                System.out.println("imagen contiene src: "+ imagen);
            }else{
                imagen = e.select(" div.product-item-photo-box img   ").attr("data-src");
                System.out.println("imagen contiene data-src: "+ imagen);
            }

            /*
            System.out.println("- nombre1(Marca): "+ nombre1);
            System.out.println("  nombre2():  "+ nombre2 );
            System.out.println("  descripcion:  "+ descripcion);
            System.out.println("Tienda Hp Laptops para el Empresas  descripcion2:  "+ descripcion2);
            System.out.println("  precio actual:  "+ precio);
            System.out.println("  Imagen:   "+ imagen);
            System.out.println("stock: "+ stock);
            */

            /////////////////////////////////////////////////////////////////////////
            contadorId = contadorId + 1;
            //PRUEBA de ARRAY SIN BASE DE DATOS
            //List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
            ProductDto productDto = new ProductDto();
            productDto.setProductId(contadorId);//nombre1 + ""+ shop
            productDto.setName(nombre1);
            productDto.setName2(nombre2);
            productDto.setShopName("Tienda oficial HP");
            productDto.setDescription(descripcion);
            productDto.setDescription2(descripcion2);
            productDto.setImg(imagen);
            productDto.setPrice(precio);
            productDto.setStock(stock);
            //Usando expresiones regulares para buscar coincidencias
            /**
             productDto.setBrand(matcherMarca.group());  //matcherMarca.group()
             productDto.setRam(matcherRam.group().replaceAll("[\\ \\\\]", ""));
             productDto.setProcessor(matcherProcesador.group());
             productDto.setStorage(matcherAlmacenamiento.group().replaceAll("[\\ \\\\]", ""));
             */
            //productDto.setTarjetaGrafica(tarjetaGrafica);
            //llaves foraneas
            productDto.setShopId(6);
            productDto.setCategoryId(3);

            productDtos.add( productDto);
            System.out.println("for() productDto Tienda oficial HP: " + productDto);   //muestra las listas de los productos por separado

        }
        System.out.println("-------Fin------------");
        System.out.println("Tienda oficial HP productDtos: " + productDtos);  //muestra  el conjunto de listas de productos en una sola lista
        return  productDtos;
    }




    //LISTADO DE TODOS LOS PRODUCTOS
    // para el Api, productos category
    //String urlS1,String urlS2,String urlS3,String urlS4,
    // String urlW4,String urlW5,
    public List<ProductDto> productListAll( String urlTiendaHpHogar,   String urlCompuCenterGamer,  String urlTiendaHpEmpresas) throws IOException{
        //categoria Home
        List<ProductDto> productTiendaHpHogar = new ArrayList<ProductDto>();
        //categoria Gamer
        List<ProductDto> productGamerCompuCenter = new ArrayList<ProductDto>();
        //categoria Work
        List<ProductDto> productTiendaHpEmpresas = new ArrayList<ProductDto>();


        //categoria Home
        productTiendaHpHogar = tiendaHpHogar(urlTiendaHpHogar);
        //categoria Gamer
        productGamerCompuCenter = compuCenterGaming(urlCompuCenterGamer);
        //categoria Work
        productTiendaHpEmpresas = tiendaHpEmpresas(urlTiendaHpEmpresas);


        //System.out.println("Tamaño: " + productDtos.size());
        List<ProductDto> productAll = new ArrayList<ProductDto>(); // Lista para guardar todos los productos

        productAll.addAll(productTiendaHpHogar);
        productAll.addAll(productGamerCompuCenter);
        productAll.addAll(productTiendaHpEmpresas);

        //System.out.println("product all: " + productAll);
        return  productAll;
    }


    //LISTADO ProductCategory  por categoriaId
    //llamando al metodo productListAll
    public List<ProductDto> selectProductsByCategory(Integer categoryId,  String urlTiendaHpHogar,  String urlCompuCenterGamer,   String urlTiendaHpEmpresas)throws IOException{
        //List<Product> products = productDao.getProductListByCategory(categoryId);      //productos, se crea un for para recorrer products
        List<ProductDto> productDtosFor = productListAll( urlTiendaHpHogar, urlCompuCenterGamer,  urlTiendaHpEmpresas); //se crea para el for y para llamar al metodo productListAll y obtener sus datos extraidos
        List<ProductDto> productAux = new ArrayList<ProductDto>();      //return aux

        for(int i=0; i < productDtosFor.size(); i++) {
            ProductDto product = productDtosFor.get(i);
            ProductDto productDto = new ProductDto();
            //System.out.println("tamaño de productListAll: " +i +", "+ product);   //recorre todo el for
            if(product.getCategoryId()  == categoryId ){            //si product.getCategoryId() es == al parametro Integer categoryId
                /**
                System.out.println("cumple con la condicion, parametro introducido: " +categoryId +", CategoryId del producto: "+ product.getCategoryId());   //
                System.out.println("productoId: " + product.getProductId() );   //
                System.out.println("name: " + product.getName() );   //
                System.out.println("description: " + product.getDescription() );   //
                System.out.println("img: " + product.getImg() );   //
                System.out.println("precio: " + product.getPrice() );   //
                System.out.println("categoria a la que pertenece: " + product.getCategoryId() );   //
                */
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
                System.out.println("productAux: " +i +", "+ productAux);    //muestra los arreglos de los productos que cumplen con la condicion
            }//if
        }//for
        return  productAux;
    }
//FIN




    //////////////////////////////////////////////////////////////////////
    // LISTADO DE TODOS LOS PRODUCTOS PARA EL HILO
    public List<ProductDto> productListAllHilo() {
        //Creacion de arrayList
        List<ProductDto> productsByCategory = new ArrayList<ProductDto>(); //se crea productDtos para tener el listado de products

        //Llamamos a los metodos
        try {
            //selectProductsByCategory
            productsByCategory = selectProductsByCategory(categoryId,  urlTiendaHpHogar,  urlCompuCenterGamer,  urlTiendaHpEmpresas);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //agregamos en una sola lista
        List<ProductDto> productAll = new ArrayList<ProductDto>(); // Lista para guardar todos los productos
        productAll.addAll(productsByCategory);
        //System.out.println("product all: " + productAll);

        return  productAll;
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
            System.out.println("  Thread category is running...");
            List<ProductDto> objCategory = new ArrayList<ProductDto>();
            objCategory = productListAllHilo();                                     //Se llama al metodo productListAllHilo() que contiene los productos
            this.template.convertAndSend("/topic/products",  objCategory);  //envio de productos extraidos a la consola en frontend
            System.out.println(" Fin Hilo objCategory");


            try {
                //Thread, Wait for one sec so it doesn't print too fast
                Thread.sleep(80000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //return objCategory;
        }//fin while
    }







}