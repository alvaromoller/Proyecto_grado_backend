package webcrawler.prueba.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import webcrawler.prueba.bl.ProductTypeBl;
import webcrawler.prueba.dto.ProductTypeDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/v1/productType")
public class ProductTypeApi {

    private ProductTypeBl productTypeBl;

    @Autowired
    public ProductTypeApi (ProductTypeBl productTypeBl){
        this.productTypeBl = productTypeBl;
    }

    //listado de tipos de productos
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductTypeDto> selectProductTypes(){
        return productTypeBl.selectProductTypes();
    }

    //metodo para encontrar productType por ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductTypeDto findById(@PathVariable("id") Integer id, HttpServletRequest request){
        return productTypeBl.findProductTypeById(id);
    }


}
