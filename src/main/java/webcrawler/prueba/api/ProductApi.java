package webcrawler.prueba.api;
import org.springframework.beans.factory.annotation.Autowired;
import webcrawler.prueba.bl.ProductBl;
import webcrawler.prueba.bl.TransactionBl;
import webcrawler.prueba.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;
import webcrawler.prueba.model.Transaction;
import webcrawler.prueba.util.TransactionUtil;
import webcrawler.prueba.webCrawler.ComputerPageOne;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/v1/product")
public class ProductApi {

    private ProductBl productBl;
    private TransactionBl transactionBl;
    private ComputerPageOne computerPageOne;


    @Autowired
    public ProductApi (ProductBl productBl, TransactionBl transactionBl, ComputerPageOne computerPageOne){
        this.productBl = productBl;
        this.transactionBl = transactionBl;
        this.computerPageOne = computerPageOne;
    }

    //listado de productos
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> selectProducts(HttpServletRequest request){
        return productBl.selectProducts();
    }

    // encontrar producto por ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto findById(@PathVariable("id") Integer id, HttpServletRequest request){
        return productBl.findProductById(id);
    }

    //Crear producto
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto createBrand(@RequestBody ProductDto productDto, HttpServletRequest request){
        //Creamos transaccion para la operacion.
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        ProductDto productDtoResponse = productBl.createProduct(productDto, transaction);
        return productDtoResponse;
    }

    //Extrae informacion de pagina web y guarda los datos en BD.
    @RequestMapping(path ="/crawler", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void webCrawler(HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        String url="https://www.intecsa.com.bo/product/dell-latitude-3520-core-i5-2/";  //PC1
        computerPageOne.extractProduct(url, transaction);
    }

    //url1
    //https://www.intecsa.com.bo/product/dell-latitude-3520-core-i5-2/
    //url2
    //https://www.intecsa.com.bo/product/dell-nb-inspiron-5502-silver-core-i7/
}
