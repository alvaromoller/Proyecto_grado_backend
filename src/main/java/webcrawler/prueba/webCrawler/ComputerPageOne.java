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
    private String url4;
    private String url5;
    private String url6;
    private String url7;
    private String url8;
    private String url9;
    private String urlCompuCenter;
    private SimpMessagingTemplate template;


    @Autowired
    public ComputerPageOne(DirectionIp directionIp , BrandDao brandDao, ProductDao productDao, ProductTypeDao productTypeDao, ShopDao shopDao, ProductDetailDao productDetailDao, TransactionDao transactionDao){
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
            String url4,
            String url5,
            String url6,
            String url7,
            String url8,
            String url9) {
        this.url4 = url4;
        this.url5 = url5;
        this.url6 = url6;
        this.url7 = url7;
        this.url8 = url8;
        this.url9 = url9;
        this.urlCompuCenter = urlCompuCenter;
        this.template = template;
    }
/**
    public ComputerPageOne(
            String url4,
            String url5,
            String url6,
            String url7,
            String url8,
            String url9,
            String urlCompuCenter) {
        this.url4 = url4;
        this.url5 = url5;
        this.url6 = url6;
        this.url7 = url7;
        this.url8 = url8;
        this.url9 = url9;
        this.urlCompuCenter = urlCompuCenter;
    }
*/

//TIENDAS 1, 2, 3

