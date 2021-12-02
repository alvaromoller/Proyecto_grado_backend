package webcrawler.prueba.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.ShopDao;
import webcrawler.prueba.dao.TransactionDao;
import webcrawler.prueba.dto.ShopDto;
import webcrawler.prueba.model.Shop;
import webcrawler.prueba.model.Transaction;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopBl {
    private ShopDao shopDao;
    private TransactionDao transactionDao;


    @Autowired
    public ShopBl (ShopDao shopDao, TransactionDao transactionDao){
        this.shopDao = shopDao;
        this.transactionDao = transactionDao;
    }

    //listado de Tiendas
    public List<ShopDto> selectShops(){
        List<Shop> shops = shopDao.getShops();
        List<ShopDto> shopDtos = new ArrayList<ShopDto>();

        for(int i=0; i < shops.size(); i++){
            Shop shop = shops.get(i);
            ShopDto shopDto = new ShopDto();
            shopDto.setShopId(shop.getShopId());
            shopDto.setName(shop.getName());
            shopDto.setDescription(shop.getDescription());
            shopDto.setLocation(shop.getLocation());
            shopDto.setImg(shop.getImg());

            shopDtos.add(i, shopDto);
        }
        return shopDtos;
    }

    public ShopDto findShopById(Integer shopId){
        Shop shop = shopDao.findShopById(shopId);
        ShopDto shopDto = new ShopDto();

        shopDto.setShopId(shop.getShopId());
        shopDto.setName(shop.getName());
        shopDto.setDescription(shop.getDescription());
        shopDto.setLocation(shop.getLocation());
        shopDto.setImg(shop.getImg());
        return  shopDto;
    }

    //Crear marca
    public ShopDto createShop(ShopDto shopDto, Transaction transaction){
        Shop shop = new Shop();
        shop.setName(shopDto.getName());
        shop.setDescription(shopDto.getDescription());
        shop.setLocation(shopDto.getLocation());
        shop.setImg(shopDto.getImg());
        //transaction
        shop.setTxId(transaction.getTxId());
        shop.setTxHost(transaction.getTxHost());
        shop.setTxUserId(transaction.getTxUserId());
        shop.setTxDate(transaction.getTxDate());
        shop.setStatus(1);
        //create
        shopDao.create(shop);
        Integer getLastId = transactionDao.getLastInsertId();
        shopDto.setShopId(getLastId);
        return  shopDto;
    }


}
