package webcrawler.prueba.bl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.BrandDao;
import webcrawler.prueba.dao.TransactionDao;
import webcrawler.prueba.dto.BrandDto;
import webcrawler.prueba.model.Brand;
import webcrawler.prueba.model.Transaction;
import webcrawler.prueba.webCrawler.ParseComputerPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrandBl {

    private BrandDao brandDao;
    private TransactionDao transactionDao;

    @Autowired
    public BrandBl (BrandDao brandDao, TransactionDao transactionDao){
        this.brandDao = brandDao;
        this.transactionDao = transactionDao;
    }

    //Listado de marca
    public List<BrandDto> selectBrands(){
        List<Brand> brands = brandDao.getBrands();
        List<BrandDto> brandDtos = new ArrayList<BrandDto>();

        for (int i=0; i < brands.size(); i++){
            Brand brand = brands.get(i);
            BrandDto brandDto = new BrandDto();
            brandDto.setBrandId(brand.getBrandId());
            brandDto.setName(brand.getName());

            brandDtos.add(i, brandDto);
        }
        return  brandDtos;
    }

    //encontrar marca por ID
    public BrandDto findBrandById(Integer brandId){
        Brand brand = brandDao.findBrandById(brandId);
        BrandDto brandDto = new BrandDto();

        brandDto.setBrandId(brand.getBrandId());
        brandDto.setName(brand.getName());
        return  brandDto;
    }

    //Crear marca
    public BrandDto createBrand(BrandDto brandDto, Transaction transaction){
        Brand brand = new Brand();
        brand.setName(brandDto.getName());
        brand.setTxId(transaction.getTxId());
        brand.setTxHost(transaction.getTxHost());
        brand.setTxUserId(transaction.getTxUserId());
        brand.setTxDate(transaction.getTxDate());
        brand.setStatus(1);
        //
        brandDao.create(brand);
        Integer getLastId = transactionDao.getLastInsertId();
        brandDto.setBrandId(getLastId);
        return  brandDto;
    }

}
