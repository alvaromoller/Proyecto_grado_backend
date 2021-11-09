package webcrawler.prueba.dao;

import org.apache.ibatis.annotations.Mapper;
import webcrawler.prueba.model.ProductType;

import java.util.List;


@Mapper
public interface ProductTypeDao {

    public List<ProductType> getProductTypes();
    public ProductType findProductTypeById(Integer productTypeid);
}
