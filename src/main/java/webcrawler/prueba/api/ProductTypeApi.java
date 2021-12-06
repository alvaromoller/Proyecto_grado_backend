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



    @Autowired
    public ProductTypeApi (ProductTypeBl productTypeBl, ComputerPageOne computerPageOne, ComputerPageTwo computerPageTwo,TransactionBl transactionBl){
        this.productTypeBl = productTypeBl;
        this.computerPageOne = computerPageOne;
        this.computerPageTwo = computerPageTwo;
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
        String url="https://www.intecsa.com.bo/product/dell-latitude-3520-core-i5-2/";  //Pc1
        computerPageOne.extractProductType(url, productTypeDto,transaction);
    }

    //Producto 2
    //Extrae informacion de pagina web y guarda los datos en BD.
    @RequestMapping(path ="/crawler2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler2(@RequestBody ProductTypeDto productTypeDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección product 2
        String url="https://www.intecsa.com.bo/product/dell-nb-inspiron-5502-silver-core-i7/";  //Pc2
        computerPageOne.extractProductType2(url, productTypeDto, transaction);
    }

    //Producto 3
    //Extrae informacion de pagina web y guarda los datos en BD.
    @RequestMapping(path ="/crawler3", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler3(@RequestBody ProductTypeDto productTypeDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección product 3
        String url="https://www.intecsa.com.bo/product/hp-nb-15-dw2034la/";  //Pc3
        computerPageOne.extractProductType3(url, productTypeDto, transaction);
    }


//TIENDA 2, Producto 1
    @RequestMapping(path ="/crawler4", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler4(@RequestBody ProductTypeDto productTypeDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección product 1
        String url="https://www.dismac.com.bo/o85pd.html";  //Pc1
        computerPageTwo.extractProductType(url, productTypeDto,transaction);
    }


}
