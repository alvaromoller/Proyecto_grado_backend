package webcrawler.prueba.dao;

import org.apache.ibatis.annotations.Mapper;
import webcrawler.prueba.model.Shop;

import java.util.List;


@Mapper
public interface ShopDao {

    public List<Shop> getShops();
    public Shop findShopById(Integer shopId);
    public void create(Shop shop);
}
