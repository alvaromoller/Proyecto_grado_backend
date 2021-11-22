package webcrawler.prueba.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import webcrawler.prueba.bl.ShopBl;
import webcrawler.prueba.dto.ShopDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/v1/shop")
public class ShopApi {

    private ShopBl shopBl;

    @Autowired
    public ShopApi (ShopBl shopBl){
        this. shopBl = shopBl;
    }
    //listado de tiendas
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ShopDto> selectShops(HttpServletRequest request){
        return shopBl.selectShops();
    }

    //encontar shop ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ShopDto findById(@PathVariable("id") Integer id, HttpServletRequest request){
        return shopBl.findShopById(id);
    }

}
