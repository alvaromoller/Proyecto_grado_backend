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
public class ExtractStores {

    //ShopDao
    private ShopDao shopDao;



    @Autowired
    public ExtractStores( ShopDao shopDao) {
        this.shopDao = shopDao;
    }

    public ExtractStores() {

    }

/***/
    //LISTADO de TIENDAS, extraccion de tiendas  sin  BD
    //Tienda 1: Dismac
    public List<ShopDto> extractShopList(String url) throws IOException {
        System.out.println("Extrayendo inf. de Tienda 1, página DISMAC " + url + "...");
        Document doc = Jsoup.connect(url).get();      //.timeout(10000).get();
        Elements descriptionPage = doc.select(" div.first"); // buscando por clase, <div class = first >
        Elements locationPage = doc.select("div.bh-sl-loc-list");
        Elements imgPage = doc.select(" div.category-view");

        String name="Dismac";
        String description="Somos una empresa Boliviana fundada en el año de 1985  que pertenece a la industria retail.\n" +
        "En una trayectoria de  más de 30 años,  nos hemos posicionado en el país como una de las marcas con mayor confianza para el mejoramiento del hogar.";
        String location ="https://www.dismac.com.bo/categorias/54-electronica/computacion/computadoras.html";
        String img="";
        //Description

        for (Element e : descriptionPage.select("div.full"))
        {
            description = e.select("div.span-text" ).text();
            //System.out.println("Descripcion: " + description);
        }
        //location
        for (Element e : locationPage.select(" li "))
        {
            location = e.select("div.store-addr " ).text();
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
        shopDto.setName(name);
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

        String name ="CompuCenter";
        String description="Legalmente establecida en territorio nacional, nace un 22 de Febrero de 2010 enfocado a la comercialización de equipos de " +
                "computación y soluciones informáticas a la necesitad de cada cliente sea de Hogar, Empresa y/o Institución Pública. Dando soluciones" +
                " con las marcas más reconocidas a nivel mundial en equipos de cómputo y desarrollo de Software a medida.\n" ;
        String location ="https://compucenter.store/#";
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
        shopDto.setName(name);
        shopDto.setDescription(description);
        shopDto.setLocation(location);
        shopDto.setImg("https://compucenter.store/_nuxt/img/logo_cc_lg.c362771.svg");

        shopDtos.add( shopDto);
        System.out.println("tienda 2: " + shopDtos);
        return shopDtos;
    }

    //Tienda 3: techstore
    public List<ShopDto> extractShopList3(String url) throws IOException {
        System.out.println("Tienda 3, página TechStore " + url );
        Document doc = Jsoup.connect(url).get();      //.timeout(10000).get();
        Elements descriptionPage = doc.select(" section.vc_row.wpb_row.vc_row-fluid "); //
        Elements locationPage = doc.select(" div.wpb_wrapper ");
        Elements imgPage = doc.select(" section.vc_row.wpb_row");

        //Salto de linea locationPage
        locationPage.select("div.my-2 span:matches(La Paz|Cochabamba|Santa Cruz) ").prepend("\\nl "); //append salto de linea
        locationPage.select("div.my-1 span").prepend("\\nl "); //append salto de linea
        locationPage.select("div.my-1 span").append("\\nl "); //append salto de linea
        //

        String name="TechStore";
        String description="Tech Store Bolivia es una de la tiendas autorizadas de Apple para Bolivia, bajo la figura de Authorized Reseller." +
                " Cuenta con una variedad de productos como ser iPhone, iPad, Apple Watch, Mac, Apple TV, Macbook Pro, Macbook Air, iMac, iMacPro," +
                " accesorios, entretenimiento y soporte técnico.";
        String location = "https://techstore.bo/product-category/mac/";
        String img="";
        //Description
        for (Element e : descriptionPage.select("div.wpb_column.vc_column_container "))
        {
            description = e.select("div.wpb_wrapper  span " ).text();//no deja extraer datos
            description = "Tech Store Bolivia es una de la tiendas autorizadas de Apple para Bolivia, bajo la figura de Authorized Reseller. Cuenta con una variedad de productos como ser iPhone, iPad, Apple Watch, Mac, Apple TV, Macbook Pro, Macbook Air, iMac, iMacPro, accesorios, entretenimiento y soporte técnico.";
            //System.out.println("Descripcion: \n" + description);
        }
        //location
        List<String> locationList = new ArrayList<String>();
        for (Element e : locationPage.select(" div.hongo-featurebox-wrap.hongo-product-featurebox "))
        {
            location = e.select("div.hongo-featurebox-wrap.hongo-product-featurebox " ).text().replaceAll("\\\\nl", "\n");
            locationList.add(location);
            //System.out.println("locationList: " + locationList );
        }
        //img
        for (Element e : imgPage.select("div.header-logo-wrapper"))
        {
            img = e.select(" a img ").attr("src"); //Obtener src, img del PC
            //System.out.println("Logo de la tienda 3: " + img);
        }
        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ShopDto> shopDtos = new ArrayList<ShopDto>(); // se crea ShopDto para tener el listado de tiendas
        ShopDto shopDto = new ShopDto();

        shopDto.setShopId(3);
        shopDto.setName(name);
        shopDto.setDescription(description);
        shopDto.setLocation(location);
        //shopDto.setLocation2(locationList);
        shopDto.setImg(img);

        shopDtos.add( shopDto);
        System.out.println("tienda 3: " + shopDtos);
        return shopDtos;
    }


    //Tienda 4: Pc.COM
    //https://www.pc.com.bo/index.html#
    public List<ShopDto> pcTienda(String url) throws IOException {
        System.out.println("Tienda 4, Pc.com " + url + "...");
        Document doc = Jsoup.connect(url).timeout(9000).get();
        Elements body = doc.select("div.box-contact-info");
        //Salto de linea descriptionPage
        body.select("br").append("\\nl"); //append salto de linea despues de un elemento
        body.select("span").prepend("\\nl"); //append salto de linea Antes de un elemento

        //ubicacion
        String nombre = "PC.COM";
        String location ="";
        String imagen = "https://www.pc.com.bo/assets/img/slide/index/1.jpg";


        List<ShopDto> shopDtos = new ArrayList<ShopDto>(); // se crea ShopDto para tener el listado de tiendas
        System.out.println("--------Inicio Tienda PC.COM-----------");
        for (Element e : body.select("ul"))
        {
            location = e.select(" li span  ").text().replaceAll("\\\\nl", "\n");
            //System.out.println("  Ubicación: "+ location);

            /////////////////////////////////////////////////////////////////////////
            //PRUEBA de ARRAY SIN BASE DE DATOS
            ShopDto shopDto = new ShopDto();

            shopDto.setShopId(4);
            shopDto.setName(nombre);
            shopDto.setDescription("Sin Descripción");
            shopDto.setLocation(location);
            shopDto.setImg(imagen);

            shopDtos.add( shopDto);
            //System.out.println("Tienda PC.COM: " + shopDto);

        }
        System.out.println("--------FIN-----------");
        System.out.println("Tienda PC.COM: " + shopDtos);
        return shopDtos;
    }

    //Tienda 5: Creativo computacion
    //https://creativo.com.bo/
    public List<ShopDto> creativoTienda(String url) throws IOException {
        System.out.println("Creativo computacion Tienda, " + url + "...");
        Document doc = Jsoup.connect(url).timeout(9000).get();
        Elements body = doc.select("div.et_pb_with_border.et_pb_module.et_pb_text");
        //Salto de linea
        body.select("br").append("\\nl"); //append salto de linea despues de un elemento
        body.select("span").prepend("\\nl"); //append salto de linea Antes de un elemento

        //ubicacion, descripcion
        String nombre = "Computación, diseño creativo";
        String imagen = "https://creativo.com.bo/wp-content/uploads/2021/08/DisenoAprobadoLogoCreativoLB.png";
        String location =" https://creativo.com.bo/";
        List<ShopDto> shopDtos = new ArrayList<ShopDto>(); // se crea ShopDto para tener el listado de tiendas
        System.out.println("--------Inicio Tienda Creativo computacion-----------");
        for (Element e : body.select("div.et_pb_text_inner"))
        {
            //no tiene descripcion
            //String location = e.select(" div span ").text().replaceAll("\\\\nl", "\n");
            //System.out.println("  Ubicación: "+ location);

            /////////////////////////////////////////////////////////////////////////
            //PRUEBA de ARRAY SIN BASE DE DATOS
            ShopDto shopDto = new ShopDto();

            shopDto.setShopId(5);
            shopDto.setName(nombre);
            shopDto.setDescription("Sin Descripción");
            shopDto.setLocation(location);
            shopDto.setImg(imagen);

            shopDtos.add( shopDto);
            //System.out.println("Tienda PC.COM: " + shopDto);

        }
        System.out.println("--------FIN-----------");
        System.out.println("Tienda Creativo computacion: " + shopDtos);
        return shopDtos;
    }

    //Tienda 6: Creativo computacion
    //https://www.hp.com/cl-es/hp-information.html
    public List<ShopDto> hpTienda(String url) throws IOException {
        System.out.println("Tienda 6, Hp " + url + "...");
        Document doc = Jsoup.connect(url).timeout(9000).get();
        Elements body = doc.select("hp-grid#abaut-us");

        String name = "Hp";
        String description ="";
        String imagen = "https://d1pc5hp1w29h96.cloudfront.net/logo/default/HP_logo.svg";
        String location ="https://www.hp.com/cl-es/home.html";

        List<ShopDto> shopDtos = new ArrayList<ShopDto>(); // se crea ShopDto para tener el listado de tiendas
        System.out.println("--------Inicio Tienda Hp----------");
        for (Element e : body.select(" div.c-title-and-text__description "))
        {
            description = e.select(" p  ").text();
            //System.out.println("  description: "+ description);

            /////////////////////////////////////////////////////////////////////////
            //PRUEBA de ARRAY SIN BASE DE DATOS
            ShopDto shopDto = new ShopDto();

            shopDto.setShopId(6);
            shopDto.setName(name);
            shopDto.setDescription(description);
            shopDto.setLocation(location);
            shopDto.setImg(imagen);

            shopDtos.add( shopDto);
            //System.out.println("Tienda 6 Hp: " + shopDto);

        }
        System.out.println("--------FIN-----------");
        System.out.println("Tienda 6 Hp: " + shopDtos);
        return shopDtos;
    }

/***/
    //LISTADO DE TODAS LAS TIENDAS
    public List<ShopDto> shopListAll(String url, String url2, String url3,  String url4, String url5, String url6) throws IOException{
        List<ShopDto> shopDtos = new ArrayList<ShopDto>(); //se crea productDtos para tener el listado de products
        List<ShopDto> shopDtos2 = new ArrayList<ShopDto>();
        List<ShopDto> shopDtos3 = new ArrayList<ShopDto>();

        //PC.com, Creativo computacion
        List<ShopDto> shopDtos4 = new ArrayList<ShopDto>();
        List<ShopDto> shopDtos5 = new ArrayList<ShopDto>();
        List<ShopDto> shopDtos6 = new ArrayList<ShopDto>();


        //Tienda 1
        shopDtos = extractShopList(url);
        //Tienda 2
        shopDtos2 = extractShopList2(url2);
        //Tienda 3
        shopDtos3 = extractShopList3(url3);

        //Tienda PC.COM
        shopDtos4 = pcTienda(url4);
        //Tienda Creativo computacion
        shopDtos5 = creativoTienda(url5);
        //Hp
        shopDtos6 = hpTienda(url6);

        List<ShopDto> shopAll = new ArrayList<ShopDto>(); // Lista para guardar todos los productos
        shopAll.addAll(shopDtos);
        shopAll.addAll(shopDtos2);
        shopAll.addAll(shopDtos3);
        shopAll.addAll(shopDtos4);
        shopAll.addAll(shopDtos5);
        shopAll.addAll(shopDtos6);

        //System.out.println("Shops all: " + shopAll);
        return  shopAll;
    }

    //TIENDA POR ID
    public ShopDto findShopById(Integer shopId, String url, String url2, String url3,    String url4, String url5, String url6 ) throws IOException {
        List<ShopDto> shopDtosFor = shopListAll(url, url2, url3, url4, url5, url6); //se crea para el for y para llamar al metodo shopListAll
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

//PRUEBA NUEVO METODOS PARA TIENDAS
    //Tienda 1: Dismac
    public List<ShopDto> shopDismac() throws IOException {
        System.out.println("Dismac  "  );
        String name="Dismac";
        String description="Somos una empresa Boliviana fundada en el año de 1985  que pertenece a la industria retail.\n" +
                "En una trayectoria de  más de 30 años,  nos hemos posicionado en el país como una de las marcas con mayor confianza para el mejoramiento del hogar.";
        String location ="https://www.dismac.com.bo/categorias/54-electronica/computacion/computadoras.html";
        String img="https://www.dismac.com.bo/media/logo/stores/1/dismac.png";
        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ShopDto> shopDtos = new ArrayList<ShopDto>(); // se crea ShopDto para tener el listado de tiendas
        ShopDto shopDto = new ShopDto();

        shopDto.setShopId(1);
        shopDto.setName(name);
        shopDto.setDescription(description);
        shopDto.setLocation(location);
        shopDto.setImg(img);

        shopDtos.add( shopDto);
        System.out.println("Tienda 1: " + shopDtos);
        return shopDtos;
    }

    //Tienda 2: CompuCenter
    public List<ShopDto> shopCompuCenter() throws IOException {
        System.out.println(" CompuCenter  " );

        String name ="CompuCenter";
        String description="Legalmente establecida en territorio nacional, nace un 22 de Febrero de 2010 enfocado a la comercialización de equipos de " +
                "computación y soluciones informáticas a la necesitad de cada cliente sea de Hogar, Empresa y/o Institución Pública. Dando soluciones" +
                " con las marcas más reconocidas a nivel mundial en equipos de cómputo y desarrollo de Software a medida.\n" ;
        String location ="https://compucenter.store/#";
        String img="https://compucenter.store/_nuxt/img/logo_cc_lg.c362771.svg";

        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ShopDto> shopDtos = new ArrayList<ShopDto>(); // se crea ShopDto para tener el listado de tiendas
        ShopDto shopDto = new ShopDto();

        shopDto.setShopId(2);
        shopDto.setName(name);
        shopDto.setDescription(description);
        shopDto.setLocation(location);
        shopDto.setImg(img);

        shopDtos.add( shopDto);
        System.out.println("tienda 2: " + shopDtos);
        return shopDtos;
    }

    //Tienda 3: techstore
    public List<ShopDto> shopTechStore() throws IOException {
        System.out.println("TechStore "  );

        String name="TechStore";
        String description="Tech Store Bolivia es una de la tiendas autorizadas de Apple para Bolivia, bajo la figura de Authorized Reseller." +
                " Cuenta con una variedad de productos como ser iPhone, iPad, Apple Watch, Mac, Apple TV, Macbook Pro, Macbook Air, iMac, iMacPro," +
                " accesorios, entretenimiento y soporte técnico.";
        String location = "https://techstore.bo/product-category/mac/";
        String img="\thttps://techstore.bo/wp-content/uploads/2020/08/TechStoreBolivia-Authorized-Reseller-.png";
        /////////////////////////////////////////////////////////////////////////
        //PRUEBA de ARRAY SIN BASE DE DATOS
        List<ShopDto> shopDtos = new ArrayList<ShopDto>(); // se crea ShopDto para tener el listado de tiendas
        ShopDto shopDto = new ShopDto();

        shopDto.setShopId(3);
        shopDto.setName(name);
        shopDto.setDescription(description);
        shopDto.setLocation(location);
        //shopDto.setLocation2(locationList);
        shopDto.setImg(img);

        shopDtos.add( shopDto);
        System.out.println("tienda 3: " + shopDtos);
        return shopDtos;
    }

    //Tienda 4: Pc.COM
    //https://www.pc.com.bo/index.html#
    /**
    public List<ShopDto> shopPcCom(String url) throws IOException {
        System.out.println("Tienda 4, Pc.com " + url + "...");
        Document doc = Jsoup.connect(url).timeout(9000).get();
        Elements body = doc.select("div.box-contact-info");
        //Salto de linea descriptionPage
        body.select("br").append("\\nl"); //append salto de linea despues de un elemento
        body.select("span").prepend("\\nl"); //append salto de linea Antes de un elemento

        //ubicacion
        String nombre = "PC.COM";
        String location ="";
        String imagen = "https://www.pc.com.bo/assets/img/slide/index/1.jpg";


        List<ShopDto> shopDtos = new ArrayList<ShopDto>(); // se crea ShopDto para tener el listado de tiendas
        System.out.println("--------Inicio Tienda PC.COM-----------");
        for (Element e : body.select("ul"))
        {
            location = e.select(" li span  ").text().replaceAll("\\\\nl", "\n");
            //System.out.println("  Ubicación: "+ location);

            /////////////////////////////////////////////////////////////////////////
            //PRUEBA de ARRAY SIN BASE DE DATOS
            ShopDto shopDto = new ShopDto();

            shopDto.setShopId(4);
            shopDto.setName(nombre);
            shopDto.setDescription("Sin Descripción");
            shopDto.setLocation(location);
            shopDto.setImg(imagen);

            shopDtos.add( shopDto);
            //System.out.println("Tienda PC.COM: " + shopDto);

        }
        System.out.println("--------FIN-----------");
        System.out.println("Tienda PC.COM: " + shopDtos);
        return shopDtos;
    }
    */

    //Tienda 5: Creativo computacion
    //https://creativo.com.bo/
    public List<ShopDto> shopCreativoComputacion() throws IOException {
        System.out.println("Creativo computacion ");
        //ubicacion, descripcion
        String nombre = "Computación, diseño creativo";
        String descripcion= "Sin descripción";
        String location ="https://creativo.com.bo/";
        String imagen = "https://creativo.com.bo/wp-content/uploads/2021/08/DisenoAprobadoLogoCreativoLB.png";

        List<ShopDto> shopDtos = new ArrayList<ShopDto>(); // se crea ShopDto para tener el listado de tiendas
            /////////////////////////////////////////////////////////////////////////
            //PRUEBA de ARRAY SIN BASE DE DATOS
            ShopDto shopDto = new ShopDto();

            shopDto.setShopId(5);
            shopDto.setName(nombre);
            shopDto.setDescription(descripcion);
            shopDto.setLocation(location);
            shopDto.setImg(imagen);

            shopDtos.add( shopDto);
            //System.out.println("Tienda PC.COM: " + shopDto);

        System.out.println("Tienda Creativo computacion: " + shopDtos);
        return shopDtos;
    }

    //Tienda 6: Creativo computacion
    //https://www.hp.com/cl-es/hp-information.html
    public List<ShopDto> shopHp() throws IOException {
        System.out.println("Tienda Hp " );

        String name = "Hp";
        String description ="Somos una compañía de tecnología que nació bajo la creencia de que las empresas deben hacer más que simplemente obtener una ganancia. Deben hacer del mundo un lugar mejor.\n" +
                "Nuestros esfuerzos para mitigar el cambio climático, el estar en pro de los derechos humanos y la equidad digital demuestran que estamos haciendo todo lo posible para lograrlo.\n" +
                "Con más de 80 años de acciones que prueban nuestras intenciones, tenemos la confianza necesaria para imaginar un mundo donde la innovación impulse contribuciones extraordinarias para la humanidad.\n" +
                "Y nuestra tecnología –un portafolio de productos y servicios de sistemas personales, impresoras y soluciones de impresión en 3D– fue creada para inspirar ese progreso.\n" +
                "Sabemos que las buenas ideas pueden venir de cualquier persona, en cualquier momento y lugar.\n" +
                "Y solo hace falta una para cambiar el mundo.";
        String location ="https://www.hp.com/cl-es/home.html";
        String imagen = "https://d1pc5hp1w29h96.cloudfront.net/logo/default/HP_logo.svg";

        List<ShopDto> shopDtos = new ArrayList<ShopDto>(); // se crea ShopDto para tener el listado de tiendas
            /////////////////////////////////////////////////////////////////////////
            //PRUEBA de ARRAY SIN BASE DE DATOS
            ShopDto shopDto = new ShopDto();

            shopDto.setShopId(6);
            shopDto.setName(name);
            shopDto.setDescription(description);
            shopDto.setLocation(location);
            shopDto.setImg(imagen);

            shopDtos.add( shopDto);
            //System.out.println("Tienda 6 Hp: " + shopDto);

        System.out.println("Tienda 6 Hp: " + shopDtos);
        return shopDtos;
    }

    //Listado de todas las tiendas
    public List<ShopDto> shopListAll2() throws IOException{
        List<ShopDto> shop1 = new ArrayList<ShopDto>(); //se crea productDtos para tener el listado de products
        List<ShopDto> shop2 = new ArrayList<ShopDto>();
        List<ShopDto> shop3 = new ArrayList<ShopDto>();

        //PC.com, Creativo computacion
        List<ShopDto> shop4 = new ArrayList<ShopDto>();
        List<ShopDto> shop5 = new ArrayList<ShopDto>();
        List<ShopDto> shop6 = new ArrayList<ShopDto>();


        //Tienda 1
        shop1 = shopDismac();
        //Tienda 2
        shop2 = shopCompuCenter();
        //Tienda 3
        shop3 = shopTechStore();

        //Tienda PC.COM
        //shop4 = pcTienda();
        //Tienda Creativo computacion
        shop5 = shopCreativoComputacion();
        //Hp
        shop6 = shopHp();

        List<ShopDto> shopAll = new ArrayList<ShopDto>(); // Lista para guardar todos los productos
        shopAll.addAll(shop1);
        shopAll.addAll(shop2);
        shopAll.addAll(shop3);
        //shopAll.addAll(shop4);
        shopAll.addAll(shop5);
        shopAll.addAll(shop6);

        //System.out.println("Shops all: " + shopAll);
        return  shopAll;
    }






}
