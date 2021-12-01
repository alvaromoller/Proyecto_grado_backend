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


    @Autowired
    public ProductTypeApi (ProductTypeBl productTypeBl, TransactionBl transactionBl, ComputerPageOne computerPageOne){
        this.productTypeBl = productTypeBl;
        this.transactionBl = transactionBl;
        this.computerPageOne = computerPageOne;
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

    //Extrae informacion de pagina web y guarda los datos en BD.
    @RequestMapping(path ="/crawler", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void webCrawler(HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        String url="https://www.intecsa.com.bo/product/dell-latitude-3520-core-i5-2/";  //Pc1
        computerPageOne.extractProductType(url, transaction);
    }



}
