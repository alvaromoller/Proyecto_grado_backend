package webcrawler.prueba.bl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.ProductDao;
import webcrawler.prueba.dto.ProductDto;
import webcrawler.prueba.model.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductBl {

    private ProductDao productDao;

    @Autowired
    public ProductBl(ProductDao productDao){
        this.productDao = productDao;
    }

    //listado de productos
    public List<ProductDto> selectProducts(){
        List<Product> products = productDao.getProducts();
        List<ProductDto> productDtos = new ArrayList<ProductDto>();

        for(int i=0; i < products.size(); i++){
            Product product = products.get(i);
            ProductDto productDto = new ProductDto();
            productDto.setProductId(product.getProductId());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setImg(product.getImg());
            //llaves foraneas
            productDto.setBrandId(product.getBrandId());
            productDto.setShopId(product.getShopId());
            productDto.setProductTypeId(product.getProductTypeId());

            productDtos.add(i, productDto);
        }
        return  productDtos;
    }


}
