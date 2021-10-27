package webcrawler.prueba.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import webcrawler.prueba.bl.ProductBl;
import webcrawler.prueba.dto.ProductDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


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
}
