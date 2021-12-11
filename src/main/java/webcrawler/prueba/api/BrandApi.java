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
import webcrawler.prueba.webCrawler.ComputerPageTwo;

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
    private ComputerPageTwo computerPageTwo;


    @Autowired
    public BrandApi (BrandBl brandBl,  ComputerPageOne computerPageOne,ComputerPageTwo computerPageTwo,TransactionBl transactionBl){
        this.brandBl = brandBl;
        this.computerPageOne = computerPageOne;
        this.computerPageTwo = computerPageTwo;
        this.transactionBl = transactionBl;
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

//TIENDA 1, producto 1
    //Extrae informacion de pagina web y guarda los datos en BD.
    @RequestMapping(path ="/crawler1", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler1(@RequestBody BrandDto brandDto, HttpServletRequest request)throws IOException{
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección marca 1
        String url="https://www.dismac.com.bo/o85pd.html";  //Pc1
        computerPageOne.extractBrand(url, brandDto,transaction);
    }

    //producto 2
    @RequestMapping(path ="/crawler2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler2(@RequestBody BrandDto brandDto, HttpServletRequest request)throws IOException{
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección marca 2
        String url="https://www.dismac.com.bo/3g573lt-abm.html";  //Pc2
        computerPageOne.extractBrand2(url, brandDto, transaction);
    }

    //producto 3 HP
    @RequestMapping(path ="/crawler3", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler3(@RequestBody BrandDto brandDto, HttpServletRequest request)throws IOException{
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección marca 3
        String url="https://www.dismac.com.bo/82a2007lm.html";  //Pc3
        computerPageOne.extractBrand3(url, brandDto, transaction);
    }

//TIENDA 2,
    //producto 1
    @RequestMapping(path ="/crawler4", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler4(@RequestBody BrandDto brandDto, HttpServletRequest request)throws IOException{
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección marca 1
        String url="https://compucenter.store/product/2548-equipo-hp-laptop-348-g7";  //Pc1
        computerPageTwo.extractBrand(url, brandDto, transaction);
    }

    //producto 2
    @RequestMapping(path ="/crawler5", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler5(@RequestBody BrandDto brandDto, HttpServletRequest request)throws IOException{
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección marca 2
        String url="https://compucenter.store/product/1658-equipo-dell-laptop-latitude-5420";  //Pc2
        computerPageTwo.extractBrand2(url, brandDto, transaction);
    }

    //producto 3
    @RequestMapping(path ="/crawler6", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crawler6(@RequestBody BrandDto brandDto, HttpServletRequest request)throws IOException{
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        //dirección marca 3
        String url="https://compucenter.store/product/1671-equipo-lenovo-laptop-yoga-720-12ikb";  //Pc3
        computerPageTwo.extractBrand3(url, brandDto, transaction);
    }


}
