package webcrawler.prueba.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import webcrawler.prueba.bl.ShopBl;
import webcrawler.prueba.bl.TransactionBl;
import webcrawler.prueba.dto.ProductDto;
import webcrawler.prueba.dto.ShopDto;
import webcrawler.prueba.model.Transaction;
import webcrawler.prueba.util.TransactionUtil;
import webcrawler.prueba.webCrawler.ComputerPageOne;
import webcrawler.prueba.webCrawler.ExtractStores;
import webcrawler.prueba.webCrawler.ComputerPageTwo;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/v1/shop")
public class ShopApi {

    private ShopBl shopBl;
    private TransactionBl transactionBl;
    private ComputerPageOne computerPageOne;
    private ComputerPageTwo computerPageTwo;
    private ExtractStores extractStores;


    @Autowired
    public ShopApi (ShopBl shopBl, ComputerPageOne computerPageOne, ComputerPageTwo computerPageTwo, ExtractStores extractStores, TransactionBl transactionBl){
        this. shopBl = shopBl;
        this.computerPageOne = computerPageOne;
        this.computerPageTwo = computerPageTwo;
        this.extractStores = extractStores;
        this.transactionBl = transactionBl;
    }
    public ShopApi(){}

    /**
    //listado de tiendas
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ShopDto> selectShops(HttpServletRequest request){
        return shopBl.selectShops();
    }
    */
    /**
    //encontar shop ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ShopDto findById(@PathVariable("id") Integer id, HttpServletRequest request){
        return shopBl.findShopById(id);
    }
    */

    //Crear shop
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ShopDto createBrand(@RequestBody ShopDto shopDto, HttpServletRequest request){
        //Creamos transaccion para la operacion.
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        ShopDto shopDtoResponse = shopBl.createShop(shopDto, transaction);
        return shopDtoResponse;
    }

    //UPDATE de Shop
    @RequestMapping(method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ShopDto updateShop(@RequestBody ShopDto shopDto, HttpServletRequest request){
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction((transaction));
        ShopDto Response = shopBl.updateShop(shopDto, transaction);
        return Response;
    }


///////////////////////////
    //TIENDAS 1, 2, 3
    //LISTA DE TIENDAS SIN BASE DE DATOS
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ShopDto> selectShops(HttpServletRequest request)throws IOException {
        //dirección tienda 1
        String url="https://www.dismac.com.bo/empresa.html";  //tienda 1
        //dirección tienda 2
        String url2="https://compucenter.store/about";  //tienda 2, img y ubicacion, description
        //tienda 3, TechStore
        String url3="https://techstore.bo/contactanos/";  //tienda 3, img, descripcipon y ubicacion

        //dirección tienda 4, Pc.com
        String url4="https://www.pc.com.bo/index.html#";
        //dirección tienda 5, Creativo computacion
        String url5="https://creativo.com.bo/";
        //dirección tienda 6, Hp
        String url6="https://www.hp.com/cl-es/hp-information.html";

        return extractStores.shopListAll(url, url2 , url3, url4, url5, url6);
    }
    //FIN


    //encontar shop ID SIN BASE DE DATOS
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ShopDto findById(@PathVariable("id") Integer id, HttpServletRequest request)throws IOException {
        //dirección tienda 1
        String url="https://www.dismac.com.bo/empresa.html";  //tienda 1
        //dirección tienda 2
        String url2="https://compucenter.store/about";  //tienda 2, img y ubicacion, description
        //tienda 3, TechStore
        String url3="https://techstore.bo/contactanos/";  //tienda 3, img, descripcipon y ubicacion

        //dirección tienda 4, Pc.com
        String url4="https://www.pc.com.bo/index.html#";
        //dirección tienda 5, Creativo computacion
        String url5="https://creativo.com.bo/";
        //dirección tienda 6, Hp
        String url6="https://www.hp.com/cl-es/hp-information.html";

        return extractStores.findShopById(id, url, url2, url3,  url4, url5, url6);
    }
    //FIN




///////////////////////////


///////////////////////////
    //TIENDAS 4, 5, 6
    //LISTA DE TIENDAS SIN BASE DE DATOS
    //cambiamos en path=" ", para que el frontend reconozco la lista de productos de tiendas 4 , 5 ,6
//    @RequestMapping(path ="/productsByCategory/{categoryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)



/**
    //Tienda 1
    //Extrae informacion de pagina web y guarda los datos en BD.
    @RequestMapping(path ="/crawler1", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler1(@RequestBody ShopDto shopDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección tienda 1
        String url="https://www.dismac.com.bo/empresa.html";  //tienda 1
        computerPageOne.extractShop(url, shopDto,transaction);
    }

    //Tienda 2
    //Extrae informacion de pagina web y guarda los datos en BD.
    @RequestMapping(path ="/crawler2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler2(@RequestBody ShopDto shopDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección tienda 2
        String url="https://compucenter.store/about";  //tienda 2, img y ubicacion, description
        computerPageTwo.extractShop(url, shopDto,transaction);
    }

    //Tienda 3
    //Extrae informacion de pagina web y guarda los datos en BD.
    @RequestMapping(path ="/crawler3", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler3(@RequestBody ShopDto shopDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección tienda 3
        String url="https://www.multilaptops.net/acerca";  //tienda 3, img, descripcipon y ubicacion
        computerPageThree.extractShop(url, shopDto,transaction);
    }


//Actualizar tiendas
 //Tienda 1
    @RequestMapping(path ="/updateShopCrawler1", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ShopDto updateShopCrawler1(@RequestBody ShopDto shopDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction((transaction));
        //dirección tienda 1
        String url="https://www.dismac.com.bo/empresa.html";  //tienda 1
        ShopDto Response = computerPageOne.updateShop(url, shopDto, transaction);
        return Response;
    }


//Tienda 2
    @RequestMapping(path ="/updateShopCrawler2", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ShopDto updateShopCrawler2(@RequestBody ShopDto shopDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction((transaction));
        //dirección tienda 2
        String url="https://compucenter.store/about";  //tienda 2, img y ubicacion, description
        ShopDto Response = computerPageTwo.updateShop(url, shopDto, transaction);
        return Response;
    }

//Tienda 3
    @RequestMapping(path ="/updateShopCrawler3", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ShopDto updateShopCrawler3(@RequestBody ShopDto shopDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction((transaction));
        //dirección tienda 2
        String url="https://www.multilaptops.net/acerca";  //tienda 3, img, descripcipon y ubicacion
        ShopDto Response = computerPageThree.updateShop(url, shopDto, transaction);
        return Response;
    }




*/


}
