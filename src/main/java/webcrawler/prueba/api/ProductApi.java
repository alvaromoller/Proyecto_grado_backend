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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/v1/product")
public class ProductApi {

    private ProductBl productBl;
    private TransactionBl transactionBl;


    @Autowired
    public ProductApi (ProductBl productBl, TransactionBl transactionBl){
        this.productBl = productBl;
        this.transactionBl = transactionBl;
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

}
