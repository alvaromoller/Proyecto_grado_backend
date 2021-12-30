package webcrawler.prueba.dao;

import org.apache.ibatis.annotations.Mapper;
import webcrawler.prueba.model.Product;

import java.util.List;

@Mapper
public interface ProductDao {

    public List<Product> getProducts();
    public Product findByProductId(Integer productId);
    public void create(Product product);
    public void update(Product product);

    //Listado de JOIN de tabla product con productCategory
    //para saber y obtener cuantos productos tiene una categoria
    public List<Product> getProductListByCategory(Integer categoryId);
}
