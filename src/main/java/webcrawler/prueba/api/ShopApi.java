package webcrawler.prueba.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import webcrawler.prueba.bl.ShopBl;
import webcrawler.prueba.bl.TransactionBl;
import webcrawler.prueba.dto.ProductDetailDto;
import webcrawler.prueba.dto.ProductDto;
import webcrawler.prueba.dto.ShopDto;
import webcrawler.prueba.model.Transaction;
import webcrawler.prueba.util.TransactionUtil;
import webcrawler.prueba.webCrawler.ComputerPageOne;
import webcrawler.prueba.webCrawler.ComputerPageThree;
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
    private ComputerPageThree computerPageThree;


    @Autowired
    public ShopApi (ShopBl shopBl, ComputerPageOne computerPageOne, ComputerPageTwo computerPageTwo, ComputerPageThree computerPageThree, TransactionBl transactionBl){
        this. shopBl = shopBl;
        this.computerPageOne = computerPageOne;
        this.computerPageTwo = computerPageTwo;
        this.computerPageThree = computerPageThree;
        this.transactionBl = transactionBl;
    }
    public ShopApi(){}

    //listado de tiendas
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ShopDto> selectShops(HttpServletRequest request){
        return shopBl.selectShops();
    }

    //encontar shop ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ShopDto findById(@PathVariable("id") Integer id, HttpServletRequest request){
        return shopBl.findShopById(id);
    }

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



    // PRUEBA, actualizando Shop1, product and productDetail
    @RequestMapping(path ="/updateShop1", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateShop1(@RequestBody ShopDto shopDto, ProductDto productDto,Transaction transaction2, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction((transaction));
        computerPageOne.updateShopPrueba(shopDto, productDto,transaction2);
    }


}
