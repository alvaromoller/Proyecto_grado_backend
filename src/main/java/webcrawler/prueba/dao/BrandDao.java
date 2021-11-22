package webcrawler.prueba.dao;

import org.apache.ibatis.annotations.Mapper;
import webcrawler.prueba.model.Brand;

import java.util.List;

@Mapper
public interface BrandDao {

    public List<Brand> getBrands();
    public Brand findBrandById(Integer brandId);
    public void create(Brand brand);
}
