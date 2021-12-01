package webcrawler.prueba.webCrawler;

import org.apache.ibatis.annotations.Mapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.BrandDao;
import webcrawler.prueba.dao.ProductDao;
import webcrawler.prueba.dao.ProductTypeDao;
import webcrawler.prueba.dao.TransactionDao;
import webcrawler.prueba.dto.BrandDto;
import webcrawler.prueba.model.Brand;
import webcrawler.prueba.model.Product;
import webcrawler.prueba.model.ProductType;
import webcrawler.prueba.model.Transaction;

import java.io.IOException;

@Service
public class ComputerPageOne {

    //brand
    private BrandDao brandDao;
    private TransactionDao transactionDao;
    //private BrandDto brandDto;
    //private Transaction transaction;

    //ProductType
    private ProductTypeDao productTypeDao;
    //Product
    private ProductDao productDao;

    @Autowired
    public ComputerPageOne(BrandDao brandDao, ProductDao productDao, ProductTypeDao productTypeDao, TransactionDao transactionDao){
        this.brandDao = brandDao;
        this.productTypeDao = productTypeDao;
        this.productDao = productDao;
        this.transactionDao = transactionDao;
        //this.brandDto = brandDto;
        //this.transaction = transaction;
    }

    public ComputerPageOne() {

    }

    //MARCA
    //extraccion de marca y guardado en la BD
    public void extractBrand(String url, Transaction transaction) throws IOException {
        System.out.println("Extrayendo Marca de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements header = doc.select(" div.woocommerce-tabs.wc-tabs-wrapper");

        String marca="";
        for (Element e : header.select("div.woocommerce-Tabs-panel.woocommerce-Tabs-panel--description.panel.entry-content.wc-tab"))
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
        //Integer getLastId = transactionDao.getLastInsertId();
        //brandDto.setBrandId(getLastId);
    }


    public void extractProductType(String url, Transaction transaction) throws IOException {
        System.out.println("Extrayendo tipo de producto de la página " + url + "...");
        Document doc = Jsoup.connect(url).timeout(8000).get();
        Elements header = doc.select(" div.woocommerce-tabs.wc-tabs-wrapper");

        String tipoProducto="";
        for (Element e : header.select("div.woocommerce-Tabs-panel.woocommerce-Tabs-panel--description.panel.entry-content.wc-tab"))
        {
            tipoProducto = e.select("p  a:matches(Notebook|notebook|NOTEBOOK)" ).text(); //Obtener tipo de PC
            System.out.println("Tipo de PC: " + tipoProducto);
        }

        //brandBl
        ProductType productType = new ProductType();
        productType.setName(tipoProducto);
        //transaction
        productType.setTxId(transaction.getTxId());
        productType.setTxHost(transaction.getTxHost());
        productType.setTxUserId(transaction.getTxUserId());
        productType.setTxDate(transaction.getTxDate());
        productType.setStatus(1);
        //productTypeDao.create(productType);
        //Integer getLastId = transactionDao.getLastInsertId();
        //brandDto.setBrandId(getLastId);
    }


    //PRODUCTO url1
    //Transaction transaction
    public void extractProduct(String url1, Transaction transaction) throws IOException {
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
        for (Element e : pc1.select("div.woocommerce-Tabs-panel.woocommerce-Tabs-panel--description.panel.entry-content.wc-tab"))
        {
            //Datos del producto
            nameProduct = e.select("p strong span ").text(); //Obtener nombre del PC
            processor = e.select("p:matches(Intel core i7 1135G7|intel core i7 1135G7|INTEL core i7 1135G7)" ).text(); //Obtener procesador de PC
            ram = e.select("p:matches(Memoria RAM)" ).text(); //Obtener procesador de PC
            discoAlmacenamiento = e.select("p:matches(Disco)" ).text(); //Obtener procesador de PC
            video = e.select("p:matches(Video)" ).text(); //Obtener procesador de PC
            pantalla = e.select("p:matches(Pantalla)" ).text(); //Obtener procesador de PC
            description = e.select("p:matches(Garantía)" ).text(); //Obtener procesador de PC

            System.out.println("imagen: " + img); //llamando Img del primer For
            System.out.println("Nombre: " + nameProduct);
            System.out.println(processor); // Procesador
            System.out.println(ram); // Memoria RAM
            System.out.println(discoAlmacenamiento); // Disco de Almacenamiento
            System.out.println(video); // tarjeta de video
            System.out.println(pantalla); // medidas de la pantalla
            System.out.println(description); // descripcion de la garantía

            //Bl createProduct
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
            //Integer getLastId = transactionDao.getLastInsertId();
            //productDto.setProductId(getLastId);
        }
    }


    //Extraccion prueba de PRODUCTO IMG
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



    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }



}
