package webcrawler.prueba.dao;

import org.apache.ibatis.annotations.Mapper;
import webcrawler.prueba.model.ProductCategory;

import java.util.List;

@Mapper
public interface ProductCategoryDao {

    public List<ProductCategory> getProductCategory();
    public ProductCategory findProductCategoryById(Integer productCategoryId);
    public void create(ProductCategory productCategory);

}
