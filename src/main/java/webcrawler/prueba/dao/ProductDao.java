package webcrawler.prueba.dao;

import org.apache.ibatis.annotations.Mapper;
import webcrawler.prueba.model.Product;

import java.util.List;

@Mapper
public interface ProductDao {

    public List<Product> getProducts();
}