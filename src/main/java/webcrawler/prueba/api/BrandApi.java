package webcrawler.prueba.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import webcrawler.prueba.bl.BrandBl;
import webcrawler.prueba.bl.TransactionBl;
import webcrawler.prueba.dto.BrandDto;
import webcrawler.prueba.model.Transaction;
import webcrawler.prueba.util.TransactionUtil;
import webcrawler.prueba.webCrawler.ComputerPageOne;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/v1/brand")
public class BrandApi {
    private BrandBl brandBl;
    private TransactionBl transactionBl;

    private ComputerPageOne computerPageOne;

    @Autowired
    public BrandApi (BrandBl brandBl, TransactionBl transactionBl, ComputerPageOne computerPageOne){
        this.brandBl = brandBl;
        this.transactionBl = transactionBl;
        this.computerPageOne = computerPageOne;
    }

    //lista de marca
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BrandDto> selectBrands(HttpServletRequest request){
        return brandBl.selectBrands();
    }

    //encontrar por ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BrandDto findById(@PathVariable("id") Integer id, HttpServletRequest request){
        return brandBl.findBrandById(id);
    }

    //Crear Marca
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BrandDto createBrand(@RequestBody BrandDto brandDto, HttpServletRequest request){
        //Creamos transaccion para la operacion.
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        BrandDto brandDtoResponse = brandBl.createBrand(brandDto, transaction);
        return brandDtoResponse;
    }

    //producto 1
    //Extrae informacion de pagina web y guarda los datos en BD.
    @RequestMapping(path ="/crawler1", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler1(HttpServletRequest request)throws IOException{
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 1
        String url="https://www.intecsa.com.bo/product/dell-latitude-3520-core-i5-2/";  //Pc1
        computerPageOne.extractBrand(url, transaction);
    }

    //producto 2
    @RequestMapping(path ="/crawler2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler2(HttpServletRequest request)throws IOException{
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 2
        String url="https://www.intecsa.com.bo/product/dell-nb-inspiron-5502-silver-core-i7/";  //Pc2
        computerPageOne.extractBrand2(url, transaction);
    }

    //producto 3 HP
    @RequestMapping(path ="/crawler3", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler3(HttpServletRequest request)throws IOException{
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección producto 3
        String url="https://www.intecsa.com.bo/product/hp-nb-15-dw2034la/";  //Pc3
        computerPageOne.extractBrand3(url, transaction);
    }


}
