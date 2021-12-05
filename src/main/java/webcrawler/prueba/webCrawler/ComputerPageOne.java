package webcrawler.prueba.webCrawler;

import org.apache.ibatis.annotations.Mapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.*;
import webcrawler.prueba.dto.BrandDto;
import webcrawler.prueba.dto.ProductDto;
import webcrawler.prueba.dto.ProductTypeDto;
import webcrawler.prueba.dto.ShopDto;
import webcrawler.prueba.model.*;

import java.io.IOException;

@Service
public class ComputerPageOne {

    //BrandDao
    private BrandDao brandDao;
    private TransactionDao transactionDao;
    //ProductType
    private ProductTypeDao productTypeDao;
    //ShopDao
    private ShopDao shopDao;
    //ProductDao
    private ProductDao productDao;

    @Autowired
    public ComputerPageOne(BrandDao brandDao, ProductDao productDao, ProductTypeDao productTypeDao, ShopDao shopDao, TransactionDao transactionDao){
        this.brandDao = brandDao;
        this.productTypeDao = productTypeDao;
        this.shopDao = shopDao;
        this.productDao = productDao;
        this.transactionDao = transactionDao;
        //this.brandDto = brandDto;
        //this.transaction = transaction;
    }

    public ComputerPageOne() {

    }

//Tienda 1, que contiene productos 1, 2 y 3
    //Tienda Intecsa, de Bolivia
    //ShopDto shopDto, Transaction transaction
    public ShopDto extractShop(String url, ShopDto shopDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo inf. de Tienda 1, página Intecsa " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements descriptionPagina1 = doc.select(" div.elementor-element.elementor-element-73b3094b"); // buscando por clase, <div class = elementor-element>
        Elements locationPagina1 = doc.select(" aside#custom_html-3.widget_text.widget.widget_custom_html");//buscando por ID, <aside id=custom_html-3>
        Elements imgPagina1 = doc.select(" div.headerwrap");

        String description="";
        String location="";
        String img="";
        //Description
        for (Element e : descriptionPagina1.select("div.elementor-text-editor.elementor-clearfix"))
        {
            description = e.select("p span strong:matches(La empresa INTECSA SRL)" ).text(); //Obtener nombre del PC
            System.out.println("Descripcion: " + description);
        }

        //Ubicacion
        for (Element e : locationPagina1.select("div.textwidget.custom-html-widget"))
        {
            location = e.select("center " ).text(); //Obtener nombre del PC
            System.out.println("Ubicación: " + location );
        }

        //img
        for (Element e : imgPagina1.select("div.headerinnerwrap"))
        {
            img = e.select("a span img").attr("src"); //Obtener src, img del PC
            System.out.println("Logo de la tienda 1: " + img);
        }

        //ShopBl
        Shop shop = new Shop();
        shop.setName("INTECSA SRL");
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
    //Productos DELL
    // MARCA, extraccion de marca y guardado en la BD
    public BrandDto extractBrand(String url, BrandDto brandDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo Marca de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements producto1 = doc.select(" div.woocommerce-tabs.wc-tabs-wrapper");

        String marca1="";
        for (Element e : producto1.select("div.woocommerce-Tabs-panel.woocommerce-Tabs-panel--description.panel.entry-content.wc-tab"))
        {
            marca1 = e.select("p  a:matches(Dell|dell|DELL)" ).text(); //Obtener nombre del PC
            System.out.println("Marca: " + marca1);
        }

        //brandBl
        Brand brand = new Brand();
        brand.setName(marca1);
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
        Elements producto = doc.select(" div.woocommerce-tabs.wc-tabs-wrapper");

        String tipoProducto="";
        for (Element e : producto.select("div.woocommerce-Tabs-panel.woocommerce-Tabs-panel--description.panel.entry-content.wc-tab"))
        {
            tipoProducto = e.select("p  a:matches(Notebook|notebook|NOTEBOOK)" ).text(); //Obtener tipo de PC
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
    public ProductDto extractProduct(String url1, ProductDto productDto, Transaction transaction) throws IOException {
        System.out.println("Computadoras, Página Intecsa url1" + url1 + "...");
        Document doc1 = Jsoup.connect(url1).timeout(10000).get();
        Elements imgProduct = doc1.select("div.site-content");  //extraccion de imagen
        Elements pc1 = doc1.select("div.woocommerce-tabs.wc-tabs-wrapper"); //extracion de detalle del PC

        //Extraccion de la img
        String img="";
        for (Element e : imgProduct.select("div.product.type-product.post-6932"))
        {
            img = e.select("div.woocommerce-product-gallery__image img").attr("src"); //Obtener src, img del PC
            System.out.println("imagen : " + img);
        }

        //extracion de detalle del PC
        String nameProduct="";
        String processor="";
        String ram="";
        String discoAlmacenamiento="";
        String video="";
        String pantalla="";
        String description="";

        print("\nBody: (%d)", pc1.size());
        for (Element e : pc1.select("div.woocommerce-Tabs-panel.woocommerce-Tabs-panel--description.panel.entry-content.wc-tab")) {
            //Datos del producto
            nameProduct = e.select("p strong span ").text(); //Obtener nombre del PC
            processor = e.select("p:matches(Intel core i7 1135G7|intel core i7 1135G7|INTEL core i7 1135G7)").text(); //Obtener procesador de PC
            ram = e.select("p:matches(Memoria RAM)").text(); //Obtener procesador de PC
            discoAlmacenamiento = e.select("p:matches(Disco)").text(); //Obtener procesador de PC
            video = e.select("p:matches(Video)").text(); //Obtener procesador de PC
            pantalla = e.select("p:matches(Pantalla)").text(); //Obtener procesador de PC
            description = e.select("p:matches(Garantía)").text(); //Obtener procesador de PC

            System.out.println("imagen: " + img); //llamando Img del primer For
            System.out.println("Nombre: " + nameProduct);
            System.out.println(processor); // Procesador
            System.out.println(ram); // Memoria RAM
            System.out.println(discoAlmacenamiento); // Disco de Almacenamiento
            System.out.println(video); // tarjeta de video
            System.out.println(pantalla); // medidas de la pantalla
            System.out.println(description); // descripcion de la garantía
        }
            //ProductBl, create
            Product product = new Product();
            product.setName(nameProduct);
            product.setDescription(description);
            product.setImg(img);
            product.setProcesador(processor);
            product.setMemoriaRam(ram);
            product.setDiscoAlmacenamiento(discoAlmacenamiento);
            product.setTarjetaVideo(video);
            product.setPantalla(pantalla);
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


    //Detalle del producto, precio y cantidad
//  , BrandDto brandDto, Transaction transaction
    public void extractDetail(String url) throws IOException {
        System.out.println("Extrayendo precio y cantidad de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements producto1 = doc.select(" div.woocommerce-tabs.wc-tabs-wrapper");

        String detalle1="";
        for (Element e : producto1.select("div.woocommerce-Tabs-panel.woocommerce-Tabs-panel--description.panel.entry-content.wc-tab"))
        {
            detalle1 = e.select("p  a:matches(Dell|dell|DELL)" ).text(); //Obtener nombre del PC
            System.out.println("Marca: " + detalle1);
        }
/**
        //ProductDetailBl
        Brand brand = new Brand();
        brand.setName(marca1);
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
 */
    }

    //Extraccion de prueba IMG
    public void extractImg(String url) throws IOException {
        System.out.println("IMG, Página Intecsa url2" + url + "...");
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements pc1 = doc1.select("div.site-content");

        String img="";
        for (Element e : pc1.select("div.product.type-product.post-6932"))
        {
            //Encabezado, img, nombre y link del producto
            img = e.select("div.woocommerce-product-gallery__image img").attr("src"); //Obtener src, img del PC
            System.out.println("imagen : " + img);
            //"\n"

        }
    }


//Producto 2
    // MARCA, extraccion de marca y guardado en la BD
    public BrandDto extractBrand2(String url, BrandDto brandDto, Transaction transaction) throws IOException {
    System.out.println("Extrayendo Marca de la página " + url + "...");
    Document doc = Jsoup.connect(url).timeout(8000).get();
    Elements producto = doc.select(" div.woocommerce-tabs.wc-tabs-wrapper");

    String marca="";
    for (Element e : producto.select("div.woocommerce-Tabs-panel.woocommerce-Tabs-panel--description.panel.entry-content.wc-tab"))
    {
        marca = e.select("p  a:matches(Dell|dell|DELL)" ).text(); //Obtener nombre del PC
        System.out.println("Marca: " + marca);
    }
    //brandBl
    Brand brand = new Brand();
    brand.setName(marca);
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
        Elements producto = doc.select(" div.woocommerce-tabs.wc-tabs-wrapper");

        String tipoProducto="";
        for (Element e : producto.select("div.woocommerce-Tabs-panel.woocommerce-Tabs-panel--description.panel.entry-content.wc-tab"))
        {
            tipoProducto = e.select("p  a:matches(Notebook|notebook|NOTEBOOK)" ).text(); //Obtener tipo de PC
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

    //PRODUCTO DETALLE, extraccion de Producto y guardado en la BD
    public ProductDto extractProduct2(String url1, ProductDto productDto, Transaction transaction) throws IOException {
        System.out.println("Computadoras, Página Intecsa url1" + url1 + "...");
        Document doc1 = Jsoup.connect(url1).timeout(10000).get();
        Elements imgProduct = doc1.select("div.site-content");  //extraccion de imagen
        Elements pc = doc1.select("div.woocommerce-tabs.wc-tabs-wrapper"); //extracion de detalle del PC

        //Extraccion de la img
        String img="";
        for (Element e : imgProduct.select("div.product.type-product.post-6928"))
        {
            img = e.select("div.woocommerce-product-gallery img").attr("src"); //Obtener src, img del PC
            System.out.println("imagen : " + img);
        }

        //extracion de detalle del PC
        String nameProduct="";
        String processor="";
        String ram="";
        String discoAlmacenamiento="";
        String video="";
        String pantalla="";
        String description="";

        print("\nBody: (%d)", pc.size());
        for (Element e : pc.select("div.woocommerce-Tabs-panel.woocommerce-Tabs-panel--description.panel.entry-content.wc-tab")) {
            //Datos del producto
            nameProduct = e.select("p strong span ").text(); //Obtener nombre del PC
            processor = e.select("p:matches(Intel core i7 1165G7|intel core i7 1165G7|INTEL core i7 1165G7)").text(); //Obtener procesador de PC
            ram = e.select("p:matches(Memoria RAM)").text(); //Obtener procesador de PC
            discoAlmacenamiento = e.select("p:matches(Disco)").text(); //Obtener procesador de PC
            video = e.select("p:matches(Nvidia MX 330 2GB)").text(); //Obtener procesador de PC
            pantalla = e.select("p:matches(Pantalla)").text(); //Obtener procesador de PC
            description = e.select("p:matches(Garantía)").text(); //Obtener procesador de PC

            System.out.println("imagen: " + img); //llamando Img del primer For
            System.out.println("Nombre: " + nameProduct);
            System.out.println(processor); // Procesador
            System.out.println(ram); // Memoria RAM
            System.out.println(discoAlmacenamiento); // Disco de Almacenamiento
            System.out.println(video); // tarjeta de video
            System.out.println(pantalla); // medidas de la pantalla
            System.out.println(description); // descripcion de la garantía
        }
            //ProductBl, create
            Product product = new Product();
            product.setName(nameProduct);
            product.setDescription(description);
            product.setImg(img);
            product.setProcesador(processor);
            product.setMemoriaRam(ram);
            product.setDiscoAlmacenamiento(discoAlmacenamiento);
            product.setTarjetaVideo(video);
            product.setPantalla(pantalla);
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

//Producto 3
    //Productos HP
    // MARCA, extraccion de marca y guardado en la BD
    public BrandDto extractBrand3(String url, BrandDto brandDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo Marca de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements producto = doc.select(" div.woocommerce-tabs.wc-tabs-wrapper");

        String marca="";
        for (Element e : producto.select("div.woocommerce-Tabs-panel.woocommerce-Tabs-panel--description.panel.entry-content.wc-tab"))
        {
            marca = e.select("p  a:matches(Hp|hp|HP)" ).text(); //Obtener nombre del PC
            System.out.println("Marca: " + marca);
        }

        //brandBl
        Brand brand = new Brand();
        brand.setName(marca);
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
        Elements producto = doc.select(" div.woocommerce-tabs.wc-tabs-wrapper");

        String tipoProducto="";
        for (Element e : producto.select("div.woocommerce-Tabs-panel.woocommerce-Tabs-panel--description.panel.entry-content.wc-tab"))
        {
            tipoProducto = e.select("p  a:matches(Pc Portátil)" ).text(); //Obtener tipo de PC
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

    //PRODUCTO DETALLE, extraccion de Producto y guardado en la BD
    public ProductDto extractProduct3(String url1, ProductDto productDto, Transaction transaction) throws IOException {
        System.out.println("Computadoras, Página Intecsa url" + url1 + "...");
        Document doc = Jsoup.connect(url1).timeout(10000).get();
        Elements imgProduct = doc.select("div.site-content");  //extraccion de imagen
        Elements pc = doc.select("div.woocommerce-tabs.wc-tabs-wrapper"); //extracion de detalle del PC

        //Extraccion de la img
        String img="";
        for (Element e : imgProduct.select("div.product.type-product.post-6733"))
        {
            img = e.select("div.woocommerce-product-gallery img").attr("src"); //Obtener src, img del PC
            System.out.println("imagen : " + img);
        }

        //extracion de detalle del PC
        String nameProduct="";
        String processor="";
        String ram="";
        String discoAlmacenamiento="";
        String video="";
        String pantalla="";
        String description="";

        print("\nBody: (%d)", pc.size());
        for (Element e : pc.select("div.woocommerce-Tabs-panel.woocommerce-Tabs-panel--description.panel.entry-content.wc-tab")) {
            //Datos del producto
            nameProduct = e.select("p span strong ").text(); //Obtener nombre del PC
            processor = e.select("p:matches(Intel core i7 de décima generación)").text(); //Obtener procesador de PC
            ram = e.select("p:matches(Memoria RAM)").text(); //Obtener procesador de PC
            discoAlmacenamiento = e.select("p:matches(Almacenamiento)").text(); //Obtener procesador de PC
            video = e.select("p:matches(Video:)").text(); //Obtener procesador de PC
            pantalla = e.select("p:matches(15,6 pulgadas.)").text(); //Obtener procesador de PC
            description = e.select("p:matches(Garantía)").text(); //Obtener procesador de PC

            System.out.println("imagen: " + img); //llamando Img del primer For
            System.out.println("Nombre: " + nameProduct);
            System.out.println(processor); // Procesador
            System.out.println(ram); // Memoria RAM
            System.out.println(discoAlmacenamiento); // Disco de Almacenamiento
            System.out.println(video); // tarjeta de video
            System.out.println(pantalla); // medidas de la pantalla
            System.out.println(description); // descripcion de la garantía
        }
            //ProductBl, create
            Product product = new Product();
            product.setName(nameProduct);
            product.setDescription(description);
            product.setImg(img);
            product.setProcesador(processor);
            product.setMemoriaRam(ram);
            product.setDiscoAlmacenamiento(discoAlmacenamiento);
            product.setTarjetaVideo(video);
            product.setPantalla(pantalla);
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
//









    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }



}
