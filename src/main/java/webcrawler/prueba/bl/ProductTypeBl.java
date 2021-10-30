package webcrawler.prueba.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.ProductTypeDao;
import webcrawler.prueba.dto.ProductTypeDto;
import webcrawler.prueba.model.ProductType;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductTypeBl {
    private ProductTypeDao productTypeDao;

    @Autowired
    public ProductTypeBl (ProductTypeDao productTypeDao){
        this.productTypeDao = productTypeDao;
    }

    //listado de tipo de productos
    public List<ProductTypeDto> selectProductTypes(){
        List<ProductType> productTypes = productTypeDao.getProductTypes();
        List<ProductTypeDto> productTypeDtos = new ArrayList<ProductTypeDto>();

        for (int i=0; i < productTypes.size(); i++){
            ProductType productType = productTypes.get(i);
            ProductTypeDto productTypeDto = new ProductTypeDto();
            productTypeDto.setProductTypeId(productType.getProductTypeId());
            productTypeDto.setName(productType.getName());

            productTypeDtos.add(i, productTypeDto);
        }
        return productTypeDtos;
    }

}
