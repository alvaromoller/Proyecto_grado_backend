package webcrawler.prueba.bl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.BrandDao;
import webcrawler.prueba.dto.BrandDto;

import java.util.List;

@Service
public class BrandBl {

    private BrandDao brandDao;

    @Autowired
    public BrandBl (BrandDao brandDao){
        this.brandDao = brandDao;
    }

    //Listado de marca
    public List<BrandDto>
}
