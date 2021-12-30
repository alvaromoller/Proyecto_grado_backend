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
public class ComputerPageThree {

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
    public ComputerPageThree(BrandDao brandDao, ProductDao productDao, ProductTypeDao productTypeDao, ShopDao shopDao, ProductDetailDao productDetailDao, TransactionDao transactionDao) {
        this.brandDao = brandDao;
        this.productTypeDao = productTypeDao;
        this.shopDao = shopDao;
        this.productDao = productDao;
        this.productDetailDao = productDetailDao;
        this.transactionDao = transactionDao;
    }

    public ComputerPageThree() {

    }



/** Con Base de Datos
//Tienda 3, que contiene productos 1, 2 y 3
//Tienda Multilaptos, La Paz Bolivia
    public ShopDto extractShop(String url, ShopDto shopDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo inf. de Tienda 3, página CompuCenter  " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();      //para buscar img y ubicacion
        Elements descriptionPage = doc.select(" section.content-section.bg-light.mt-5.mb-2.py-5.col-12"); // buscando por clase, <div class = first >
        Elements locationPage = doc.select("section.content-section.col-12.col-md-6.p-0.pr-2.d-flex");
        Elements imgPage = doc.select(" div.carousel-inner");

        String description = "";
        String location = "";
        String img = "";
        //Description
        for (Element e : descriptionPage.select("div.col-lg-10")) {
            description = e.select(" p ").text();
            System.out.println("Descripcion: " + description);
        }
        //location
        for (Element e : locationPage.select("div.col-lg-10")) {
            location = e.select("p ").text();
        }
        System.out.println("Location: " + location);

        //img
        for (Element e : imgPage.select("div.carousel-item ")) {
            img = e.select(" img ").attr("src"); //Obtener src, img del PC
            System.out.println("Logo de la tienda 3: " + img);
        }
        //ShopBl
        Shop shop = new Shop();
        shop.setName("Multilaptops");
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
        return shopDto;
    }

    //Producto 1
    //MARCA Hp, extraccion de marca y guardado en la BD
    public BrandDto extractBrand(String url, BrandDto brandDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo Marca de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements product = doc.select(" div.col-12.col-md-6.px-1.px-md-1.my-0.my-md-0");

        String marca = "";
        for (Element e : product.select("div.px-2.titulo ")) {
            marca = e.select(" span ").text(); //Obtener marca del PC
        }
        System.out.println("Marca: " + marca);
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
        return brandDto;
    }

    //Tipo de Producto, extraccion de Tipo de Producto y guardado en la BD
    public ProductTypeDto extractProductType(String url, ProductTypeDto productTypeDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo tipo de producto de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements producto = doc.select(" div.col-12.col-md-6.px-1.px-md-1.my-0.my-md-0 ");

        String tipoProducto = "";
        for (Element e : producto.select("div.px-2.titulo ")) {
            tipoProducto = e.select(" span ").text(); //Obtener tipo de PC
        }
        System.out.println("Tipo de PC: " + tipoProducto);
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
        Elements productName = doc1.select(" div.col-12.col-md-6.px-1.px-md-1.my-0.my-md-0");
        Elements imgProduct = doc1.select("div.card-body.pt-2");  //extraccion de imagen
        Elements productDescription = doc1.select("div.col-12.boxe"); //extracion de detalle del PC
        Elements price = doc1.select(" div.card-body.bg-transparent");

        //Salto de linea productDescription
        productDescription.select("br").append("\\nl"); //append salto de linea
        productDescription.select("div").append("\\nl");
        //productDescription.select("p").prepend("\\nl"); //append salto de linea
        //productDescription.select("p").append("\\nl");
        productDescription.select("li").append("\\nl");
        //

        //extracion del PC
        String name = "";
        String img = "";
        String description = "";

        //productName
        for (Element e : productName.select("div.px-2.titulo ")) {
            name = e.select(" span ").text(); //Obtener tipo de PC
        }
        System.out.println("Nombre del PC: " + name);

        //imgProduct
        for (Element e : imgProduct.select("div.row")) {
            img = e.select("   div a  ").attr("href"); //Obtener src, img del PC
            System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("ul.list-group")) {
            description = e.select(" li ").text().replaceAll("\\\\nl", "\n"); //Obtener marca del PC
            System.out.println("Descripción: \n" + description);
        }

        String precio = "";
        for (Element e : price.select("div.col-6.p-0.m-0.let.precioB.d-block")) {
            precio = e.select(" div:matches(Bs.)   ").text(); //Obtener precio del PC
            System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double \n");
        }
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        String precio2 = precio.replace("Bs.", "");
        System.out.println("Precio2: " + precio2 + " , se quito exitosamente el Bs: para convertir el precio en Double");

        //Convertir de String a Double, problema, hay letras adelante de los numeros, Bs: 7980
        Double precioConvertido = Double.parseDouble(precio2.replaceAll(",", "."));
        System.out.println("Precio2: " + precioConvertido);

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
        return productDto;
    }

    //Detalle producto, precio y cantidad
    public ProductDetailDto extractDetail(String url, ProductDetailDto productDetailDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo Precio del producto " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements producto = doc.select(" div.card-body.bg-transparent");

        String precio = "";
        for (Element e : producto.select("div.col-6.p-0.m-0.let.precioB.d-block")) {
            precio = e.select(" div:matches(Bs.)   ").text(); //Obtener precio del PC
            System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double \n");
        }
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        String precio2 = precio.replace("Bs.", "");
        System.out.println("Precio2: " + precio2 + " , se quito exitosamente el Bs: para convertir el precio en Double");

        //Convertir de String a Double, problema, hay letras adelante de los numeros, Bs: 7980
        Double precioConvertido = Double.parseDouble(precio2.replaceAll(",", "."));
        System.out.println("Precio2: " + precioConvertido);

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
        return productDetailDto;
    }


    //Producto 2
    //MARCA Hp, extraccion de marca y guardado en la BD
    public BrandDto extractBrand2(String url, BrandDto brandDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo Marca de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements product = doc.select(" div.col-12.col-md-6.px-1.px-md-1.my-0.my-md-0");

        String marca = "";
        for (Element e : product.select("div.px-2.titulo ")) {
            marca = e.select(" span ").text(); //Obtener marca del PC
        }
        System.out.println("Marca: " + marca);
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
        return brandDto;
    }

    //Tipo de Producto, extraccion de Tipo de Producto y guardado en la BD
    public ProductTypeDto extractProductType2(String url, ProductTypeDto productTypeDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo tipo de producto de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements producto = doc.select(" div.col-12.col-md-6.px-1.px-md-1.my-0.my-md-0 ");

        String tipoProducto = "";
        for (Element e : producto.select("div.px-2.titulo ")) {
            tipoProducto = e.select(" span ").text(); //Obtener tipo de PC
        }
        System.out.println("Tipo de PC: " + tipoProducto);
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
        Elements productName = doc1.select(" div.col-12.col-md-6.px-1.px-md-1.my-0.my-md-0");
        Elements imgProduct = doc1.select("div.card-body.pt-2");  //extraccion de imagen
        Elements productDescription = doc1.select("div.col-12.boxe"); //extracion de detalle del PC
        Elements price = doc1.select(" div.card-body.bg-transparent");

        //Salto de linea productDescription
        productDescription.select("br").append("\\nl"); //append salto de linea
        productDescription.select("div").append("\\nl");
        //productDescription.select("p").prepend("\\nl"); //append salto de linea
        //productDescription.select("p").append("\\nl");
        productDescription.select("li").append("\\nl");
        //
        //extracion del PC
        String name = "";
        String img = "";
        String description = "";

        //productName
        for (Element e : productName.select("div.px-2.titulo ")) {
            name = e.select(" span ").text(); //Obtener tipo de PC
        }
        System.out.println("Nombre del PC: " + name);

        //imgProduct
        for (Element e : imgProduct.select("div.row")) {
            img = e.select("   div a  ").attr("href"); //Obtener src, img del PC
            System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("ul.list-group")) {
            description = e.select(" li ").text().replaceAll("\\\\nl", "\n"); //Obtener marca del PC
            System.out.println("Descripción: \n" + description);
        }

        String precio = "";
        for (Element e : price.select("div.col-6.p-0.m-0.let.precioB.d-block")) {
            precio = e.select(" div:matches(Bs.)   ").text(); //Obtener precio del PC
            System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double \n");
        }
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        String precio2 = precio.replace("Bs.", "");
        System.out.println("Precio2: " + precio2 + " , se quito exitosamente el Bs: para convertir el precio en Double");

        //Convertir de String a Double, problema, hay letras adelante de los numeros, Bs: 7980
        Double precioConvertido = Double.parseDouble(precio2.replaceAll(",", "."));
        System.out.println("Precio2: " + precioConvertido);

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
        return productDto;
    }

    //Detalle producto, precio y cantidad
    public ProductDetailDto extractDetail2(String url, ProductDetailDto productDetailDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo Precio del producto " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements producto = doc.select(" div.card-body.bg-transparent");

        String precio = "";
        for (Element e : producto.select("div.col-6.p-0.m-0.let.precioB.d-block")) {
            precio = e.select(" div:matches(Bs.)   ").text(); //Obtener precio del PC
            System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double \n");
        }
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        String precio2 = precio.replace("Bs.", "");
        System.out.println("Precio2: " + precio2 + " , se quito exitosamente el Bs: para convertir el precio en Double");

        //Convertir de String a Double, problema, hay letras adelante de los numeros, Bs: 7980
        Double precioConvertido = Double.parseDouble(precio2.replaceAll(",", "."));
        System.out.println("Precio2: " + precioConvertido);

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
        return productDetailDto;
    }


    //Producto 3
    //MARCA Hp, extraccion de marca y guardado en la BD
    public BrandDto extractBrand3(String url, BrandDto brandDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo Marca de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements product = doc.select(" div.col-12.col-md-6.px-1.px-md-1.my-0.my-md-0");

        String marca = "";
        for (Element e : product.select("div.px-2.titulo ")) {
            marca = e.select(" span ").text(); //Obtener marca del PC
        }
        System.out.println("Marca: " + marca);
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
        return brandDto;
    }

    //Tipo de Producto, extraccion de Tipo de Producto y guardado en la BD
    public ProductTypeDto extractProductType3(String url, ProductTypeDto productTypeDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo tipo de producto de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements producto = doc.select(" div.col-12.col-md-6.px-1.px-md-1.my-0.my-md-0 ");

        String tipoProducto = "";
        for (Element e : producto.select("div.px-2.titulo ")) {
            tipoProducto = e.select(" span ").text(); //Obtener tipo de PC
        }
        System.out.println("Tipo de PC: " + tipoProducto);
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
        Elements productName = doc1.select(" div.col-12.col-md-6.px-1.px-md-1.my-0.my-md-0");
        Elements imgProduct = doc1.select("div.card-body.pt-2");  //extraccion de imagen
        Elements productDescription = doc1.select("div.col-12.boxe"); //extracion de detalle del PC
        Elements price = doc1.select(" div.card-body.bg-transparent");

        //Salto de linea productDescription
        productDescription.select("br").append("\\nl"); //append salto de linea
        productDescription.select("div").append("\\nl");
        //productDescription.select("p").prepend("\\nl"); //append salto de linea
        //productDescription.select("p").append("\\nl");
        productDescription.select("li").append("\\nl");
        //
        //extracion del PC
        String name = "";
        String img = "";
        String description = "";

        //productName
        for (Element e : productName.select("div.px-2.titulo ")) {
            name = e.select(" span ").text(); //Obtener tipo de PC
        }
        System.out.println("Nombre del PC: " + name);

        //imgProduct
        for (Element e : imgProduct.select("div.row")) {
            img = e.select("   div a  ").attr("href"); //Obtener src, img del PC
            System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("ul.list-group")) {
            description = e.select(" li ").text().replaceAll("\\\\nl", "\n"); //Obtener marca del PC
            System.out.println("Descripción: \n" + description);
        }

        String precio = "";
        for (Element e : price.select("div.col-6.p-0.m-0.let.precioB.d-block")) {
            precio = e.select(" div:matches(Bs.)   ").text(); //Obtener precio del PC
            System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double \n");
        }
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        String precio2 = precio.replace("Bs.", "");
        System.out.println("Precio2: " + precio2 + " , se quito exitosamente el Bs: para convertir el precio en Double");

        //Convertir de String a Double, problema, hay letras adelante de los numeros, Bs: 7980
        Double precioConvertido = Double.parseDouble(precio2.replaceAll(",", "."));
        System.out.println("Precio2: " + precioConvertido);

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
        return productDto;
    }

    //Detalle producto, precio y cantidad
    public ProductDetailDto extractDetail3(String url, ProductDetailDto productDetailDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo Precio del producto " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements producto = doc.select(" div.card-body.bg-transparent");

        String precio = "";
        for (Element e : producto.select("div.col-6.p-0.m-0.let.precioB.d-block")) {
            precio = e.select(" div:matches(Bs.)   ").text(); //Obtener precio del PC
            System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double \n");
        }
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        String precio2 = precio.replace("Bs.", "");
        System.out.println("Precio2: " + precio2 + " , se quito exitosamente el Bs: para convertir el precio en Double");

        //Convertir de String a Double, problema, hay letras adelante de los numeros, Bs: 7980
        Double precioConvertido = Double.parseDouble(precio2.replaceAll(",", "."));
        System.out.println("Precio2: " + precioConvertido);

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
        return productDetailDto;
    }


//ACTUALIZACIONES
//Tienda 3: Multilaptops que contiene productos 1, 2 y 3
    public ShopDto updateShop(String url, ShopDto shopDto, Transaction transaction) throws IOException {
        System.out.println("Extrayendo inf. de Tienda 3, página CompuCenter  " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();      //para buscar img y ubicacion
        Elements descriptionPage = doc.select(" section.content-section.bg-light.mt-5.mb-2.py-5.col-12"); // buscando por clase, <div class = first >
        Elements locationPage = doc.select("section.content-section.col-12.col-md-6.p-0.pr-2.d-flex");
        Elements imgPage = doc.select(" div.carousel-inner");

        String description = "";
        String location = "";
        String img = "";
        //Description
        for (Element e : descriptionPage.select("div.col-lg-10")) {
            description = e.select(" p ").text();
            System.out.println("Descripcion: " + description);
        }
        //location
        for (Element e : locationPage.select("div.col-lg-10")) {
            location = e.select("p ").text();
        }
        System.out.println("Location: " + location);

        //img
        for (Element e : imgPage.select("div.carousel-item ")) {
            img = e.select(" img ").attr("src"); //Obtener src, img del PC
            System.out.println("Logo de la tienda 3: " + img);
        }
        //ShopBl Update
        Shop shop= new Shop();
        shop.setShopId(shopDto.getShopId());
        shop.setName("Multilaptops");
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
    System.out.println("Computadoras, Página CompuCenter url" + url + "...");
    Document doc1 = Jsoup.connect(url).timeout(10000).get();
    Elements productName = doc1.select(" div.col-12.col-md-6.px-1.px-md-1.my-0.my-md-0");
    Elements imgProduct = doc1.select("div.card-body.pt-2");  //extraccion de imagen
    Elements productDescription = doc1.select("div.col-12.boxe"); //extracion de detalle del PC
    Elements price = doc1.select(" div.card-body.bg-transparent");

    //Salto de linea productDescription
    productDescription.select("br").append("\\nl"); //append salto de linea
    productDescription.select("div").append("\\nl");
    //productDescription.select("p").prepend("\\nl"); //append salto de linea
    //productDescription.select("p").append("\\nl");
    productDescription.select("li").append("\\nl");
    //

    //extracion del PC
    String name = "";
    String img = "";
    String description = "";

    //productName
    for (Element e : productName.select("div.px-2.titulo ")) {
        name = e.select(" span ").text(); //Obtener tipo de PC
    }
    System.out.println("Nombre del PC: " + name);

    //imgProduct
    for (Element e : imgProduct.select("div.row")) {
        img = e.select("   div a  ").attr("href"); //Obtener src, img del PC
        System.out.println("imagen : " + img);
    }

    //pruductDescription
    for (Element e : productDescription.select("ul.list-group")) {
        description = e.select(" li ").text().replaceAll("\\\\nl", "\n"); //Obtener marca del PC
        System.out.println("Descripción: \n" + description);
    }

    String precio = "";
    for (Element e : price.select("div.col-6.p-0.m-0.let.precioB.d-block")) {
        precio = e.select(" div:matches(Bs.)   ").text(); //Obtener precio del PC
        System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double \n");
    }
    //En replace se quito exitosamente el Bs: para convertir el precio en Double
    String precio2 = precio.replace("Bs.", "");
    System.out.println("Precio2: " + precio2 + " , se quito exitosamente el Bs: para convertir el precio en Double");

    //Convertir de String a Double, problema, hay letras adelante de los numeros, Bs: 7980
    Double precioConvertido = Double.parseDouble(precio2.replaceAll(",", "."));
    System.out.println("Precio2: " + precioConvertido);

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
    System.out.println("Computadoras, Página CompuCenter url" + url + "...");
    Document doc1 = Jsoup.connect(url).timeout(10000).get();
    Elements productName = doc1.select(" div.col-12.col-md-6.px-1.px-md-1.my-0.my-md-0");
    Elements imgProduct = doc1.select("div.card-body.pt-2");  //extraccion de imagen
    Elements productDescription = doc1.select("div.col-12.boxe"); //extracion de detalle del PC
    Elements price = doc1.select(" div.card-body.bg-transparent");

    //Salto de linea productDescription
    productDescription.select("br").append("\\nl"); //append salto de linea
    productDescription.select("div").append("\\nl");
    //productDescription.select("p").prepend("\\nl"); //append salto de linea
    //productDescription.select("p").append("\\nl");
    productDescription.select("li").append("\\nl");
    //
    //extracion del PC
    String name = "";
    String img = "";
    String description = "";

    //productName
    for (Element e : productName.select("div.px-2.titulo ")) {
        name = e.select(" span ").text(); //Obtener tipo de PC
    }
    System.out.println("Nombre del PC: " + name);

    //imgProduct
    for (Element e : imgProduct.select("div.row")) {
        img = e.select("   div a  ").attr("href"); //Obtener src, img del PC
        System.out.println("imagen : " + img);
    }

    //pruductDescription
    for (Element e : productDescription.select("ul.list-group")) {
        description = e.select(" li ").text().replaceAll("\\\\nl", "\n"); //Obtener marca del PC
        System.out.println("Descripción: \n" + description);
    }

    String precio = "";
    for (Element e : price.select("div.col-6.p-0.m-0.let.precioB.d-block")) {
        precio = e.select(" div:matches(Bs.)   ").text(); //Obtener precio del PC
        System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double \n");
    }
    //En replace se quito exitosamente el Bs: para convertir el precio en Double
    String precio2 = precio.replace("Bs.", "");
    System.out.println("Precio2: " + precio2 + " , se quito exitosamente el Bs: para convertir el precio en Double");

    //Convertir de String a Double, problema, hay letras adelante de los numeros, Bs: 7980
    Double precioConvertido = Double.parseDouble(precio2.replaceAll(",", "."));
    System.out.println("Precio2: " + precioConvertido);

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
    System.out.println("Computadoras, Página CompuCenter url" + url + "...");
    Document doc1 = Jsoup.connect(url).timeout(10000).get();
    Elements productName = doc1.select(" div.col-12.col-md-6.px-1.px-md-1.my-0.my-md-0");
    Elements imgProduct = doc1.select("div.card-body.pt-2");  //extraccion de imagen
    Elements productDescription = doc1.select("div.col-12.boxe"); //extracion de detalle del PC
    Elements price = doc1.select(" div.card-body.bg-transparent");

    //Salto de linea productDescription
    productDescription.select("br").append("\\nl"); //append salto de linea
    productDescription.select("div").append("\\nl");
    //productDescription.select("p").prepend("\\nl"); //append salto de linea
    //productDescription.select("p").append("\\nl");
    productDescription.select("li").append("\\nl");
    //
    //extracion del PC
    String name = "";
    String img = "";
    String description = "";

    //productName
    for (Element e : productName.select("div.px-2.titulo ")) {
        name = e.select(" span ").text(); //Obtener tipo de PC
    }
    System.out.println("Nombre del PC: " + name);

    //imgProduct
    for (Element e : imgProduct.select("div.row")) {
        img = e.select("   div a  ").attr("href"); //Obtener src, img del PC
        System.out.println("imagen : " + img);
    }

    //pruductDescription
    for (Element e : productDescription.select("ul.list-group")) {
        description = e.select(" li ").text().replaceAll("\\\\nl", "\n"); //Obtener marca del PC
        System.out.println("Descripción: \n" + description);
    }

    String precio = "";
    for (Element e : price.select("div.col-6.p-0.m-0.let.precioB.d-block")) {
        precio = e.select(" div:matches(Bs.)   ").text(); //Obtener precio del PC
        System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double \n");
    }
    //En replace se quito exitosamente el Bs: para convertir el precio en Double
    String precio2 = precio.replace("Bs.", "");
    System.out.println("Precio2: " + precio2 + " , se quito exitosamente el Bs: para convertir el precio en Double");

    //Convertir de String a Double, problema, hay letras adelante de los numeros, Bs: 7980
    Double precioConvertido = Double.parseDouble(precio2.replaceAll(",", "."));
    System.out.println("Precio2: " + precioConvertido);

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


}
