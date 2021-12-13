package webcrawler.prueba.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import webcrawler.prueba.bl.ProductTypeBl;
import webcrawler.prueba.bl.TransactionBl;
import webcrawler.prueba.dto.ProductTypeDto;
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
@RequestMapping(value = "/v1/productType")
public class ProductTypeApi {

    private ProductTypeBl productTypeBl;
    private TransactionBl transactionBl;

    private ComputerPageOne computerPageOne;
    private ComputerPageTwo computerPageTwo;
    private ComputerPageThree computerPageThree;



    @Autowired
    public ProductTypeApi (ProductTypeBl productTypeBl, ComputerPageOne computerPageOne, ComputerPageTwo computerPageTwo, ComputerPageThree computerPageThree, TransactionBl transactionBl){
        this.productTypeBl = productTypeBl;
        this.computerPageOne = computerPageOne;
        this.computerPageTwo = computerPageTwo;
        this.computerPageThree = computerPageThree;
        this.transactionBl = transactionBl;

    }

    //listado de tipos de productos
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductTypeDto> selectProductTypes(){
        return productTypeBl.selectProductTypes();
    }

    //metodo para encontrar productType por ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductTypeDto findById(@PathVariable("id") Integer id, HttpServletRequest request){
        return productTypeBl.findProductTypeById(id);
    }

    //Crear productType
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductTypeDto createBrand(@RequestBody ProductTypeDto productTypeDto, HttpServletRequest request){
        //Creamos transaccion para la operacion.
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        ProductTypeDto productTypeDtoResponse = productTypeBl.createProductType(productTypeDto, transaction);
        return productTypeDtoResponse;
    }

//TIENDA 1, Producto 1
    //Extrae informacion de pagina web y guarda los datos en BD.
    @RequestMapping(path ="/crawler1", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler1(@RequestBody ProductTypeDto productTypeDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección product 1
        String url="https://www.dismac.com.bo/o85pd.html";  //Pc1
        computerPageOne.extractProductType(url, productTypeDto,transaction);
    }

    //Producto 2
    //Extrae informacion de pagina web y guarda los datos en BD.
    @RequestMapping(path ="/crawler2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler2(@RequestBody ProductTypeDto productTypeDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección product 2
        String url="https://www.dismac.com.bo/3g573lt-abm.html";  //Pc2
        computerPageOne.extractProductType2(url, productTypeDto, transaction);
    }

    //Producto 3
    //Extrae informacion de pagina web y guarda los datos en BD.
    @RequestMapping(path ="/crawler3", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler3(@RequestBody ProductTypeDto productTypeDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección product 3
        String url="https://www.dismac.com.bo/82a2007lm.html";  //Pc3
        computerPageOne.extractProductType3(url, productTypeDto, transaction);
    }


//TIENDA 2,
    //Producto 1
    @RequestMapping(path ="/crawler4", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler4(@RequestBody ProductTypeDto productTypeDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección product 1
        String url="https://compucenter.store/product/2548-equipo-hp-laptop-348-g7";  //Pc1
        computerPageTwo.extractProductType(url, productTypeDto,transaction);
    }

    //Producto 2
    @RequestMapping(path ="/crawler5", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler5(@RequestBody ProductTypeDto productTypeDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección product 2
        String url="https://compucenter.store/product/2504-equipo-hp-laptop-14-dk1025wm";  //Pc2
        computerPageTwo.extractProductType2(url, productTypeDto,transaction);
    }

    //Producto 3
    @RequestMapping(path ="/crawler6", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler6(@RequestBody ProductTypeDto productTypeDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección product 3
        String url="https://compucenter.store/product/2520-equipo-hp-laptop-15-gw0007la";  //Pc3
        computerPageTwo.extractProductType3(url, productTypeDto,transaction);
    }



//TIENDA 3,
    //Producto 1
    @RequestMapping(path ="/crawler7", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler7(@RequestBody ProductTypeDto productTypeDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección product 1
        String url="https://www.multilaptops.net/store2/191";  //Pc1
        computerPageThree.extractProductType(url, productTypeDto,transaction);
    }

    //Producto 2
    @RequestMapping(path ="/crawler8", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler8(@RequestBody ProductTypeDto productTypeDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección product 2
        String url="https://www.multilaptops.net/store2/194";  //Pc2
        computerPageThree.extractProductType2(url, productTypeDto,transaction);
    }

    //Producto 3
    @RequestMapping(path ="/crawler9", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler9(@RequestBody ProductTypeDto productTypeDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección product 3
        String url="https://www.multilaptops.net/store2/181";  //Pc3
        computerPageThree.extractProductType3(url, productTypeDto,transaction);
    }


}
