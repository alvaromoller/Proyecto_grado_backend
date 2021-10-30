package webcrawler.prueba.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import webcrawler.prueba.bl.ProductTypeBl;
import webcrawler.prueba.dto.ProductTypeDto;

import java.util.List;

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


}