//LISTADO de PRODUCTOS, extraccion de Producto  sin  BD

    //tienda 2, CompuCenter
    //Producto 4
    public List<ProductDto> extractProductList4(String url) throws IOException {
        System.out.println("Computadoras, Página CompuCenter url" + url );
        Document doc1 = Jsoup.connect(url).get();    //.timeout(10000).get();
        Elements productName = doc1.select(" div.w-full");
        Elements imgProduct = doc1.select("div.w-full");  //extraccion de imagen
        Elements productDescription = doc1.select("div.w-full.pt-4"); //extracion de detalle del PC
        Elements price = doc1.select(" div.w-full");

        //extracion del PC
        String name="";
        String name2="";
        String img="";
        String description="";
        String brand="Asus";
        String ram = "Ram: 4 GB";
        String processor ="Intel";
        String storage ="SSD 128 GB";
        String tarjetaGrafica ="Tarjeta gráfica integrada";

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

        productDto.setProductId(4);
        productDto.setName(name);
        productDto.setName2(name2);
        productDto.setShopName("CompuCenter");
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        productDto.setBrand(brand);
        productDto.setRam(ram);
        productDto.setProcessor(processor);
        productDto.setStorage(storage);
        productDto.setTarjetaGrafica(tarjetaGrafica);
        //llaves foraneas
        productDto.setShopId(2);

        productDtos.add( productDto);
        System.out.println("productDtos 4: " + productDtos);
        return  productDtos;
    }

    //Producto 5
    //List<ProductDto>
    public List<ProductDto> extractProductList5(String url) throws IOException {
        //System.out.println("Computadoras, Página CompuCenter url" + url );
        Document doc1 = Jsoup.connect(url).get();    //.timeout(10000).get();
        Elements productName = doc1.select(" div.w-full");
        Elements imgProduct = doc1.select("div.w-full");  //extraccion de imagen
        Elements productDescription = doc1.select("div.w-full.pt-4"); //extracion de detalle del PC
        Elements price = doc1.select(" div.w-full");

        //extracion del PC
        String name="";
        String name2 ="";
        String img="";
        String description="";
        String brand="DELL";
        String ram = "Ram: 8 GB";
        String processor ="Intel";
        String storage ="SSD 256 GB";
        String tarjetaGrafica ="Tarjeta gráfica dedicada";

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

        productDto.setProductId(5);
        productDto.setName(name);
        productDto.setName2(name2);
        productDto.setShopName("CompuCenter");
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        productDto.setBrand(brand);
        productDto.setRam(ram);
        productDto.setProcessor(processor);
        productDto.setStorage(storage);
        productDto.setTarjetaGrafica(tarjetaGrafica);

        //llaves foraneas
        productDto.setShopId(2);

        productDtos.add( productDto);
        System.out.println("productDtos 5: " + productDtos);
        return  productDtos;
    }

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
        String name="";
        String name2 = "";
        String img="";
        String description="";
        String brand="HP";
        String ram = "Ram: 8 GB";
        String processor ="Intel";
        String storage ="SSD 512 GB";
        String tarjetaGrafica ="Tarjeta gráfica integrada";

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
        productDto.setName2(name2);
        productDto.setShopName("CompuCenter");
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        productDto.setBrand(brand);
        productDto.setRam(ram);
        productDto.setProcessor(processor);
        productDto.setStorage(storage);
        productDto.setTarjetaGrafica(tarjetaGrafica);
        //llaves foraneas
        productDto.setShopId(2);

        productDtos.add( productDto);
        System.out.println("productDtos 6: " + productDtos);
        return  productDtos;
    }


    //CompuCenter
    //Producto 7
    //List<ProductDto>
    public List<ProductDto> extractProductList7(String url) throws IOException {
        //System.out.println("Computadoras, Página CompuCenter url" + url);
        Document doc1 = Jsoup.connect(url).get();   //.timeout(10000).get();
        Elements productName = doc1.select(" div.w-full");
        Elements imgProduct = doc1.select("div.w-full");  //extraccion de imagen
        Elements productDescription = doc1.select("div.w-full.pt-4"); //extracion de detalle del PC
        Elements price = doc1.select(" div.w-full");

        //extracion del PC
        String name = "";
        String name2="";
        String img = "";
        String description = "";
        String brand="HP";
        String ram = "Ram: 12 GB";
        String processor ="AMD Ryzen";
        String storage ="SSD 256 GB";
        String tarjetaGrafica ="Tarjeta gráfica integrada";

        //productName
        for (Element e : productName.select("div.mb-10")) {
            name = e.select("h1").text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre del PC: " + name);

        //productName2 //opcional
        for (Element e : productName.select("div.flex.text-black.cursor-pointer.pb-1"))
        {
            name2 = e.select("span" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre2 opcional del PC: " + name2);


        //imgProduct
        for (Element e : imgProduct.select("div.relative.border-4.border-red-100.rounded-lg")) {
            img = e.select("  img ").attr("src"); //Obtener src, img del PC
            //System.out.println("imagen : " + img);
        }

        //pruductDescription
        for (Element e : productDescription.select("div.text-base")) {
            description = e.select(" div ").text(); //Obtener marca del PC
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

        productDto.setProductId(7);
        productDto.setName(name);
        productDto.setName2(name2);
        productDto.setShopName("CompuCenter");
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        productDto.setBrand(brand);
        productDto.setRam(ram);
        productDto.setProcessor(processor);
        productDto.setStorage(storage);
        productDto.setTarjetaGrafica(tarjetaGrafica);

        //llaves foraneas
        productDto.setShopId(2);

        productDtos.add( productDto);
        System.out.println("productDtos 7: " + productDtos);
        return  productDtos;
    }

    //Producto 8
    //List<ProductDto>
    public List<ProductDto> extractProductList8(String url) throws IOException {
        //System.out.println("Computadoras, Página CompuCenter url2" + url );
        Document doc1 = Jsoup.connect(url).get();    //.timeout(10000).get();
        Elements productName = doc1.select(" div.w-full");
        Elements imgProduct = doc1.select("div.w-full");  //extraccion de imagen
        Elements productDescription = doc1.select("div.w-full.pt-4"); //extracion de detalle del PC
        Elements price = doc1.select(" div.w-full");

        //extracion del PC
        String name="";
        String name2="";
        String img="";
        String description="";
        String brand="HP";
        String ram = "Ram: 16 GB";
        String processor ="Intel";
        String storage ="1 TB";
        String tarjetaGrafica ="Tarjeta gráfica integrada";

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

        productDto.setProductId(8);
        productDto.setName(name);
        productDto.setName2(name2);
        productDto.setShopName("CompuCenter");
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        productDto.setBrand(brand);
        productDto.setRam(ram);
        productDto.setProcessor(processor);
        productDto.setStorage(storage);
        productDto.setTarjetaGrafica(tarjetaGrafica);

        //llaves foraneas
        productDto.setShopId(2);

        productDtos.add( productDto);
        System.out.println("productDtos 8: " + productDtos);
        return  productDtos;
    }

    //Producto 9
    //List<ProductDto>
    public List<ProductDto> extractProductList9(String url) throws IOException {
        //System.out.println("Computadoras, Página CompuCenter url" + url );
        Document doc1 = Jsoup.connect(url).get();    //.timeout(10000).get();
        Elements productName = doc1.select(" div.w-full");
        Elements imgProduct = doc1.select("div.w-full");  //extraccion de imagen
        Elements productDescription = doc1.select("div.w-full.pt-4"); //extracion de detalle del PC
        Elements price = doc1.select(" div.w-full");

        //extracion del PC
        String name="";
        String name2="";
        String img="";
        String description="";
        String brand="HP";
        String ram = "Ram: 4 GB";
        String processor ="AMD Ryzen";
        String storage ="SSD 128 GB";
        String tarjetaGrafica ="Tarjeta gráfica integrada";
        //productName
        for (Element e : productName.select("div.mb-10"))
        {
            name = e.select("h1" ).text(); //Obtener tipo de PC
        }
        //System.out.println("Nombre del PC: " +

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

        productDto.setProductId(9);
        productDto.setName(name);
        productDto.setName2(name2);
        productDto.setShopName("CompuCenter");
        productDto.setDescription(description);
        productDto.setImg(img);
        productDto.setPrice(precio);
        productDto.setBrand(brand);
        productDto.setRam(ram);
        productDto.setProcessor(processor);
        productDto.setStorage(storage);
        productDto.setTarjetaGrafica(tarjetaGrafica);
        //llaves foraneas
        productDto.setShopId(2);

        productDtos.add( productDto);
        System.out.println("productDtos 9: " + productDtos);
        return  productDtos;
    }

    //CompuCenter, 24 PRODUCTOS
    //List<ProductDto>
    public List<ProductDto> compuCenterLaptops(String url) throws IOException {
        System.out.println("CompuCenter, Equipos Laptos, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(9000).get();
        Elements body = doc.select("section.flex.flex-wrap.justify-center.items-center");
        //
        //String nombre1 ="";
        String nombre2 ="";
        String descripcion ="";
        String precio ="";
        String imagen ="";
        String brand="Asus";
        String ram = "Ram: 4 GB";
        String processor ="Intel";
        String storage ="SSD 128 GB";
        String tarjetaGrafica ="Tarjeta gráfica integrada";


        //Lista para guardar los productos que recorrera el for
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
        System.out.println("-------Inicio------------");
        for (Element e : body.select("div.flex.items-center.p-5.relative.w-full"))
        {
            String nombre1 = e.select(" div.flex.flex-col.mb-2 strong  ").text();
            nombre2 = e.select(" h1.font-semibold.text-lg ").text();
            descripcion = e.select(" div.text-base.text-justify ").text();
            precio = e.select(" div.inline-block.align-bottom.mr-5 span ").text();
            imagen = e.select(" div.relative.border-4.border-red-100 img  ").attr("src");
            /**
            System.out.println("- nombre1(Marca): "+ nombre1);
            System.out.println("  nombre2:  "+ nombre2);
            System.out.println("  descripcion:  "+ descripcion);
            System.out.println("  precio:  "+ precio);
            System.out.println("  Imagen:   "+ imagen);
            System.out.println("");
            */
            /////////////////////////////////////////////////////////////////////////

            //PRUEBA de ARRAY SIN BASE DE DATOS
            //List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products
            ProductDto productDto = new ProductDto();
            productDto.setProductId(1);//nombre1 + ""+ shop
            productDto.setName(nombre1);
            productDto.setName2(nombre2);
            productDto.setShopName("CompuCenter");
            productDto.setDescription(descripcion);
            productDto.setImg(imagen);
            productDto.setPrice(precio);
            productDto.setBrand(brand);
            productDto.setRam(ram);
            productDto.setProcessor(processor);
            productDto.setStorage(storage);
            productDto.setTarjetaGrafica(tarjetaGrafica);
            //llaves foraneas
            productDto.setShopId(2);

            productDtos.add( productDto);
            System.out.println("for productDto CompuCenter: " + productDto);   //muestra las listas de los productos por separado

        }

        System.out.println("-------Fin------------");
        System.out.println("CompuCenter productDtos: " + productDtos);  //muestra  el conjunto de listas de productos en una sola lista
        return  productDtos;
    }


    //////////////////////////////////////////////////////////////////////
    //Prueba
    //List<ProductDto>
    public List<ProductDto> productListAllPrueba(String urlCompuCenter, String url4, String url5, String url6, String url7, String url8, String url9) throws IOException{
        //Productos Extraidos
        List<ProductDto> productsCompuCenter = new ArrayList<ProductDto>();

        List<ProductDto> productDtos4 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos5 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos6 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos7 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos8 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos9 = new ArrayList<ProductDto>();

        //products CompuCenter
        productsCompuCenter = compuCenterLaptops(urlCompuCenter);

        //producto 4
        productDtos4 = extractProductList4(url4);
        //producto 5
        productDtos5 = extractProductList5(url5);
        //producto 6
        productDtos6 = extractProductList6(url6);
        //producto 7
        productDtos7 = extractProductList7(url7);
        //producto 8
        productDtos8 = extractProductList8(url8);
        //producto 9
        productDtos9 = extractProductList9(url9);

        List<ProductDto> productAll = new ArrayList<ProductDto>(); // Lista para guardar todos los productos
        productAll.addAll(productsCompuCenter);

        productAll.addAll(productDtos4);
        productAll.addAll(productDtos5);
        productAll.addAll(productDtos6);
        productAll.addAll(productDtos7);
        productAll.addAll(productDtos8);
        productAll.addAll(productDtos9);
        //System.out.println("product all: " + productAll);

        return  productAll;
    }

    //prueba peoductListAll2, PARA EL HILO
    //List<ProductDto>
    public List<ProductDto> productListAll2Prueba() {
        //Productos Extraidos
        List<ProductDto> productsCompuCenter = new ArrayList<ProductDto>();

        List<ProductDto> productDtos4 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos5 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos6 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos7 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos8 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos9 = new ArrayList<ProductDto>();

        //productos
        try {
            //products CompuCenter
            productsCompuCenter = compuCenterLaptops(urlCompuCenter);

            //producto 4
            productDtos4 = extractProductList4(url4);
            //producto 5
            productDtos5 = extractProductList5(url5);
            //producto 6
            productDtos6 = extractProductList6(url6);
            //producto 7
            productDtos7 = extractProductList7(url7);
            //producto 8
            productDtos8 = extractProductList8(url8);
            //producto 9
            productDtos9 = extractProductList9(url9);

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<ProductDto> productAll = new ArrayList<ProductDto>(); // Lista para guardar todos los productos
        productAll.addAll(productsCompuCenter);

        productAll.addAll(productDtos4);
        productAll.addAll(productDtos5);
        productAll.addAll(productDtos6);
        productAll.addAll(productDtos7);
        productAll.addAll(productDtos8);
        productAll.addAll(productDtos9);
        //System.out.println("product all: " + productAll);

        return  productAll;
    }
    //////////////////////////////////////////////////////////////////////


    //LISTADO DE TODOS LOS PRODUCTOS
    //String urlCompuCenter ,
    public List<ProductDto> productListAll(String url4, String url5, String url6, String url7, String url8, String url9) throws IOException{

        //Productos Extraidos
        List<ProductDto> productDtosCompuCenter = new ArrayList<ProductDto>();

        List<ProductDto> productDtos4 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos5 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos6 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos7 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos8 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos9 = new ArrayList<ProductDto>();

        //products CompuCenter
        //productDtosCompuCenter = compuCenterLaptops(urlCompuCenter);
        //compuCenterLaptops(urlCompuCenter);

        //producto 4
        productDtos4 = extractProductList4(url4);
        //extractProductList4(url4);
        //producto 5
        productDtos5 = extractProductList5(url5);

        //producto 6
        productDtos6 = extractProductList6(url6);
        //producto 7
        productDtos7 = extractProductList7(url7);
        //producto 8
        productDtos8 = extractProductList8(url8);
        //producto 9
        productDtos9 = extractProductList9(url9);


        List<ProductDto> productAll = new ArrayList<ProductDto>(); // Lista para guardar todos los productos
        productAll.addAll(productDtosCompuCenter);

        productAll.addAll(productDtos4);
        productAll.addAll(productDtos5);
        productAll.addAll(productDtos6);
        productAll.addAll(productDtos7);
        productAll.addAll(productDtos8);
        productAll.addAll(productDtos9);

        //System.out.println("product all: " + productAll);

        return  productAll;
    }

    //LISTADO DE TODOS LOS PRODUCTOS, para el hilo
    public List<ProductDto> productListAll2() {

        //Productos Extraidos
        List<ProductDto> productDtosCompuCenter = new ArrayList<ProductDto>();

        List<ProductDto> productDtos4 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos5 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos6 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos7 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos8 = new ArrayList<ProductDto>();
        List<ProductDto> productDtos9 = new ArrayList<ProductDto>();



        //productos
        try {
            //products CompuCenter
            //productDtosCompuCenter = compuCenterLaptops(urlCompuCenter);

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
            //System.out.println("producto9: " + productDtos9);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("Tamaño: " + productDtos.size());


        List<ProductDto> productAll = new ArrayList<ProductDto>(); // Lista para guardar todos los productos
        //productAll.addAll(productDtosCompuCenter);

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
    //String urlCompuCenter,
    public ProductDto findProductById(Integer productId,  String url4, String url5, String url6, String url7, String url8, String url9)throws IOException {
        List<ProductDto> productDtosFor = productListAll( url4, url5, url6, url7, url8, url9); //se crea para el for y para llamar al metodo productListAll
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


    //Creacion metodo para Hilo con actualizacion
    //List<ProductDto>
    public void run() {
        //int i2 = 0;
        while (true) {
            //Enviand mensaje de prueba a la consola en frontend
            //Greeting greeting = new Greeting("Hello word, " );
            //this.template.convertAndSend("/topic/greetings", greeting);

            //llamando al metodo con los productos
/**
            System.out.println(" Thread is running...");
            List<ProductDto> obj2 = new ArrayList<ProductDto>(); //se crea productDtos para tener el listado de products
            obj2 = productListAll2();
            //Enviand productos extraidos a la consola en frontend
            this.template.convertAndSend("/topic/products", obj2);
*/
            // Prueba hilo1
            System.out.println(" Thread is running...");
            List<ProductDto> obj2 = new ArrayList<ProductDto>();
            obj2 = productListAll2Prueba();
            this.template.convertAndSend("/topic/products",  obj2);

            System.out.println(" Fin Hilo obj2");

            try {
                //Thread, Wait for one sec so it doesn't print too fast
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //return obj2;
        }//fin while
    }

    //probando hilo, con un for
    public void run2() {
        //int i = 0;
        while (true) {
            //System.out.println(this.getName() + ": New Thread is running..." + i2++);
            System.out.println(" Thread is running...");
             for (int i=0; i < 5; i++){
             System.out.println("Proceso 1, "+ i);
             }

            try {
                //Wait for one sec so it doesn't print too fast
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




//LISTADO de TIENDAS, extraccion de tiendas  sin  BD
    //Tienda 1: Dismac
    public List<ShopDto> extractShopList(String url) throws IOException {
        System.out.println("Extrayendo inf. de Tienda 1, página DISMAC " + url + "...");
        Document doc = Jsoup.connect(url).get();      //.timeout(10000).get();
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
            //System.out.println("Descripcion: " + description);
        }
        //location
        for (Element e : locationPage.select("div.span-text"))
        {
            location = e.select("span.text" ).text();
        }
        //System.out.println("Location: " + location);

        //img
        for (Element e : imgPage.select("div.empresa"))
        {
            img = e.select("div.banner.desktop img").attr("src"); //Obtener src, img del PC
            //System.out.println("Logo de la tienda 1: " + img);
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
        System.out.println("Tienda 1: " + shopDtos);
        return shopDtos;
    }

    //Tienda 2: CompuCenter
    public List<ShopDto> extractShopList2(String url) throws IOException {
        System.out.println("Extrayendo inf. de Tienda 2, página CompuCenter  " + url );
        Document doc = Jsoup.connect(url).get();      //.timeout(10000).get();
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
            //System.out.println("Descripcion: \n" + description);
        }
        //location
        for (Element e : locationPage.select(" div.m-auto.text-gray-800 "))
        {
            location = e.select("div span" ).text().replaceAll("\\\\nl", "\n");
            //System.out.println("Location: " + location );

        }

        //img
        for (Element e : imgPage.select("div.mb-0"))
        {
            img = e.select(" a img ").attr("src"); //Obtener src, img del PC
            //System.out.println("Logo de la tienda 2: " + img);
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
        System.out.println("tienda 2: " + shopDtos);
        return shopDtos;
    }

    //Tienda 3: Multilaptops
    public List<ShopDto> extractShopList3(String url) throws IOException {
        System.out.println("Extrayendo inf. de Tienda 3, página CompuCenter  " + url + "...");
        Document doc = Jsoup.connect(url).get();      //.timeout(10000).get();
        Elements descriptionPage = doc.select(" section.content-section.bg-light.mt-5.mb-2.py-5.col-12"); // buscando por clase, <div class = first >
        Elements locationPage = doc.select("section.content-section.col-12.col-md-6.p-0.pr-2.d-flex");
        Elements imgPage = doc.select(" div.carousel-inner");

        String description = "";
        String location = "";
        String img = "";
        //Description
        for (Element e : descriptionPage.select("div.col-lg-10")) {
            description = e.select(" p ").text();
            //System.out.println("Descripcion: " + description);
        }
        //location
        for (Element e : locationPage.select("div.col-lg-10")) {
            location = e.select("p ").text();
        }
        //System.out.println("Location: " + location);

        //img
        for (Element e : imgPage.select("div.carousel-item ")) {
            img = e.select(" img ").attr("src"); //Obtener src, img del PC
            //System.out.println("Logo de la tienda 3: " + img);
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
        System.out.println("tienda 3: " + shopDtos);
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
