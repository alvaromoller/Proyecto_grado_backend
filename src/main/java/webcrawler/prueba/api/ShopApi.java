package webcrawler.prueba.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import webcrawler.prueba.bl.ShopBl;
import webcrawler.prueba.bl.TransactionBl;
import webcrawler.prueba.dto.ShopDto;
import webcrawler.prueba.model.Transaction;
import webcrawler.prueba.util.TransactionUtil;
import webcrawler.prueba.webCrawler.ComputerPageOne;

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

    @Autowired
    public ShopApi (ShopBl shopBl, ComputerPageOne computerPageOne, TransactionBl transactionBl){
        this. shopBl = shopBl;
        this.computerPageOne = computerPageOne;
        this.transactionBl = transactionBl;
    }
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

    //Crear Marca
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ShopDto createBrand(@RequestBody ShopDto shopDto, HttpServletRequest request){
        //Creamos transaccion para la operacion.
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        ShopDto shopDtoResponse = shopBl.createShop(shopDto, transaction);
        return shopDtoResponse;
    }

    //Tienda 1
    //Extrae informacion de pagina web y guarda los datos en BD.
    @RequestMapping(path ="/crawler1", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler1(@RequestBody ShopDto shopDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección tienda 1
        String url="https://www.intecsa.com.bo/nosotros/";  //tienda 1
        computerPageOne.extractShop(url, shopDto,transaction);
    }

}
