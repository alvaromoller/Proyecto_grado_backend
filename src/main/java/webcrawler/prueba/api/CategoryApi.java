package webcrawler.prueba.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import webcrawler.prueba.bl.CategoryBl;
import webcrawler.prueba.bl.TransactionBl;
import webcrawler.prueba.dto.CategoryDto;
import webcrawler.prueba.model.Transaction;
import webcrawler.prueba.util.TransactionUtil;
import webcrawler.prueba.webCrawler.ComputerPageOne;
import webcrawler.prueba.webCrawler.ExtractStores;
import webcrawler.prueba.webCrawler.ComputerPageTwo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/v1/category")
public class CategoryApi {

    private CategoryBl categoryBl;
    private TransactionBl transactionBl;

    private ComputerPageOne computerPageOne;
    private ComputerPageTwo computerPageTwo;
    private ExtractStores extractStores;

    @Autowired
    public CategoryApi (CategoryBl categoryBl, ComputerPageOne computerPageOne, ComputerPageTwo computerPageTwo, ExtractStores extractStores, TransactionBl transactionBl){
        this.categoryBl = categoryBl;
        this.computerPageOne = computerPageOne;
        this.computerPageTwo = computerPageTwo;
        this.extractStores = extractStores;
        this.transactionBl = transactionBl;
    }

///////////////////////////
    //lista de categoria nuevo, sin base de datos
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDto> selectCategories(HttpServletRequest request){
        return categoryBl.categoryListAll();
    }

    //encontrar por ID nuevo, sin base de datos
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto findById(@PathVariable("id") Integer id, HttpServletRequest request){
        return categoryBl.findCategoryById(id);
    }
///////////////////////////



    //Crear categoria
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto createBrand(@RequestBody CategoryDto categoryDto, HttpServletRequest request){
        //Creamos transaccion para la operacion.
        Transaction transaction = TransactionUtil.createTransaction(request);
        transactionBl.createTransaction(transaction);
        CategoryDto categoryDtoResponse = categoryBl.createCategory(categoryDto, transaction);
        return categoryDtoResponse;
    }



}
