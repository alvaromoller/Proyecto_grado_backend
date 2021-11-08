package webcrawler.prueba.api;
import org.springframework.beans.factory.annotation.Autowired;
import webcrawler.prueba.bl.ProductBl;
import webcrawler.prueba.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/v1/product")
public class ProductApi {

    private ProductBl productBl;

    @Autowired
    public ProductApi (ProductBl productBl){
        this.productBl = productBl;
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
}
