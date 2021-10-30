package webcrawler.prueba.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import webcrawler.prueba.bl.ShopBl;
import webcrawler.prueba.dto.ShopDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

}
