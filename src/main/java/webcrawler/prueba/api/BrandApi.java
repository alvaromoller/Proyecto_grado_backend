package webcrawler.prueba.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import webcrawler.prueba.bl.BrandBl;
import webcrawler.prueba.dto.BrandDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/brand")
public class BrandApi {
    private BrandBl brandBl;

    @Autowired
    public BrandApi (BrandBl brandBl){
        this.brandBl = brandBl;
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

}
