package webcrawler.prueba.webCrawler;

import org.apache.ibatis.annotations.Mapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.*;
import webcrawler.prueba.dto.*;
import webcrawler.prueba.model.*;

import java.io.IOException;

@Service
public class ComputerPageOne {

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
    public ComputerPageOne(BrandDao brandDao, ProductDao productDao, ProductTypeDao productTypeDao, ShopDao shopDao, ProductDetailDao productDetailDao, TransactionDao transactionDao){
        this.brandDao = brandDao;
        this.productTypeDao = productTypeDao;
        this.shopDao = shopDao;
        this.productDao = productDao;
        this.productDetailDao = productDetailDao;
        this.transactionDao = transactionDao;
    }

    public ComputerPageOne() {

    }

//Tienda 1, que contiene productos 1, 2 y 3
    //Tienda Dismac, de Bolivia
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
        shop.setName("DISMAC");
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

            //ProductBl, create
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setImg(img);
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

            //ProductBl, create
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setImg(img);
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
        //ProductBl, create
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setImg(img);
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











    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }



}