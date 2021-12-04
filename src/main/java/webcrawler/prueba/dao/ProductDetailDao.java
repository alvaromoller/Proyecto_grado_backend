package webcrawler.prueba.dao;

import org.apache.ibatis.annotations.Mapper;
import webcrawler.prueba.model.ProductDetail;

import java.util.List;

@Mapper
public interface ProductDetailDao {
    public List<ProductDetail> getProductDetail();
    public ProductDetail findDetailById(Integer productDetailId);
    public void create(ProductDetail productDetail);
}
