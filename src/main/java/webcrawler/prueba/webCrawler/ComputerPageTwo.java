package webcrawler.prueba.webCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.*;
import webcrawler.prueba.dto.*;
import webcrawler.prueba.model.*;

import java.io.IOException;

@Service
public class ComputerPageTwo {

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

    @Autowired
    public ComputerPageTwo(BrandDao brandDao, ProductDao productDao, ProductTypeDao productTypeDao, ShopDao shopDao, ProductDetailDao productDetailDao, TransactionDao transactionDao){
        this.brandDao = brandDao;
        this.productTypeDao = productTypeDao;
        this.shopDao = shopDao;
        this.productDao = productDao;
        this.productDetailDao = productDetailDao;
        this.transactionDao = transactionDao;
    }

    public ComputerPageTwo(){

    }

//Tienda 2, que contiene productos 1, 2 y 3
//Tienda DISMAC, de Bolivia
    public ShopDto extractShop(String url, ShopDto shopDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo inf. de Tienda 1, página Intecsa " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements descriptionPagina2 = doc.select(" div.first"); // buscando por clase, <div class = first >
        Elements imgPagina2 = doc.select(" div.category-view");

        String description="";
        String img="";
        //Description
        for (Element e : descriptionPagina2.select("div.full"))
        {
            description = e.select("div.span-text" ).text();
            System.out.println("Descripcion: " + description);
        }

        //img
        for (Element e : imgPagina2.select("div.empresa"))
        {
            img = e.select("div.banner.desktop img").attr("src"); //Obtener src, img del PC
            System.out.println("Logo de la tienda 2: " + img);
        }
        //ShopBl
        Shop shop = new Shop();
        shop.setName("DISMAC");
        shop.setDescription(description);
        shop.setLocation("C./ Eloy Salmon No. 1053, Zona Gran Poder La Paz, La Paz Bolivia\n" +
                         "Horarios: Lunes a Viernes de 09:00 - 19:00. Sábado de 09:00 - 19:00. Domingo Cerrado");
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
    //MARCA DELL, extraccion de marca y guardado en la BD
    public BrandDto extractBrand(String url, BrandDto brandDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo Marca de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements producto1 = doc.select(" div.data.item.content");

        String marca1="";
        for (Element e : producto1.select("div.product.attribute.description"))
        {
            marca1 = e.select("div.value  p:matches(Laptop DELL Inspiron 5502)" ).text(); //Obtener marca del PC
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
    public ProductTypeDto extractProductType(String url, ProductTypeDto productTypeDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo tipo de producto de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements producto = doc.select(" div.columns");

        String tipoProducto="";
        for (Element e : producto.select("div.page-title-wrapper"))
        {
            tipoProducto = e.select("h1 span" ).text(); //Obtener tipo de PC
            System.out.println("Tipo de PC: " + tipoProducto);
        }
        //ProductTypeBl
        ProductType productType = new ProductType();
        productType.setName("Computadora portátil");
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
        System.out.println("Computadoras, Página Dismac: " + url + "...");
        Document doc = Jsoup.connect(url).timeout(10000).get();
        Elements imgProduct = doc.select("div.gallery-placeholder");  //extraccion de imagen
        Elements productoName = doc.select(" div.columns");
        Elements pc1 = doc.select("div.product.attribute.description"); //extracion de detalle del PC

        //Extraccion de la img
        String img="";
        for (Element e : imgProduct.select("div"))
        {
            img = e.select("div img").attr("src"); //Obtener src, img del PC
            //System.out.println("imagen : " + img);
        }

        //extraccion nombre
        String nameProduct="";
        for (Element e : productoName.select("div.page-title-wrapper"))
        {
            nameProduct = e.select("h1 span" ).text(); //Obtener tipo de PC
            //System.out.println("Nombre de PC: " + nameProduct);
        }

        //Extracion  de PC
        String processor="";
        String ram="";
        String discoAlmacenamiento="";
        String video="";
        String pantalla="";
        String description="";
        for (Element e : pc1.select("ul")) {
            //Datos del producto
            processor = e.select("li:matches(Procesador Intel Core i5 1135G7)").text(); //Obtener procesador de PC
            ram = e.select("li:matches(Memoria)").text(); //Obtener procesador de PC
            discoAlmacenamiento = e.select("li:matches(SSD de 256GB, Pantalla de 15.6\"\" LED)").text(); //Obtener procesador de PC
            video = e.select("li:matches(Video)").text(); //Obtener procesador de PC
            pantalla = e.select("li:matches(Pantalla)").text(); //Obtener procesador de PC
            description = e.select("li:matches(S.O)").text(); //Obtener procesador de PC

            System.out.println("imagen: " + img); //llamando Img del primer For
            System.out.println("Nombre: " + nameProduct);
            System.out.println("Procesador: " + processor); // Procesador
            System.out.println("Memoria Ram: " + ram); // Memoria RAM
            System.out.println("Almacenamiento: " + discoAlmacenamiento); // Disco de Almacenamiento
            System.out.println("Tarjeta de video: " + video); // tarjeta de video
            System.out.println("Pantalla: " + pantalla); // medidas de la pantalla
            System.out.println("Sistema operativo: " + description); // descripcion de la garantía
        }

        //ProductBl, create
        Product product = new Product();
        product.setName(nameProduct);
        product.setDescription(description);
        product.setImg(img);
        product.setProcesador(processor);
        product.setMemoriaRam(ram);
        product.setDiscoAlmacenamiento("SSD de 256GB");
        product.setTarjetaVideo(video);
        product.setPantalla("Pantalla de 15.6 LED");
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
        Elements producto1 = doc.select(" div.product-info-price");

        String precio="";
        for (Element e : producto1.select("div.price-box.price-final_price"))
        {
            precio = e.select("span.pixel-price " ).text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio + ".00");

        }
        //Convertir de String a Double
        Double precioConvertido = Double.parseDouble(precio.replace("," , "."));
        System.out.println("Precio: " + precioConvertido + ".00");

        //brandBl, agregar precio, cantidad
        ProductDetail detail = new ProductDetail();
        detail.setPrice(precioConvertido);
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

}