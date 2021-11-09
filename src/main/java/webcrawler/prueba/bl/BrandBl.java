package webcrawler.prueba.bl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.BrandDao;
import webcrawler.prueba.dto.BrandDto;
import webcrawler.prueba.model.Brand;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandBl {

    private BrandDao brandDao;

    @Autowired
    public BrandBl (BrandDao brandDao){
        this.brandDao = brandDao;
    }

    //Listado de marca
    public List<BrandDto> selectBrands(){
        List<Brand> brands = brandDao.getBrands();
        List<BrandDto> brandDtos = new ArrayList<BrandDto>();

        for (int i=0; i < brands.size(); i++){
            Brand brand = brands.get(i);
            BrandDto brandDto = new BrandDto();
            brandDto.setBrandId(brand.getBrandId());
            brandDto.setName(brand.getName());

            brandDtos.add(i, brandDto);
        }
        return  brandDtos;
    }

    //encontrar marca por ID
    public BrandDto findBrandById(Integer brandId){
        Brand brand = brandDao.findBrandById(brandId);
        BrandDto brandDto = new BrandDto();

        brandDto.setBrandId(brand.getBrandId());
        brandDto.setName(brand.getName());
        return  brandDto;
    }

}
