package webcrawler.prueba.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import webcrawler.prueba.bl.BrandBl;
import webcrawler.prueba.bl.TransactionBl;
import webcrawler.prueba.dto.BrandDto;
import webcrawler.prueba.model.Transaction;
import webcrawler.prueba.util.TransactionUtil;
import webcrawler.prueba.webCrawler.ParseComputerPage;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/v1/brand")
public class BrandApi {
    private BrandBl brandBl;
    private TransactionBl transactionBl;

    private ParseComputerPage parseComputerPage;

    @Autowired
    public BrandApi (BrandBl brandBl, TransactionBl transactionBl, ParseComputerPage parseComputerPage){
        this.brandBl = brandBl;
        this.transactionBl = transactionBl;
        this.parseComputerPage = parseComputerPage;
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

    // extrae informacion de pagina web y guarda los datos en BD.
    @RequestMapping(path ="/crawler", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void webCrawler(HttpServletRequest request)throws IOException{
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        String url="https://www.imdb.com/chart/top";
        parseComputerPage.listPages(url, transaction);
    }


}
