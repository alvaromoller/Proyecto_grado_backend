package webcrawler.prueba.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import webcrawler.prueba.bl.ProductDetailBl;
import webcrawler.prueba.bl.TransactionBl;
import webcrawler.prueba.dto.ProductDetailDto;
import webcrawler.prueba.model.Transaction;
import webcrawler.prueba.util.TransactionUtil;
import webcrawler.prueba.webCrawler.ComputerPageOne;
import webcrawler.prueba.webCrawler.ComputerPageTwo;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/v1/productDetail")
public class ProductDetailApi {

    private ProductDetailBl productDetailBl;
    private TransactionBl transactionBl;
    private ComputerPageOne computerPageOne;
    private ComputerPageTwo computerPageTwo;


    @Autowired
    public ProductDetailApi (ProductDetailBl productDetailBl,ComputerPageOne computerPageOne, ComputerPageTwo computerPageTwo, TransactionBl transactionBl){
        this.productDetailBl = productDetailBl;
        this.computerPageOne = computerPageOne;
        this.computerPageTwo = computerPageTwo;
         this.transactionBl = transactionBl;
    }



    //listado de productos
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDetailDto> selectProductDetail(HttpServletRequest request){
        return productDetailBl.selectProductDetail();
    }

    // encontrar producto por ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDetailDto findDetailById(@PathVariable("id") Integer id, HttpServletRequest request){
        return productDetailBl.findDetailById(id);
    }

    //Crear producto
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDetailDto createProductDetail(@RequestBody ProductDetailDto productDetailDto, HttpServletRequest request){
        //Creamos transaccion para la operacion.
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        ProductDetailDto productDtoResponse = productDetailBl.createDetail(productDetailDto, transaction);
        return productDtoResponse;
    }

//TIENDA 1 DISMAC,
//producto 1
    @RequestMapping(path ="/crawler1", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler1(@RequestBody ProductDetailDto productDetailDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección  1
        String url="https://www.dismac.com.bo/o85pd.html";  //Pc1
        computerPageOne.extractDetail(url, productDetailDto, transaction);
    }

//producto 2
    @RequestMapping(path ="/crawler2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler2(@RequestBody ProductDetailDto productDetailDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección  2
        String url="https://www.dismac.com.bo/3g573lt-abm.html";  //Pc2
        computerPageOne.extractDetail2(url, productDetailDto, transaction);
    }


//Producto 3
    @RequestMapping(path ="/crawler3", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler3(@RequestBody ProductDetailDto productDetailDto, HttpServletRequest request)throws IOException {
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección  3
        String url="https://www.dismac.com.bo/82a2007lm.html";  //Pc3
        computerPageOne.extractDetail3(url, productDetailDto, transaction);
    }

}
