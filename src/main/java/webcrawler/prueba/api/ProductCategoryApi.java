package webcrawler.prueba.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import webcrawler.prueba.bl.ProductCategoryBl;
import webcrawler.prueba.bl.TransactionBl;
import webcrawler.prueba.dto.ProductCategoryDto;
import webcrawler.prueba.model.Transaction;
import webcrawler.prueba.util.TransactionUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/v1/productCategory")
public class ProductCategoryApi {

    private ProductCategoryBl productCategoryBl;
    private TransactionBl transactionBl;

    @Autowired
    public ProductCategoryApi (ProductCategoryBl productCategoryBl, TransactionBl transactionBl){
        this.productCategoryBl = productCategoryBl;
        this.transactionBl = transactionBl;
    }

    //lista de ProductCategory
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductCategoryDto> selectProductCategory(HttpServletRequest request){
        return productCategoryBl.selectProductCategory();
    }

    //encontrar por ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductCategoryDto findById(@PathVariable("id") Integer id, HttpServletRequest request){
        return productCategoryBl.findProductCategoryById(id);
    }

    //Crear ProductCategory
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductCategoryDto createProductCategory(@RequestBody ProductCategoryDto productCategoryDto, HttpServletRequest request){
        //Creamos transaccion para la operacion.
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        ProductCategoryDto pcDtoResponse = productCategoryBl.createProductCategory(productCategoryDto, transaction);
        return pcDtoResponse;
    }



}
