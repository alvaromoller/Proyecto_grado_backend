package webcrawler.prueba.webCrawler;

import com.google.gson.Gson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.tomcat.util.json.JSONParser;
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
import java.util.ArrayList;
import java.util.List;

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
    //


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


//TIENDAS 1, 2, 3

//LISTADO de PRODUCTOS, extraccion de Producto  sin  BD
    //tienda 1, Dismac
    //PRODUCTO 1
    public List<ProductDto> extractProductList(String url) throws IOException {
        System.out.println("Computadoras, Página Dismac url1" + url );
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.columns");
        Elements imgProduct = doc1.select("div.gallery-placeholder");  //extraccion de imagen
        Elements productDescription = doc1.select("div.data.item.content"); //extracion de detalle del PC
        Elements price = doc1.select(" div.product-info-price");

        //extracion del PC
        String name = "";
        String img = "";
        String description = "";

        //productName
        for (Element e : productName.select("div.page-title-wrapper.product")) {
            name = e.select("h1 span").text(); //Obtener tipo de PC
            //System.out.println("Nombre del PC: " + name);
        }
        //imgProduct
        for (Element e : imgProduct.select("div")) {
            img = e.select("div img").attr("src"); //Obtener src, img del PC
            //System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("div.product.attribute.description")) {
            description = e.select("div.value ").text(); //Obtener marca del PC
            //System.out.println("Descripción: \n" + description);
        }

        String precio = "";
        for (Element e : price.select("div.price-box.price-final_price")) {
            precio = e.select("span.pixel-price ").text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio + ".00");

        }
        //Convertir de String a Double
        //Double precioConvertido = Double.parseDouble(precio.replace(",", "."));
        //System.out.println("Precio: " + precioConvertido + ".00");

        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        ProductDto productDto = new ProductDto();

        productDto.setProductId(1);
        productDto.setName(name);
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(1);
        productDto.setCategoryId(1);

        productDtos.add( productDto);
        System.out.println("productDtos: " + productDtos);
        return  productDtos;
    }

    //PRODUCTO 2
    public List<ProductDto> extractProductList2(String url) throws IOException {
        //System.out.println("Computadoras, Página Dismac url1" + url + "...");
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
            //System.out.println("Nombre del PC: " + name);
        }
        //imgProduct
        for (Element e : imgProduct.select("div"))
        {
            img = e.select("div img").attr("src"); //Obtener src, img del PC
            //System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("div.product.attribute.description"))
        {
            description = e.select("div.value " ).text(); //Obtener marca del PC
            //System.out.println("Descripción: \n" + description);
        }

        String precio="";
        for (Element e : price.select("span.pixel-price"))
        {
            precio = e.select("span.pixel-price " ).text(); //Obtener precio del PC
        }
        //System.out.println("Precio INT: " + precio );

        //Convertir de String a Double
        //Double precioConvertido = Double.parseDouble(precio.replace("," , "."));
        //System.out.println("PrecioConvertido: " + precioConvertido );

        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        ProductDto productDto = new ProductDto();

        productDto.setProductId(2);
        productDto.setName(name);
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(1);

        productDtos.add( productDto);
        System.out.println("productDtos 2 : " + productDtos);
        return  productDtos;
    }

    //PRODUCTO 3
    public List<ProductDto> extractProductList3(String url) throws IOException {
        //System.out.println("Computadoras, Página Dismac url1" + url + "...");
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
            //System.out.println("Nombre del PC: " + name);
        }
        //imgProduct
        for (Element e : imgProduct.select("div"))
        {
            img = e.select("div img").attr("src"); //Obtener src, img del PC
            //System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("div.product.attribute.description"))
        {
            description = e.select("div.value " ).text(); //Obtener marca del PC
            //System.out.println("Descripción: \n" + description);
        }

        String precio="";
        for (Element e : price.select("span.pixel-price"))
        {
            precio = e.select("span.pixel-price " ).text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio + ".00");

        }
        //Convertir de String a Double
        //Double precioConvertido = Double.parseDouble(precio.replace("," , "."));
        //System.out.println("Precio: " + precioConvertido + ".00");

        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        ProductDto productDto = new ProductDto();

        productDto.setProductId(3);
        productDto.setName(name);
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(1);

        productDtos.add( productDto);
        System.out.println("productDtos 3: " + productDtos);
        return  productDtos;
    }

    //tienda 2, CompuCenter
    //Producto 4
    public List<ProductDto> extractProductList4(String url) throws IOException {
        System.out.println("Computadoras, Página CompuCenter url2" + url );
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.w-full");
        Elements imgProduct = doc1.select("div.w-full");  //extraccion de imagen
        Elements productDescription = doc1.select("div.w-full.pt-4"); //extracion de detalle del PC
        Elements price = doc1.select(" div.w-full");

        //extracion del PC
        String name="";
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.mb-10"))
        {
            name = e.select("h1" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre del PC: " + name);

        //imgProduct
        for (Element e : imgProduct.select("div.relative.border-4.border-red-100.rounded-lg"))
        {
            img = e.select("  img ").attr("src"); //Obtener src, img del PC
            //System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("div.text-base"))
        {
            description = e.select(" div " ).text(); //Obtener marca del PC
            //System.out.println("Descripción: \n" + description);
        }

        String precio="";
        for (Element e : price.select("div.inline-block.align-bottom.mr-5"))
        {
            precio = e.select("span  " ).text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double" );

        }
        /**
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
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

        productDto.setProductId(4);
        productDto.setName(name);
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(2);

        productDtos.add( productDto);
        System.out.println("productDtos 4: " + productDtos);
        return  productDtos;
    }

    //Producto 5
    public List<ProductDto> extractProductList5(String url) throws IOException {
        System.out.println("Computadoras, Página CompuCenter url" + url );
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.w-full");
        Elements imgProduct = doc1.select("div.w-full");  //extraccion de imagen
        Elements productDescription = doc1.select("div.w-full.pt-4"); //extracion de detalle del PC
        Elements price = doc1.select(" div.w-full");

        //extracion del PC
        String name="";
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.mb-10"))
        {
            name = e.select("h1" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre del PC: " + name);

        //imgProduct
        for (Element e : imgProduct.select("div.relative.border-4.border-red-100.rounded-lg"))
        {
            img = e.select("  img ").attr("src"); //Obtener src, img del PC
            //System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("div.text-base"))
        {
            description = e.select(" div " ).text(); //Obtener marca del PC
            //System.out.println("Descripción: \n" + description);
        }

        String precio="";
        for (Element e : price.select("div.inline-block.align-bottom.mr-5"))
        {
            precio = e.select("span  " ).text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double" );

        }
        /**
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
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

        productDto.setProductId(5);
        productDto.setName(name);
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(2);

        productDtos.add( productDto);
        System.out.println("productDtos 5: " + productDtos);
        return  productDtos;
    }

    //Producto 6
    public List<ProductDto> extractProductList6(String url) throws IOException {
        System.out.println("Computadoras, Página CompuCenter url" + url );
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.w-full");
        Elements imgProduct = doc1.select("div.w-full");  //extraccion de imagen
        Elements productDescription = doc1.select("div.w-full.pt-4"); //extracion de detalle del PC
        Elements price = doc1.select(" div.w-full");

        //extracion del PC
        String name="";
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.mb-10"))
        {
            name = e.select("h1" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre del PC: " + name);

        //imgProduct
        for (Element e : imgProduct.select("div.relative.border-4.border-red-100.rounded-lg"))
        {
            img = e.select("  img ").attr("src"); //Obtener src, img del PC
            System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("div.text-base"))
        {
            description = e.select(" div " ).text(); //Obtener marca del PC
            //System.out.println("Descripción: \n" + description);
        }

        String precio="";
        for (Element e : price.select("div.inline-block.align-bottom.mr-5"))
        {
            precio = e.select("span  " ).text(); //Obtener precio del PC
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

        productDto.setProductId(6);
        productDto.setName(name);
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(6);

        productDtos.add( productDto);
        System.out.println("productDtos 6: " + productDtos);
        return  productDtos;
    }


    //CompuCenter
    //Producto7
    public List<ProductDto> extractProductList7(String url) throws IOException {
        System.out.println("Computadoras, Página CompuCenter url" + url );
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.w-full");
        Elements imgProduct = doc1.select("div.w-full");  //extraccion de imagen
        Elements productDescription = doc1.select("div.w-full.pt-4"); //extracion de detalle del PC
        Elements price = doc1.select(" div.w-full");

        //extracion del PC
        String name="";
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.mb-10"))
        {
            name = e.select("h1" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre del PC: " + name);

        //imgProduct
        for (Element e : imgProduct.select("div.relative.border-4.border-red-100.rounded-lg"))
        {
            img = e.select("  img ").attr("src"); //Obtener src, img del PC
            System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("div.text-base"))
        {
            description = e.select(" div " ).text(); //Obtener marca del PC
            //System.out.println("Descripción: \n" + description);
        }

        String precio="";
        for (Element e : price.select("div.inline-block.align-bottom.mr-5"))
        {
            precio = e.select("span  " ).text(); //Obtener precio del PC
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

        productDto.setProductId(6);
        productDto.setName(name);
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(6);

        productDtos.add( productDto);
        System.out.println("productDtos 6: " + productDtos);
        return  productDtos;
    }

    //Producto 8
    public List<ProductDto> extractProductList8(String url) throws IOException {
        System.out.println("Computadoras, Página CompuCenter url" + url );
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.w-full");
        Elements imgProduct = doc1.select("div.w-full");  //extraccion de imagen
        Elements productDescription = doc1.select("div.w-full.pt-4"); //extracion de detalle del PC
        Elements price = doc1.select(" div.w-full");

        //extracion del PC
        String name="";
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.mb-10"))
        {
            name = e.select("h1" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre del PC: " + name);

        //imgProduct
        for (Element e : imgProduct.select("div.relative.border-4.border-red-100.rounded-lg"))
        {
            img = e.select("  img ").attr("src"); //Obtener src, img del PC
            System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("div.text-base"))
        {
            description = e.select(" div " ).text(); //Obtener marca del PC
            //System.out.println("Descripción: \n" + description);
        }

        String precio="";
        for (Element e : price.select("div.inline-block.align-bottom.mr-5"))
        {
            precio = e.select("span  " ).text(); //Obtener precio del PC
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

        productDto.setProductId(6);
        productDto.setName(name);
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(6);

        productDtos.add( productDto);
        System.out.println("productDtos 6: " + productDtos);
        return  productDtos;
    }

    //Producto 9
    public List<ProductDto> extractProductList9(String url) throws IOException {
        System.out.println("Computadoras, Página CompuCenter url" + url );
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.w-full");
        Elements imgProduct = doc1.select("div.w-full");  //extraccion de imagen
        Elements productDescription = doc1.select("div.w-full.pt-4"); //extracion de detalle del PC
        Elements price = doc1.select(" div.w-full");

        //extracion del PC
        String name="";
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.mb-10"))
        {
            name = e.select("h1" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre del PC: " + name);

        //imgProduct
        for (Element e : imgProduct.select("div.relative.border-4.border-red-100.rounded-lg"))
        {
            img = e.select("  img ").attr("src"); //Obtener src, img del PC
            System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("div.text-base"))
        {
            description = e.select(" div " ).text(); //Obtener marca del PC
            //System.out.println("Descripción: \n" + description);
        }

        String precio="";
        for (Element e : price.select("div.inline-block.align-bottom.mr-5"))
        {
            precio = e.select("span  " ).text(); //Obtener precio del PC
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

        productDto.setProductId(6);
        productDto.setName(name);
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(6);

        productDtos.add( productDto);
        System.out.println("productDtos 6: " + productDtos);
        return  productDtos;
    }


    /** Tienda 3 Multilaptops Producto 9, problemas con la direccion de esta pagina
    public List<ProductDto> extractProductList9(String url) throws IOException {
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
        //String precio2 = precio.replace("Bs.", "");
        //System.out.println("Precio2: " + precio2 + " , se quito exitosamente el Bs: para convertir el precio en Double");

        //Convertir de String a Double, problema, hay letras adelante de los numeros, Bs: 7980
        //Double precioConvertido = Double.parseDouble(precio2.replaceAll(",", "."));
        //System.out.println("Precio2: " + precioConvertido);

        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        ProductDto productDto = new ProductDto();

        productDto.setProductId(9);
        productDto.setName(name);
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(3);

        productDtos.add( productDto);
        System.out.println("productDtos 9: " + productDtos);
        return productDtos;
    }
    */

    //LISTADO DE TODOS LOS PRODUCTOS
    public List<ProductDto> productListAll(String url, String url2, String url3, String url4, String url5, String url6, String url7, String url8, String url9) throws IOException{
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); //se crea productDtos para tener el listado de products
        List<ProductDto> productDtos2 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos3 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos4 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos5 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos6 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos7 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos8 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos9 = new ArrayList<ProductDto>();

        //producto 1
        productDtos = extractProductList(url);
        //System.out.println("producto1: " + productDtos);

        //producto 2
        productDtos2 = extractProductList2(url2);
        //System.out.println("producto2 url2: " + productDtos2);

        //producto 3
        productDtos3 = extractProductList3(url3);
        //System.out.println("producto3: " + productDtos3);

        //producto 4
        productDtos4 = extractProductList4(url4);
        //System.out.println("producto4: " + productDtos4);

        //producto 5
        productDtos5 = extractProductList5(url5);
        //System.out.println("producto5: " + productDtos5);

        //producto 6
        productDtos6 = extractProductList6(url6);
        //System.out.println("producto6: " + productDtos6);

        //producto 7
        productDtos7 = extractProductList7(url7);
        //System.out.println("producto7: " + productDtos7);

        //producto 8
        productDtos8 = extractProductList8(url8);
        //System.out.println("producto8: " + productDtos8);

        //producto 9
        productDtos9 = extractProductList9(url9);
        System.out.println("producto9: " + productDtos9);

        //System.out.println("Tamaño: " + productDtos.size());
        List<ProductDto> productAll = new ArrayList<ProductDto>(); // Lista para guardar todos los productos
        productAll.addAll(productDtos);
        productAll.addAll(productDtos2);
        productAll.addAll(productDtos3);
        productAll.addAll(productDtos4);
        productAll.addAll(productDtos5);
        productAll.addAll(productDtos6);
        productAll.addAll(productDtos7);
        productAll.addAll(productDtos8);
        productAll.addAll(productDtos9);

        //System.out.println("product all: " + productAll);
        return  productAll;
    }

    //PRODUCTO POR ID
    public ProductDto findProductById(Integer productId, String url, String url2, String url3, String url4, String url5, String url6, String url7, String url8, String url9)throws IOException {
        List<ProductDto> productDtosFor = productListAll(url, url2, url3, url4, url5, url6, url7, url8, url9); //se crea para el for y para llamar al metodo productListAll
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
//FIN




//LISTADO de TIENDAS, extraccion de tiendas  sin  BD
    //Tienda 1: Dismac
    public List<ShopDto> extractShopList(String url) throws IOException {
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

        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ShopDto> shopDtos = new ArrayList<ShopDto>(); // se crea ShopDto para tener el listado de tiendas
        ShopDto shopDto = new ShopDto();

        shopDto.setShopId(1);
        shopDto.setName("Dismac");
        shopDto.setDescription(description);
        shopDto.setLocation(location);
        shopDto.setImg(img);

        shopDtos.add( shopDto);
        System.out.println("shopDtos 1: " + shopDtos);
        return shopDtos;
    }

    //Tienda 2: CompuCenter
    public List<ShopDto> extractShopList2(String url) throws IOException {
        System.out.println("Extrayendo inf. de Tienda 2, página CompuCenter  " + url );
        Document doc = Jsoup.connect(url).timeout(8000).get();      //para buscar img y ubicacion, description
        Elements descriptionPage = doc.select(" div.min-h-screen"); //
        Elements locationPage = doc.select(" footer#contacts ");
        Elements imgPage = doc.select(" nav.relative.container");
        //Salto de linea descriptionPage
        descriptionPage.select("br").append("\\nl"); //append salto de linea despues de un elemento
        descriptionPage.select("div").append("\\nl");
        descriptionPage.select("p").prepend("\\nl"); //append salto de linea Antes de un elemento
        //
        //Salto de linea locationPage
        locationPage.select("div.my-2 span:matches(La Paz|Cochabamba|Santa Cruz) ").prepend("\\nl "); //append salto de linea
        locationPage.select("div.my-1 span").prepend("\\nl "); //append salto de linea
        locationPage.select("div.my-1 span").append("\\nl "); //append salto de linea
        //

        String description="";
        String location ="";
        String img="";
        //Description
        for (Element e : descriptionPage.select("section"))
        {
            description = e.select("div.mb-10 " ).text().replaceAll("\\\\nl", "\n");
            System.out.println("Descripcion: \n" + description);
        }
        //location
        for (Element e : locationPage.select(" div.m-auto.text-gray-800 "))
        {
            location = e.select("div span" ).text().replaceAll("\\\\nl", "\n");
            System.out.println("Location: " + location );

        }

        //img
        for (Element e : imgPage.select("div.mb-0"))
        {
            img = e.select(" a img ").attr("src"); //Obtener src, img del PC
            System.out.println("Logo de la tienda 2: " + img);
        }
        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ShopDto> shopDtos = new ArrayList<ShopDto>(); // se crea ShopDto para tener el listado de tiendas
        ShopDto shopDto = new ShopDto();

        shopDto.setShopId(2);
        shopDto.setName("CompuCenter");
        shopDto.setDescription(description);
        shopDto.setLocation(location);
        shopDto.setImg("https://compucenter.store/_nuxt/img/logo_cc_lg.c362771.svg");

        shopDtos.add( shopDto);
        System.out.println("shopDtos 2: " + shopDtos);
        return shopDtos;
    }

    //Tienda 3: Multilaptops
    public List<ShopDto> extractShopList3(String url) throws IOException {
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
        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ShopDto> shopDtos = new ArrayList<ShopDto>(); // se crea ShopDto para tener el listado de tiendas
        ShopDto shopDto = new ShopDto();

        shopDto.setShopId(3);
        shopDto.setName("Multilaptops");
        shopDto.setDescription(description);
        shopDto.setLocation(location);
        shopDto.setImg(img);

        shopDtos.add( shopDto);
        System.out.println("shopDtos 3: " + shopDtos);
        return shopDtos;

    }


    //LISTADO DE TODAS LAS TIENDAS
    public List<ShopDto> shopListAll(String url, String url2, String url3) throws IOException{
        List<ShopDto> shopDtos = new ArrayList<ShopDto>(); //se crea productDtos para tener el listado de products
        List<ShopDto> shopDtos2 = new ArrayList<ShopDto>();
        List<ShopDto> shopDtos3 = new ArrayList<ShopDto>();

        //Tienda 1
        shopDtos = extractShopList(url);
        //System.out.println(" Tienda 1: " + shopDtos);

        //Tienda 2
        shopDtos2 = extractShopList2(url2);
        //System.out.println(" Tienda 2: " + shopDtos2);

        //Tienda 3
        shopDtos3 = extractShopList3(url3);
        //System.out.println(" Tienda 3: " + shopDtos3);

        //System.out.println("Tamaño: " + productDtos.size());
        List<ShopDto> shopAll = new ArrayList<ShopDto>(); // Lista para guardar todos los productos
        shopAll.addAll(shopDtos);
        shopAll.addAll(shopDtos2);
        shopAll.addAll(shopDtos3);
        //System.out.println("Shops all: " + shopAll);
        return  shopAll;
    }

    //TIENDA POR ID
    public ShopDto findShopById(Integer shopId, String url, String url2, String url3 ) throws IOException {
        List<ShopDto> shopDtosFor = shopListAll(url, url2, url3); //se crea para el for y para llamar al metodo shopListAll
        ShopDto shopAux = new ShopDto();                          // para el return, para guardar el listado final

        for(int i=0; i < shopDtosFor.size(); i++) {
            ShopDto shop = shopDtosFor.get(i);      // shop para guardar el recorrido del for
            ShopDto shopDto = new ShopDto();

            //System.out.println("tamaño de la lista: " + shopDtosFor.size());
            //System.out.println("Posicion I: " + i + ", shop obtenidos: "+ shop );
            //System.out.println("shopId de la tienda desde el for: "+ shop.getShopId());     //condicional IF(), shop.getShopId()
            //System.out.println("shopId de la tienda, declarado Integer: "+ shopId);       //condicional IF(), shopId
            if(shop.getShopId() == shopId){                 //listado: shop.getShopId() ==  parametro introducido: Integer shopId
                System.out.println("shopId de la tienda desde el for: "+ shop.getShopId() + ", es igual a parametro shopId? "+ shopId);
                shopDto.setShopId(shop.getShopId());
                shopDto.setName(shop.getName());
                shopDto.setDescription(shop.getDescription());
                shopDto.setLocation(shop.getLocation());
                shopDto.setImg(shop.getImg());

                shopAux = shopDto;
            }//fin IF
        }//fin FOR
            //System.out.println("findShopById: "+ shopAux);
        return  shopAux;
    }
//FIN











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
