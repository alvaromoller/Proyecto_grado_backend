package webcrawler.prueba.webCrawler;

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
//Tienda CompuCenter, de Bolivia
    public ShopDto extractShop(String url, String url2, ShopDto shopDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo inf. de Tienda 2, página CompuCenter  " + url + "..." +url2);
        Document doc = Jsoup.connect(url).timeout(8000).get();      //para buscar img y ubicacion
        Document doc2 = Jsoup.connect(url2).timeout(8000).get();    //para buscar la descripcion
        Elements descriptionPage = doc2.select(" div.container"); // buscando por clase, <div class = first >
        Elements locationPage = doc.select("div.container");
        Elements imgPage = doc.select(" nav.nav__first");

        String description="";
        String location ="";
        String img="";
        //Description
        for (Element e : descriptionPage.select("div.card-panel.info-page"))
        {
            description = e.select("p:matches(Legalmente)" ).text();
            System.out.println("Descripcion: " + description);
        }
        //location
        for (Element e : locationPage.select("div.card-panel"))
        {
            location = e.select("p:matches(Elóy)" ).text();
        }
        System.out.println("Location: " + location);

        //img
        for (Element e : imgPage.select("div.nav-wrapper"))
        {
            img = e.select(" img ").attr("src"); //Obtener src, img del PC
            System.out.println("Logo de la tienda 1: " + img);
        }
        //ShopBl
        Shop shop = new Shop();
        shop.setName("CompuCenter");
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
    //MARCA Hp, extraccion de marca y guardado en la BD
    public BrandDto extractBrand(String url, BrandDto brandDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo Marca de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements product = doc.select(" div.col.s12.m6.l6.offset-l1");

        String marca="";
        for (Element e : product.select("div.detail__specification"))
        {
            marca = e.select("li:matches(Marca) ").text(); //Obtener marca del PC
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
    public ProductTypeDto extractProductType(String url, ProductTypeDto productTypeDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo tipo de producto de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements producto = doc.select(" div.container");

        String tipoProducto="";
        for (Element e : producto.select("div.detail__specification"))
        {
            tipoProducto = e.select("h5" ).text(); //Obtener tipo de PC
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
        System.out.println("Computadoras, Página CompuCenter url" + url + "...");
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.container");
        Elements imgProduct = doc1.select("div.col.s12.m6.l4");  //extraccion de imagen
        Elements productDescription = doc1.select("div.row.container__tab"); //extracion de detalle del PC

        //extracion del PC
        String name="";
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.detail__specification"))
        {
            name = e.select("h5" ).text(); //Obtener tipo de PC
            System.out.println("Nombre del PC: " + name);
        }
        //imgProduct
        for (Element e : imgProduct.select("div.card-image"))
        {
            img = e.select("  img.materialboxed ").attr("src"); //Obtener src, img del PC
            System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("div.col.s12.tab__body"))
        {
            description = e.select(" p " ).text(); //Obtener marca del PC
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
        Elements producto = doc.select(" div.col.s12.m6.l6");

        String precio="";
        for (Element e : producto.select("div.detail__specification"))
        {
            precio = e.select("li:matches(Bs)  " ).text(); //Obtener precio del PC
            System.out.println("Precio: " + precio + ", se quitara Bs: xq no permite  convertir el precio a Double" );

        }
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        String precio2 = precio.replace("Bs:", "");
        System.out.println("Precio2: " + precio2 + ", se quito exitosamente el Bs: para convertir el precio en Double" );

        //Convertir de String a Double, problema, hay letras adelante de los numeros, Bs: 7980
        Double precioConvertido = Double.valueOf(precio2);
        //System.out.println("Precio: " + precioConvertido );

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
    //MARCA Hp, extraccion de marca y guardado en la BD
    public BrandDto extractBrand2(String url, BrandDto brandDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo Marca de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements product = doc.select(" div.col.s12.m6.l6.offset-l1");

        String marca="";
        for (Element e : product.select("div.detail__specification"))
        {
            marca = e.select("li:matches(Marca) ").text(); //Obtener marca del PC
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
        Elements producto = doc.select(" div.container");

        String tipoProducto="";
        for (Element e : producto.select("div.detail__specification"))
        {
            tipoProducto = e.select("h5" ).text(); //Obtener tipo de PC
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
        System.out.println("Computadoras, Página CompuCenter url" + url + "...");
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.container");
        Elements imgProduct = doc1.select("div.col.s12.m6.l4");  //extraccion de imagen
        Elements productDescription = doc1.select("div.row.container__tab"); //extracion de detalle del PC

        //extracion del PC
        String name="";
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.detail__specification"))
        {
            name = e.select("h5" ).text(); //Obtener tipo de PC
            System.out.println("Nombre del PC: " + name);
        }
        //imgProduct
        for (Element e : imgProduct.select("div.card-image"))
        {
            img = e.select("  img.materialboxed ").attr("src"); //Obtener src, img del PC
            System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("div.col.s12.tab__body"))
        {
            description = e.select(" p " ).text(); //Obtener marca del PC
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
        Elements producto = doc.select(" div.col.s12.m6.l6");//col s12 m6 l6 offset-l1

        String precio="";
        for (Element e : producto.select("div.detail__specification"))
        {
            precio = e.select("li:matches(Bs)  " ).text(); //Obtener precio del PC
            System.out.println("Precio: " + precio + ", se quitara Bs: xq no permite  convertir el precio a Double" );

        }
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        String precio2 = precio.replace("Bs:", "");
        System.out.println("Precio2: " + precio2 + ", se quito exitosamente el Bs: para convertir el precio en Double" );

        //Convertir de String a Double, problema, hay letras adelante de los numeros, Bs: 7980
        Double precioConvertido = Double.valueOf(precio2);
        //System.out.println("Precio: " + precioConvertido );

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
    //MARCA Hp, extraccion de marca y guardado en la BD
    public BrandDto extractBrand3(String url, BrandDto brandDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo Marca de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements product = doc.select(" div.col.s12.m6.l6.offset-l1");

        String marca="";
        for (Element e : product.select("div.detail__specification"))
        {
            marca = e.select("li:matches(Marca) ").text(); //Obtener marca del PC
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
        Elements producto = doc.select(" div.container");

        String tipoProducto="";
        for (Element e : producto.select("div.detail__specification"))
        {
            tipoProducto = e.select("h5" ).text(); //Obtener tipo de PC
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
        System.out.println("Computadoras, Página CompuCenter url" + url + "...");
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.container");
        Elements imgProduct = doc1.select("div.col.s12.m6.l4");  //extraccion de imagen
        Elements productDescription = doc1.select("div.row.container__tab"); //extracion de detalle del PC

        //extracion del PC
        String name="";
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.detail__specification"))
        {
            name = e.select("h5" ).text(); //Obtener tipo de PC
            System.out.println("Nombre del PC: " + name);
        }
        //imgProduct
        for (Element e : imgProduct.select("div.card-image"))
        {
            img = e.select("  img.materialboxed ").attr("src"); //Obtener src, img del PC
            System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("div.col.s12.tab__body"))
        {
            description = e.select(" p " ).text(); //Obtener marca del PC
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
        Elements producto = doc.select(" div.col.s12.m6.l6");//col s12 m6 l6 offset-l1

        String precio="";
        for (Element e : producto.select("div.detail__specification"))
        {
            precio = e.select("li:matches(Bs)  " ).text(); //Obtener precio del PC
            System.out.println("Precio: " + precio + ", se quitara Bs: xq no permite  convertir el precio a Double" );

        }
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        String precio2 = precio.replace("Bs:", "");
        System.out.println("Precio2: " + precio2 + ", se quito exitosamente el Bs: para convertir el precio en Double" );

        //Convertir de String a Double, problema, hay letras adelante de los numeros, Bs: 7980
        Double precioConvertido = Double.valueOf(precio2);
        //System.out.println("Precio: " + precioConvertido );

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