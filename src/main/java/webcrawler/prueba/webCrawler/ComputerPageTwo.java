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
import java.util.ArrayList;
import java.util.List;

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

//CATEGORIAS 1, 2, 3 de las tiendas
//TIENDAS 4, 5, 6,

//LISTADO de PRODUCTOS, extraccion de Producto  sin  BD
    //Categoria Study, PC.com
    //PRODUCTO 1
    public List<ProductDto> extractStudyList(String url) throws IOException {
        System.out.println("Categoria estudio, Página PC.com url" + url);
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.container");
        Elements imgProduct = doc1.select("div.container");  //extraccion de imagen
        Elements productDescription = doc1.select("div.container"); //extracion de detalle del PC
        Elements price = doc1.select(" div.container");
        //Salto de linea descriptionPage
        productDescription.select("strong").append("\\nl"); //append salto de linea despues de un elemento
        productDescription.select("li").append("\\nl");
        //productDescription.select("li").prepend("\\nl"); //append salto de linea Antes de un elemento
        //
        //extracion del PC
        String name = "";
        String name2 = "";
        String img = "";
        String description = "";

        //productName
        for (Element e : productName.select("div.col-md-6.text-white.py-5")) {
            name = e.select(" h2:matches(HP)  ").text(); //Obtener tipo de PC
            //System.out.println("Nombre del PC: " + name);
        }
        //Name 2
        for (Element e : productName.select("div.col-md-6.text-white.py-5")) {
            name2 = e.select(" h1  ").text(); //Obtener tipo de PC
            //System.out.println("Nombre del PC: " + name);
        }
        //imgProduct
        for (Element e : imgProduct.select("div.col-md-6.py-5") ) {
            img = e.select(" div.col-md-6.py-5 > img  ").attr("src"); //Obtener src, img del PC
            //System.out.println("imagen : " + img);
        }
        String img2 = img.replace("", "https://www.pc.com.bo/assets/img/productos/hp/hp_15-ef0025wm.png");

        //pruductDescription
        for (Element e : productDescription.select("div.col-md-6.text-white.py-5")) {
            description = e.select(" li ").text().replaceAll("\\\\nl", "\n"); //Obtener marca del PC
            //System.out.println("Descripción: \n" + description);
        }

        String precio = "";
        for (Element e : price.select("div.precio")) {
            precio = e.select(" h2 ").text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio );

        }
        //Convertir de String a Double
        //Double precioConvertido = Double.parseDouble(precio.replace(",", "."));
        //System.out.println("Precio: " + precioConvertido + ".00");

        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        ProductDto productDto = new ProductDto();

        productDto.setProductId(10);
        productDto.setName(name);
        productDto.setName2(name2);
        productDto.setShopName("PC.COM");
        productDto.setDescription(description);
        productDto.setImg(img2);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(4);
        productDto.setCategoryId(1);

        productDtos.add( productDto);
        System.out.println("productDtos, categoria estudio: " + productDtos);
        return  productDtos;
    }

    //PRODUCTO 2
    public List<ProductDto> extractStudyList2(String url) throws IOException {
        //System.out.println("Computadoras, Página PC.com url" + url);
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.container");
        Elements imgProduct = doc1.select("div.container");  //extraccion de imagen
        Elements productDescription = doc1.select("div.container"); //extracion de detalle del PC
        Elements price = doc1.select(" div.container");
        //Salto de linea descriptionPage
        productDescription.select("strong").append("\\nl"); //append salto de linea despues de un elemento
        productDescription.select("li").append("\\nl");
        //productDescription.select("li").prepend("\\nl"); //append salto de linea Antes de un elemento
        //
        //extracion del PC
        String name = "";
        String name2 = "";
        String img = "";
        String description = "";

        //productName
        for (Element e : productName.select("div.col-md-6.text-white.py-5")) {
            name = e.select(" h2:matches(HP)  ").text(); //Obtener tipo de PC
            //System.out.println("Nombre del PC: " + name);
        }
        //Name 2
        for (Element e : productName.select("div.col-md-6.text-white.py-5")) {
            name2 = e.select(" h1  ").text(); //Obtener tipo de PC
            //System.out.println("Nombre del PC: " + name);
        }
        //imgProduct
        for (Element e : imgProduct.select("div.col-md-6.py-5") ) {
            img = e.select(" div.col-md-6.py-5 > img  ").attr("src"); //Obtener src, img del PC
            //System.out.println("imagen : " + img);
        }
        String img2 = img.replace("", "https://www.pc.com.bo/assets/img/productos/hp/hp_15-dy1751ms.png");

        //pruductDescription
        for (Element e : productDescription.select("div.col-md-6.text-white.py-5")) {
            description = e.select(" li ").text().replaceAll("\\\\nl", "\n"); //Obtener marca del PC
            //System.out.println("Descripción: \n" + description);
        }

        String precio = "";
        for (Element e : price.select("div.precio")) {
            precio = e.select(" h2 ").text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio );

        }
        //Convertir de String a Double
        //Double precioConvertido = Double.parseDouble(precio.replace(",", "."));
        //System.out.println("Precio: " + precioConvertido + ".00");

        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        ProductDto productDto = new ProductDto();

        productDto.setProductId(11);
        productDto.setName(name);
        productDto.setName2(name2);
        productDto.setShopName("PC.COM");
        productDto.setDescription(description);
        productDto.setImg(img2);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(4);
        productDto.setCategoryId(1);

        productDtos.add( productDto);
        System.out.println("productDtos, categoria estudio : " + productDtos);
        return  productDtos;
    }

    //PRODUCTO 3
    public List<ProductDto> extractStudyList3(String url) throws IOException {
        //System.out.println("Computadoras, Página PC.com url" + url);
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.container");
        Elements imgProduct = doc1.select("div.container");  //extraccion de imagen
        Elements productDescription = doc1.select("div.container"); //extracion de detalle del PC
        Elements price = doc1.select(" div.container");
        //Salto de linea descriptionPage
        productDescription.select("strong").append("\\nl"); //append salto de linea despues de un elemento
        productDescription.select("li").append("\\nl");
        //productDescription.select("li").prepend("\\nl"); //append salto de linea Antes de un elemento
        //
        //extracion del PC
        String name = "";
        String name2 = "";
        String img = "";
        String description = "";

        //productName
        for (Element e : productName.select("div.col-md-6.text-white.py-5")) {
            name = e.select(" h2:matches(Laptop HP)  ").text(); //Obtener tipo de PC
            //System.out.println("Nombre del PC: " + name);
        }
        //Name 2
        for (Element e : productName.select("div.col-md-6.text-white.py-5")) {
            name2 = e.select(" h1  ").text(); //Obtener tipo de PC
            //System.out.println("Nombre del PC: " + name);
        }
        //imgProduct
        for (Element e : imgProduct.select("div.col-md-6.py-5") ) {
            img = e.select(" div.col-md-6.py-5 > img  ").attr("src"); //Obtener src, img del PC
            //System.out.println("imagen : " + img);
        }
        String img2 = img.replace("", "https://www.pc.com.bo/assets/img/productos/hp/hp_14-dk1025wm.png");

        //pruductDescription
        for (Element e : productDescription.select("div.col-md-6.text-white.py-5")) {
            description = e.select(" li ").text().replaceAll("\\\\nl", "\n"); //Obtener marca del PC
            //System.out.println("Descripción: \n" + description);
        }

        String precio = "";
        for (Element e : price.select("div.precio")) {
            precio = e.select(" h2 ").text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio );

        }
        //Convertir de String a Double
        //Double precioConvertido = Double.parseDouble(precio.replace(",", "."));
        //System.out.println("Precio: " + precioConvertido + ".00");

        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        ProductDto productDto = new ProductDto();

        productDto.setProductId(12);
        productDto.setName(name);
        productDto.setName2(name2);
        productDto.setShopName("PC.COM");
        productDto.setDescription(description);
        productDto.setImg(img2);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(4);
        productDto.setCategoryId(1);

        productDtos.add( productDto);
        System.out.println("productDtos, categoria estudio : " + productDtos);
        return  productDtos;
    }

    //PRODUCTO 4
    public List<ProductDto> extractStudyList4(String url) throws IOException {
        //System.out.println("Computadoras, Página PC.com url" + url);
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.container");
        Elements imgProduct = doc1.select("div.container");  //extraccion de imagen
        Elements productDescription = doc1.select("div.container"); //extracion de detalle del PC
        Elements price = doc1.select(" div.container");
        //Salto de linea descriptionPage
        productDescription.select("strong").append("\\nl"); //append salto de linea despues de un elemento
        productDescription.select("li").append("\\nl");
        //productDescription.select("li").prepend("\\nl"); //append salto de linea Antes de un elemento
        //
        //extracion del PC
        String name = "";
        String name2 = "";
        String img = "";
        String description = "";

        //productName
        for (Element e : productName.select("div.col-md-6.text-white.py-5")) {
            name = e.select(" h2:matches(Envy )  ").text(); //Obtener tipo de PC
            //System.out.println("Nombre del PC: " + name);
        }
        //Name 2
        for (Element e : productName.select("div.col-md-6.text-white.py-5")) {
            name2 = e.select(" h1:matches(Notebook HP)  ").text(); //Obtener tipo de PC
            //System.out.println("Nombre del PC: " + name);
        }
        //imgProduct
        for (Element e : imgProduct.select("div.col-md-6.py-5") ) {
            img = e.select(" div.col-md-6.py-5 > img  ").attr("src"); //Obtener src, img del PC
            //System.out.println("imagen : " + img);
        }
        String img2 = img.replace("", "https://www.pc.com.bo/assets/img/productos/hp/hp_13-ag0003la.png");

        //pruductDescription
        for (Element e : productDescription.select("div.col-md-6.text-white.py-5")) {
            description = e.select(" li ").text().replaceAll("\\\\nl", "\n"); //Obtener marca del PC
            //System.out.println("Descripción: \n" + description);
        }

        String precio = "";
        for (Element e : price.select("div.precio")) {
            precio = e.select(" h1 ").text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio );

        }
        //Convertir de String a Double
        //Double precioConvertido = Double.parseDouble(precio.replace(",", "."));
        //System.out.println("Precio: " + precioConvertido + ".00");

        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        ProductDto productDto = new ProductDto();

        productDto.setProductId(13);
        productDto.setName(name);
        productDto.setName2(name2);
        productDto.setShopName("PC.COM");
        productDto.setDescription(description);
        productDto.setImg(img2);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(4);
        productDto.setCategoryId(1);

        productDtos.add( productDto);
        System.out.println("productDtos, categoria estudio : " + productDtos);
        return  productDtos;
    }


    //Categoria Gamer, CompuCenter
    //Producto 1,
    public List<ProductDto> extractGamerList1(String url) throws IOException {
        System.out.println("Categorias Gamers, Página CompuCenter url" );
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.w-full");
        Elements imgProduct = doc1.select("div.w-full");  //extraccion de imagen
        Elements productDescription = doc1.select("div.w-full.pt-4"); //extracion de detalle del PC
        Elements price = doc1.select(" div.w-full");

        //extracion del PC
        String name="";
        String name2="";    //opcianal
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.mb-10"))
        {
            name = e.select("h1" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre del PC: " + name);

        //productName2 //opcional
        for (Element e : productName.select("div.flex.text-black.cursor-pointer.pb-1"))
        {
            name2 = e.select("span" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre2 opcional del PC: " + name2);

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
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        //String precio2 = precio.replace("Bs" , "");
        //System.out.println("Precio 2: " + precio2 + " , se quito exitosamente el Bs para convertir el precio en Double" );

        //se quito la coma
        //String precio3 = precio2.replace(",", "");
        //System.out.println("Precio 3: " + precio3 + " ,  se quito la coma ");

        //Double precioConvertido = Double.parseDouble(precio3);
        //System.out.println("precio Convertido a Double " + precioConvertido );

        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        ProductDto productDto = new ProductDto();

        productDto.setProductId(14);
        productDto.setName(name);
        productDto.setName2(name2); //opcional
        productDto.setShopName("CompuCenter");
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(2);
        productDto.setCategoryId(2);  //problema,

        productDtos.add( productDto);
        System.out.println("productDtos, categoria Gamer: " + productDtos);
        return  productDtos;
    }

    //Producto 2
    public List<ProductDto> extractGamerList2(String url) throws IOException {
        //System.out.println("Computadoras, Página CompuCenter url" + url + "...");
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.w-full");
        Elements imgProduct = doc1.select("div.w-full");  //extraccion de imagen
        Elements productDescription = doc1.select("div.w-full.pt-4"); //extracion de detalle del PC
        Elements price = doc1.select(" div.w-full");

        //extracion del PC
        String name="";
        String name2="";
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.mb-10"))
        {
            name = e.select("h1" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre del PC: " + name);

        //productName2 //opcional
        for (Element e : productName.select("div.flex.text-black.cursor-pointer.pb-1"))
        {
            name2 = e.select("span" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre2 opcional del PC: " + name2);

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
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        //String precio2 = precio.replace("Bs" , "");
        //System.out.println("Precio 2: " + precio2 + " , se quito exitosamente el Bs para convertir el precio en Double" );

        //se quito la coma
        //String precio3 = precio2.replace(",", "");
        //System.out.println("Precio 3: " + precio3 + " ,  se quito la coma ");

        //Double precioConvertido = Double.parseDouble(precio3);
        //System.out.println("precio Convertido a Double " + precioConvertido );

        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        ProductDto productDto = new ProductDto();

        productDto.setProductId(15);
        productDto.setName(name);
        productDto.setName2(name2);
        productDto.setShopName("CompuCenter");
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(2);
        productDto.setCategoryId(2);  //problema,

        productDtos.add( productDto);
        System.out.println("productDtos categoria Gamer: " + productDtos);
        return  productDtos;
    }

    //Producto 3
    public List<ProductDto> extractGamerList3(String url) throws IOException {
        //System.out.println("Computadoras, Página CompuCenter url" + url + "...");
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.w-full");
        Elements imgProduct = doc1.select("div.w-full");  //extraccion de imagen
        Elements productDescription = doc1.select("div.w-full.pt-4"); //extracion de detalle del PC
        Elements price = doc1.select(" div.w-full");

        //extracion del PC
        String name="";
        String name2="";
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.mb-10"))
        {
            name = e.select("h1" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre del PC: " + name);

        //productName2 //opcional
        for (Element e : productName.select("div.flex.text-black.cursor-pointer.pb-1"))
        {
            name2 = e.select("span" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre2 opcional del PC: " + name2);

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
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        //String precio2 = precio.replace("Bs" , "");
        //System.out.println("Precio 2: " + precio2 + " , se quito exitosamente el Bs para convertir el precio en Double" );

        //se quito la coma
        //String precio3 = precio2.replace(",", "");
        //System.out.println("Precio 3: " + precio3 + " ,  se quito la coma ");

        //Double precioConvertido = Double.parseDouble(precio3);
        //System.out.println("precio Convertido a Double " + precioConvertido );

        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        ProductDto productDto = new ProductDto();

        productDto.setProductId(16);
        productDto.setName(name);
        productDto.setName2(name2);
        productDto.setShopName("CompuCenter");
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(2);
        productDto.setCategoryId(2);  //problema,

        productDtos.add( productDto);
        System.out.println("productDtos categoria Gamer: " + productDtos);
        return  productDtos;
    }

    //Producto 4
    public List<ProductDto> extractGamerList4(String url) throws IOException {
        //System.out.println("Computadoras, Página CompuCenter url" + url + "...");
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.w-full");
        Elements imgProduct = doc1.select("div.w-full");  //extraccion de imagen
        Elements productDescription = doc1.select("div.w-full.pt-4"); //extracion de detalle del PC
        Elements price = doc1.select(" div.w-full");

        //extracion del PC
        String name="";
        String name2="";
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.mb-10"))
        {
            name = e.select("h1" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre del PC: " + name);

        //productName2 //opcional
        for (Element e : productName.select("div.flex.text-black.cursor-pointer.pb-1"))
        {
            name2 = e.select("span" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre2 opcional del PC: " + name2);

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
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        //String precio2 = precio.replace("Bs" , "");
        //System.out.println("Precio 2: " + precio2 + " , se quito exitosamente el Bs para convertir el precio en Double" );

        //se quito la coma
        //String precio3 = precio2.replace(",", "");
        //System.out.println("Precio 3: " + precio3 + " ,  se quito la coma ");

        //Double precioConvertido = Double.parseDouble(precio3);
        //System.out.println("precio Convertido a Double " + precioConvertido );

        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        ProductDto productDto = new ProductDto();

        productDto.setProductId(17);
        productDto.setName(name);
        productDto.setName2(name2);
        productDto.setShopName("CompuCenter");
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(2);
        productDto.setCategoryId(2);  //problema,

        productDtos.add( productDto);
        System.out.println("productDtos categoria Gamer: " + productDtos);
        return  productDtos;
    }

    //Producto 5
    public List<ProductDto> extractGamerList5(String url) throws IOException {
        //System.out.println("Computadoras, Página CompuCenter url" + url + "...");
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.w-full");
        Elements imgProduct = doc1.select("div.w-full");  //extraccion de imagen
        Elements productDescription = doc1.select("div.w-full.pt-4"); //extracion de detalle del PC
        Elements price = doc1.select(" div.w-full");

        //extracion del PC
        String name="";
        String name2="";
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.mb-10"))
        {
            name = e.select("h1" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre del PC: " + name);

        //productName2 //opcional
        for (Element e : productName.select("div.flex.text-black.cursor-pointer.pb-1"))
        {
            name2 = e.select("span" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre2 opcional del PC: " + name2);

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
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        //String precio2 = precio.replace("Bs" , "");
        //System.out.println("Precio 2: " + precio2 + " , se quito exitosamente el Bs para convertir el precio en Double" );

        //se quito la coma
        //String precio3 = precio2.replace(",", "");
        //System.out.println("Precio 3: " + precio3 + " ,  se quito la coma ");

        //Double precioConvertido = Double.parseDouble(precio3);
        //System.out.println("precio Convertido a Double " + precioConvertido );

        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        ProductDto productDto = new ProductDto();

        productDto.setProductId(18);
        productDto.setName(name);
        productDto.setName2(name2);
        productDto.setShopName("CompuCenter");
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(2);
        productDto.setCategoryId(2);  //problema,

        productDtos.add( productDto);
        System.out.println("productDtos categoria Gamer: " + productDtos);
        return  productDtos;
    }
    //FIN CATEGORIA GAMER



    //Categoria Work, Compucenter y Pc.com
    //Producto 1,
    public List<ProductDto> extractWorkList1(String url) throws IOException {
        System.out.println("Categorias Trabajo, Página CompuCenter url" + url );
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.w-full");
        Elements imgProduct = doc1.select("div.w-full");  //extraccion de imagen
        Elements productDescription = doc1.select("div.w-full.pt-4"); //extracion de detalle del PC
        Elements price = doc1.select(" div.w-full");

        //extracion del PC
        String name="";
        String name2="";    //opcianal
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.mb-10"))
        {
            name = e.select("h1" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre del PC: " + name);

        //productName2 //opcional
        for (Element e : productName.select("div.flex.text-black.cursor-pointer.pb-1"))
        {
            name2 = e.select("span" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre2 opcional del PC: " + name2);

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
            System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double" );

        }
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        //String precio2 = precio.replace("Bs" , "");
        //System.out.println("Precio 2: " + precio2 + " , se quito exitosamente el Bs para convertir el precio en Double" );

        //se quito la coma
        //String precio3 = precio2.replace(",", "");
        //System.out.println("Precio 3: " + precio3 + " ,  se quito la coma ");

        //Double precioConvertido = Double.parseDouble(precio3);
        //System.out.println("precio Convertido a Double " + precioConvertido );

        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        ProductDto productDto = new ProductDto();

        productDto.setProductId(19);
        productDto.setName(name);
        productDto.setName2(name2); //opcional
        productDto.setShopName("CompuCenter");
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(2);
        productDto.setCategoryId(3);  //problema,

        productDtos.add( productDto);
        System.out.println("productDtos categoria Trabajo: " + productDtos);
        return  productDtos;
    }

    //Producto 2,
    public List<ProductDto> extractWorkList2(String url) throws IOException {
        //System.out.println("Categorias Gamers, Página CompuCenter url" + url );
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.w-full");
        Elements imgProduct = doc1.select("div.w-full");  //extraccion de imagen
        Elements productDescription = doc1.select("div.w-full.pt-4"); //extracion de detalle del PC
        Elements price = doc1.select(" div.w-full");

        //extracion del PC
        String name="";
        String name2="";    //opcianal
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("div.mb-10"))
        {
            name = e.select("h1" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre del PC: " + name);

        //productName2 //opcional
        for (Element e : productName.select("div.flex.text-black.cursor-pointer.pb-1"))
        {
            name2 = e.select("span" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre2 opcional del PC: " + name2);

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
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        //String precio2 = precio.replace("Bs" , "");
        //System.out.println("Precio 2: " + precio2 + " , se quito exitosamente el Bs para convertir el precio en Double" );

        //se quito la coma
        //String precio3 = precio2.replace(",", "");
        //System.out.println("Precio 3: " + precio3 + " ,  se quito la coma ");

        //Double precioConvertido = Double.parseDouble(precio3);
        //System.out.println("precio Convertido a Double " + precioConvertido );

        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        ProductDto productDto = new ProductDto();

        productDto.setProductId(20);
        productDto.setName(name);
        productDto.setName2(name2); //opcional
        productDto.setShopName("CompuCenter");
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(2);
        productDto.setCategoryId(3);  //problema,

        productDtos.add( productDto);
        System.out.println("productDtos categoria Trabajo: " + productDtos);
        return  productDtos;
    }

    //Producto 3,
    public List<ProductDto> extractWorkList3(String url) throws IOException {
        //System.out.println("Categorias Gamers, Página CompuCenter url" + url );
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.w-full");
        Elements imgProduct = doc1.select("div.w-full");  //extraccion de imagen
        Elements productDescription = doc1.select("div.w-full"); //extracion de detalle del PC
        Elements price = doc1.select(" div.w-full");

        //extracion del PC
        String name="";
        String name2="";    //opcianal
        String img="";
        String description="";

        //productName
        for (Element e : productName.select("a"))
        {
            name = e.select(" h1.font-semibold.text-lg " ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre del PC: " + name);

        //productName2 //opcional
        for (Element e : productName.select(" div.flex.flex-col.mb-2 "))
        {
            name2 = e.select(" strong " ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre2 opcional del PC: " + name2);

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
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        //String precio2 = precio.replace("Bs" , "");
        //System.out.println("Precio 2: " + precio2 + " , se quito exitosamente el Bs para convertir el precio en Double" );

        //se quito la coma
        //String precio3 = precio2.replace(",", "");
        //System.out.println("Precio 3: " + precio3 + " ,  se quito la coma ");

        //Double precioConvertido = Double.parseDouble(precio3);
        //System.out.println("precio Convertido a Double " + precioConvertido );

        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        ProductDto productDto = new ProductDto();

        productDto.setProductId(21);
        productDto.setName(name);
        productDto.setName2(name2); //opcional
        productDto.setShopName("CompuCenter");
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(2);
        productDto.setCategoryId(3);  //problema,

        productDtos.add( productDto);
        System.out.println("productDtos categoria Trabajo: " + productDtos);
        return  productDtos;
    }

    //Producto 4, Pc.com
    public List<ProductDto> extractWorkList4(String url) throws IOException {
        //System.out.println("Computadoras, Página PC.com url" + url);
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.container");
        Elements imgProduct = doc1.select("div.container");  //extraccion de imagen
        Elements productDescription = doc1.select("div.container"); //extracion de detalle del PC
        Elements price = doc1.select(" div.container");
        //Salto de linea descriptionPage
        productDescription.select("strong").append("\\nl"); //append salto de linea despues de un elemento
        productDescription.select("li").append("\\nl");
        //productDescription.select("li").prepend("\\nl"); //append salto de linea Antes de un elemento
        //
        //extracion del PC
        String name = "";
        String name2 = "";
        String img = "";
        String description = "";

        //productName
        for (Element e : productName.select("div.col-md-6.text-white.py-5")) {
            name = e.select(" h2:matches(Yoga )  ").text(); //Obtener tipo de PC
            //System.out.println("Nombre del PC: " + name);
        }
        //Name 2
        for (Element e : productName.select("div.col-md-6.text-white.py-5")) {
            name2 = e.select(" h1  ").text(); //Obtener tipo de PC
            //System.out.println("Nombre del PC: " + name);
        }
        //imgProduct
        for (Element e : imgProduct.select("div.col-md-6.py-5") ) {
            img = e.select(" div.col-md-6.py-5 > img  ").attr("src"); //Obtener src, img del PC
            //System.out.println("imagen : " + img);
        }
        String img2 = img.replace("", "https://www.pc.com.bo/assets/img/productos/lenovo/len_15-c740.png");

        //pruductDescription
        for (Element e : productDescription.select("div.col-md-6.text-white.py-5")) {
            description = e.select(" li ").text().replaceAll("\\\\nl", "\n"); //Obtener marca del PC
            //System.out.println("Descripción: \n" + description);
        }

        String precio = "";
        for (Element e : price.select("div.precio")) {
            precio = e.select(" h2 ").text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio );

        }
        //Convertir de String a Double
        //Double precioConvertido = Double.parseDouble(precio.replace(",", "."));
        //System.out.println("Precio: " + precioConvertido + ".00");

        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        ProductDto productDto = new ProductDto();

        productDto.setProductId(22);
        productDto.setName(name);
        productDto.setName2(name2);
        productDto.setShopName("PC.COM");
        productDto.setDescription(description);
        productDto.setImg(img2);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(4);
        productDto.setCategoryId(3);

        productDtos.add( productDto);
        System.out.println("productDtos categoria Trabajo: " + productDtos);
        return  productDtos;
    }

    //Producto 5, PC.com
    public List<ProductDto> extractWorkList5(String url) throws IOException {
        //System.out.println("Computadoras, Página PC.com url" + url);
        Document doc1 = Jsoup.connect(url).timeout(10000).get();
        Elements productName = doc1.select(" div.container");
        Elements imgProduct = doc1.select("div.container");  //extraccion de imagen
        Elements productDescription = doc1.select("div.container"); //extracion de detalle del PC
        Elements price = doc1.select(" div.container");
        //Salto de linea descriptionPage
        productDescription.select("strong").append("\\nl"); //append salto de linea despues de un elemento
        productDescription.select("li").append("\\nl");
        //productDescription.select("li").prepend("\\nl"); //append salto de linea Antes de un elemento
        //
        //extracion del PC
        String name = "";
        String name2 = "";
        String img = "";
        String description = "";

        //productName
        for (Element e : productName.select("div.col-md-6.text-white.py-5")) {
            name = e.select(" h2:matches(HP)  ").text(); //Obtener tipo de PC
            //System.out.println("Nombre del PC: " + name);
        }
        //Name 2
        for (Element e : productName.select("div.col-md-6.text-white.py-5")) {
            name2 = e.select(" h1  ").text(); //Obtener tipo de PC
            //System.out.println("Nombre del PC: " + name);
        }
        //imgProduct
        for (Element e : imgProduct.select("div.col-md-6.py-5") ) {
            img = e.select(" div.col-md-6.py-5 > img  ").attr("src"); //Obtener src, img del PC
            //System.out.println("imagen : " + img);
        }
        String img2 = img.replace("", "https://www.pc.com.bo/assets/img/productos/hp/hp_15-cs0073cl.png");

        //pruductDescription
        for (Element e : productDescription.select("div.col-md-6.text-white.py-5")) {
            description = e.select(" li ").text().replaceAll("\\\\nl", "\n"); //Obtener marca del PC
            //System.out.println("Descripción: \n" + description);
        }

        String precio = "";
        for (Element e : price.select("div.precio")) {
            precio = e.select(" h2 ").text(); //Obtener precio del PC
            //System.out.println("Precio: " + precio );

        }
        //Convertir de String a Double
        //Double precioConvertido = Double.parseDouble(precio.replace(",", "."));
        //System.out.println("Precio: " + precioConvertido + ".00");

        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        ProductDto productDto = new ProductDto();

        productDto.setProductId(23);
        productDto.setName(name);
        productDto.setName2(name2);
        productDto.setShopName("PC.COM");
        productDto.setDescription(description);
        productDto.setImg(img2);
        productDto.setPrice(precio);
        //llaves foraneas
        productDto.setShopId(4);
        productDto.setCategoryId(3);

        productDtos.add( productDto);
        System.out.println("productDtos categoria Trabajo: " + productDtos);
        return  productDtos;
    }





    //LISTADO DE TODOS LOS PRODUCTOS
    public List<ProductDto> productListAll(String urlS1,String urlS2,String urlS3,String urlS4,   String urlG1,String urlG2,String urlG3,String urlG4,String urlG5,  String urlW1,String urlW2,String urlW3,String urlW4,String urlW5) throws IOException{
        //categoria study
        List<ProductDto> productStudy1 = new ArrayList<ProductDto>(); //se crea productDtos para tener el listado de products
        List<ProductDto> productStudy2 = new ArrayList<ProductDto>();
        List<ProductDto> productStudy3 = new ArrayList<ProductDto>();
        List<ProductDto> productStudy4 = new ArrayList<ProductDto>();

        //categoria Gamer
        List<ProductDto> productGamer1 = new ArrayList<ProductDto>();
        List<ProductDto> productGamer2 = new ArrayList<ProductDto>();
        List<ProductDto> productGamer3 = new ArrayList<ProductDto>();
        List<ProductDto> productGamer4 = new ArrayList<ProductDto>();
        List<ProductDto> productGamer5 = new ArrayList<ProductDto>();
        //categoria Work
        List<ProductDto> productWork1 = new ArrayList<ProductDto>();
        List<ProductDto> productWork2 = new ArrayList<ProductDto>();
        List<ProductDto> productWork3 = new ArrayList<ProductDto>();
        List<ProductDto> productWork4 = new ArrayList<ProductDto>();
        List<ProductDto> productWork5 = new ArrayList<ProductDto>();

        //study 1
        productStudy1 = extractStudyList(urlS1);
        //System.out.println("producto1: " + productStudy);
        //study 2
        productStudy2 = extractStudyList2(urlS2);
        //System.out.println("producto2 : " + productStudy2);
        //study 3
        productStudy3 = extractStudyList3(urlS3);
        //System.out.println("producto3: " + productStudy3);
        //study 4
        productStudy4 = extractStudyList4(urlS4);
        //System.out.println("producto4: " + productStudy4);

        //Gamer 1
        productGamer1 = extractGamerList1(urlG1);
        //System.out.println("productoGamer1: " + productGamer1);
        //Gamer 2
        productGamer2 = extractGamerList2(urlG2);
        //System.out.println("productoGamer2: " + productGamer2);
        //Gamer 3
        productGamer3 = extractGamerList3(urlG3);
        //System.out.println("productoGamer3: " + productGamer3);
        //Gamer 4
        productGamer4 = extractGamerList4(urlG4);
        //System.out.println("productoGamer4: " + productGamer4);
        //Gamer 5
        productGamer5 = extractGamerList5(urlG5);
        //System.out.println("productoGamer5: " + productGamer5);


        //work 1
        productWork1 = extractWorkList1(urlW1);
        //System.out.println("productWork1: " + productWork1);
        //work 2
        productWork2 = extractWorkList2(urlW2);
        //System.out.println("productWork2: " + productWork2);
        //work 3
        productWork3 = extractWorkList3(urlW3);
        //System.out.println("productWork3: " + productWork3);
        //work 4
        productWork4 = extractWorkList4(urlW4);
        //System.out.println("productWork4: " + productWork4);
        //work 5
        productWork5 = extractWorkList5(urlW5);
        //System.out.println("productWork5: " + productWork5);

        //System.out.println("Tamaño: " + productDtos.size());
        List<ProductDto> productAll = new ArrayList<ProductDto>(); // Lista para guardar todos los productos
        productAll.addAll(productStudy1);
        productAll.addAll(productStudy2);
        productAll.addAll(productStudy3);
        productAll.addAll(productStudy4);

        productAll.addAll(productGamer1);
        productAll.addAll(productGamer2);
        productAll.addAll(productGamer3);
        productAll.addAll(productGamer4);
        productAll.addAll(productGamer5);

        productAll.addAll(productWork1);
        productAll.addAll(productWork2);
        productAll.addAll(productWork3);
        productAll.addAll(productWork4);
        productAll.addAll(productWork5);


        //System.out.println("product all: " + productAll);
        return  productAll;
    }

    //PRODUCTO POR ID
    public ProductDto findProductById(Integer productId, String urlS1,String urlS2,String urlS3,String urlS4,   String urlG1, String urlG2, String urlG3, String urlG4, String urlG5,   String urlW1,String urlW2,String urlW3,String urlW4,String urlW5)throws IOException {
        List<ProductDto> productDtosFor = productListAll(urlS1, urlS2, urlS3, urlS4, urlG1, urlG2, urlG3, urlG4, urlG5, urlW1, urlW2, urlW3, urlW4, urlW5); //se crea para el for y para llamar al metodo productListAll
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
                productDto.setShopId(product.getShopId());
                productDto.setCategoryId(product.getCategoryId());

                productAux = productDto;
            }//fin IF
        }//fin FOR
        //System.out.println("findShopById: "+ productAux);
        return  productAux;
    }


    //LISTADO ProductCategory  por categoriaId
    //listado de productos por categoria, JOIN SIN BASE DE DATOS
    public List<ProductDto> selectProductsByCategory(Integer categoryId, String urlS1, String urlS2, String urlS3, String urlS4, String urlG1, String urlG2, String urlG3, String urlG4, String urlG5 , String urlW1, String urlW2, String urlW3, String urlW4, String urlW5)throws IOException{
        //List<Product> products = productDao.getProductListByCategory(categoryId);      //productos, se crea un for para recorrer products
        List<ProductDto> productDtosFor = productListAll(urlS1,urlS2,urlS3,urlS4,  urlG1,urlG2,urlG3,urlG4,urlG5,  urlW1,urlW2,urlW3,urlW4,urlW5); //se crea para el for y para llamar al metodo productListAll y obtener sus datos extraidos
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
                productDto.setImg(product.getImg());
                productDto.setPrice(product.getPrice());
                productDto.setCategoryId(product.getCategoryId());

                //llaves foraneas
                //productDto.setBrandId(product.getBrandId());
                productDto.setShopId(product.getShopId());
                productDto.setShopName(product.getShopName());
                //productDto.setProductTypeId(product.getProductTypeId());

                productAux.add( productDto);
                //System.out.println("productAux: " +i +", "+ productAux);    //muestra los arreglos de los productos que cumplen con la condicion
            }//if
        }//for
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
    public List<ShopDto> shopListAll2(String url, String url2, String url3) throws IOException{
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

        List<ShopDto> shopAll = new ArrayList<ShopDto>(); // Lista para guardar las tiendas
        shopAll.addAll(shopDtos);
        shopAll.addAll(shopDtos2);
        shopAll.addAll(shopDtos3);
        //System.out.println("Shops all: " + shopAll);
        return  shopAll;
    }

    //TIENDA POR ID
    public ShopDto findShopById(Integer shopId, String url, String url2, String url3 ) throws IOException {
        List<ShopDto> shopDtosFor = shopListAll2(url, url2, url3); //se crea para el for y para llamar al metodo shopListAll
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
//Tienda 2, que contiene productos 1, 2 y 3
//Tienda CompuCenter, de Bolivia
    public ShopDto extractShop(String url, ShopDto shopDto, Transaction transaction) throws IOException {
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
        //ShopBl
        Shop shop = new Shop();
        shop.setName("CompuCenter");
        shop.setDescription(description);
        shop.setLocation(location);
        shop.setImg("https://compucenter.store/_nuxt/img/logo_cc_lg.c362771.svg");
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
        Elements product = doc.select(" div.w-full");

        String marca="";
        for (Element e : product.select("div.flex.text-black.cursor-pointer.pb-1"))
        {
            marca = e.select(" span.mr-2.bg-gray-100 ").text(); //Obtener marca del PC
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
        Elements producto = doc.select(" div.w-full");

        String tipoProducto="";
        for (Element e : producto.select("div.mb-10"))
        {
            tipoProducto = e.select("h1" ).text(); //Obtener tipo de PC
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
        System.out.println("Nombre del PC: " + name);

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
            System.out.println("Descripción: \n" + description);
        }

        String precio="";
        for (Element e : price.select("div.inline-block.align-bottom.mr-5"))
        {
            precio = e.select("span  " ).text(); //Obtener precio del PC
            System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double" );

        }
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        String precio2 = precio.replace("Bs" , "");
        System.out.println("Precio 2: " + precio2 + " , se quito exitosamente el Bs para convertir el precio en Double" );

        //se quito la coma
        String precio3 = precio2.replace(",", "");
        System.out.println("Precio 3: " + precio3 + " ,  se quito la coma ");

        Double precioConvertido = Double.parseDouble(precio3);
        System.out.println("precio Convertido a Double " + precioConvertido );

        //ProductBl, create
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setImg(img);
        product.setPrice( precio );
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
        Elements producto = doc.select(" div.w-full");

        String precio="";
        for (Element e : producto.select("div.inline-block.align-bottom.mr-5"))
        {
            precio = e.select("span  " ).text(); //Obtener precio del PC
            System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double" );

        }
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        String precio2 = precio.replace("Bs" , "");
        System.out.println("Precio 2: " + precio2 + " , se quito exitosamente el Bs para convertir el precio en Double" );

        //se quito la coma
        String precio3 = precio2.replace(",", "");
        System.out.println("Precio 3: " + precio3 + " ,  se quito la coma ");

        Double precioConvertido = Double.parseDouble(precio3);
        System.out.println("precio Convertido a Double " + precioConvertido );

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
//MARCA Hp, extraccion de marca y guardado en la BD
    public BrandDto extractBrand2(String url, BrandDto brandDto, Transaction transaction) throws IOException {
    System.out.println("Extrayendo Marca de la página " + url + "...");
    Document doc = Jsoup.connect(url).timeout(8000).get();
    Elements product = doc.select(" div.w-full");

    String marca="";
    for (Element e : product.select("div.flex.text-black.cursor-pointer.pb-1"))
    {
        marca = e.select(" span.mr-2.bg-gray-100 ").text(); //Obtener marca del PC
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
        Elements producto = doc.select(" div.w-full");

        String tipoProducto="";
        for (Element e : producto.select("div.mb-10"))
        {
            tipoProducto = e.select("h1" ).text(); //Obtener tipo de PC
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
        System.out.println("Nombre del PC: " + name);

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
            System.out.println("Descripción: \n" + description);
        }

        String precio="";
        for (Element e : price.select("div.inline-block.align-bottom.mr-5"))
        {
            precio = e.select("span  " ).text(); //Obtener precio del PC
            System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double" );

        }
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        String precio2 = precio.replace("Bs" , "");
        System.out.println("Precio 2: " + precio2 + " , se quito exitosamente el Bs para convertir el precio en Double" );

        //se quito la coma
        String precio3 = precio2.replace(",", "");
        System.out.println("Precio 3: " + precio3 + " ,  se quito la coma ");

        Double precioConvertido = Double.parseDouble(precio3);
        System.out.println("precio Convertido a Double " + precioConvertido );

        //ProductBl, create
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setImg(img);
        product.setPrice( precio );
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
        Elements producto = doc.select(" div.w-full");

        String precio="";
        for (Element e : producto.select("div.inline-block.align-bottom.mr-5"))
        {
            precio = e.select("span  " ).text(); //Obtener precio del PC
            System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double" );

        }
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        String precio2 = precio.replace("Bs" , "");
        System.out.println("Precio 2: " + precio2 + " , se quito exitosamente el Bs para convertir el precio en Double" );

        //se quito la coma
        String precio3 = precio2.replace(",", "");
        System.out.println("Precio 3: " + precio3 + " ,  se quito la coma ");

        Double precioConvertido = Double.parseDouble(precio3);
        System.out.println("precio Convertido a Double " + precioConvertido );

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
//MARCA Hp, extraccion de marca y guardado en la BD
    public BrandDto extractBrand3(String url, BrandDto brandDto, Transaction transaction) throws IOException {
    System.out.println("Extrayendo Marca de la página " + url + "...");
    Document doc = Jsoup.connect(url).timeout(8000).get();
    Elements product = doc.select(" div.w-full");

    String marca="";
    for (Element e : product.select("div.flex.text-black.cursor-pointer.pb-1"))
    {
        marca = e.select(" span.mr-2.bg-gray-100 ").text(); //Obtener marca del PC
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
        Elements producto = doc.select(" div.w-full");

        String tipoProducto="";
        for (Element e : producto.select("div.mb-10"))
        {
            tipoProducto = e.select("h1" ).text(); //Obtener tipo de PC
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
        System.out.println("Nombre del PC: " + name);

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
            System.out.println("Descripción: \n" + description);
        }

        String precio="";
        for (Element e : price.select("div.inline-block.align-bottom.mr-5"))
        {
            precio = e.select("span  " ).text(); //Obtener precio del PC
            System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double" );

        }
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        String precio2 = precio.replace("Bs" , "");
        System.out.println("Precio 2: " + precio2 + " , se quito exitosamente el Bs para convertir el precio en Double" );

        //se quito la coma
        String precio3 = precio2.replace(",", "");
        System.out.println("Precio 3: " + precio3 + " ,  se quito la coma ");

        Double precioConvertido = Double.parseDouble(precio3);
        System.out.println("precio Convertido a Double " + precioConvertido );

        //ProductBl, create
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setImg(img);
        product.setPrice( precio );
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
        Elements producto = doc.select(" div.w-full");

        String precio="";
        for (Element e : producto.select("div.inline-block.align-bottom.mr-5"))
        {
            precio = e.select("span  " ).text(); //Obtener precio del PC
            System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double" );

        }
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        String precio2 = precio.replace("Bs" , "");
        System.out.println("Precio 2: " + precio2 + " , se quito exitosamente el Bs para convertir el precio en Double" );

        //se quito la coma
        String precio3 = precio2.replace(",", "");
        System.out.println("Precio 3: " + precio3 + " ,  se quito la coma ");

        Double precioConvertido = Double.parseDouble(precio3);
        System.out.println("precio Convertido a Double " + precioConvertido );

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
//Tienda 2: Compucenter que contiene productos 1, 2 y 3
    public ShopDto updateShop(String url, ShopDto shopDto, Transaction transaction) throws IOException {
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
        //ShopBl Update
        Shop shop= new Shop();
        shop.setShopId(shopDto.getShopId());
        shop.setName("CompuCenter");
        shop.setDescription(description);
        shop.setLocation(location);
        shop.setImg("https://compucenter.store/_nuxt/img/logo_cc_lg.c362771.svg");
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
        System.out.println("Nombre del PC: " + name);

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
            System.out.println("Descripción: \n" + description);
        }

        String precio="";
        for (Element e : price.select("div.inline-block.align-bottom.mr-5"))
        {
            precio = e.select("span  " ).text(); //Obtener precio del PC
            System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double" );

        }
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        String precio2 = precio.replace("Bs" , "");
        System.out.println("Precio 2: " + precio2 + " , se quito exitosamente el Bs para convertir el precio en Double" );

        //se quito la coma
        String precio3 = precio2.replace(",", "");
        System.out.println("Precio 3: " + precio3 + " ,  se quito la coma ");

        Double precioConvertido = Double.parseDouble(precio3);
        System.out.println("precio Convertido a Double " + precioConvertido );

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
        System.out.println("Nombre del PC: " + name);

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
            System.out.println("Descripción: \n" + description);
        }

        String precio="";
        for (Element e : price.select("div.inline-block.align-bottom.mr-5"))
        {
            precio = e.select("span  " ).text(); //Obtener precio del PC
            System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double" );

        }
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        String precio2 = precio.replace("Bs" , "");
        System.out.println("Precio 2: " + precio2 + " , se quito exitosamente el Bs para convertir el precio en Double" );

        //se quito la coma
        String precio3 = precio2.replace(",", "");
        System.out.println("Precio 3: " + precio3 + " ,  se quito la coma ");

        Double precioConvertido = Double.parseDouble(precio3);
        System.out.println("precio Convertido a Double " + precioConvertido );

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
        System.out.println("Nombre del PC: " + name);

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
            System.out.println("Descripción: \n" + description);
        }

        String precio="";
        for (Element e : price.select("div.inline-block.align-bottom.mr-5"))
        {
            precio = e.select("span  " ).text(); //Obtener precio del PC
            System.out.println("Precio: " + precio + " , se quitara Bs: xq no permite  convertir el precio a Double" );

        }
        //En replace se quito exitosamente el Bs: para convertir el precio en Double
        String precio2 = precio.replace("Bs" , "");
        System.out.println("Precio 2: " + precio2 + " , se quito exitosamente el Bs para convertir el precio en Double" );

        //se quito la coma
        String precio3 = precio2.replace(",", "");
        System.out.println("Precio 3: " + precio3 + " ,  se quito la coma ");

        Double precioConvertido = Double.parseDouble(precio3);
        System.out.println("precio Convertido a Double " + precioConvertido );

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