package webcrawler.prueba.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.ProductTypeDao;
import webcrawler.prueba.dao.TransactionDao;
import webcrawler.prueba.dto.ProductTypeDto;
import webcrawler.prueba.model.ProductType;
import webcrawler.prueba.model.Transaction;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductTypeBl {
    private ProductTypeDao productTypeDao;
    private TransactionDao transactionDao;

    @Autowired
    public ProductTypeBl (ProductTypeDao productTypeDao, TransactionDao transactionDao){
        this.productTypeDao = productTypeDao;
        this.transactionDao = transactionDao;
    }

    //listado de tipo de productos
    public List<ProductTypeDto> selectProductTypes(){
        List<ProductType> productTypes = productTypeDao.getProductTypes();
        List<ProductTypeDto> productTypeDtos = new ArrayList<ProductTypeDto>();

        for (int i=0; i < productTypes.size(); i++){
            ProductType productType = productTypes.get(i);
            ProductTypeDto productTypeDto = new ProductTypeDto();
            productTypeDto.setProductTypeId(productType.getProductTypeId());
            productTypeDto.setName(productType.getName());

            productTypeDtos.add(i, productTypeDto);
        }
        return productTypeDtos;
    }

    //encontrando ProductType por ID
    public ProductTypeDto findProductTypeById(Integer productTypeId){
        ProductType productType = productTypeDao.findProductTypeById(productTypeId);
        ProductTypeDto productTypeDto = new ProductTypeDto();

        productTypeDto.setProductTypeId(productType.getProductTypeId());
        productTypeDto.setName(productType.getName());
        return productTypeDto;
    }

    //Crear ProductType
    public ProductTypeDto createProductType(ProductTypeDto productTypeDto, Transaction transaction){
        ProductType productType = new ProductType();
        productType.setName(productTypeDto.getName());
        productType.setTxId(transaction.getTxId());
        productType.setTxHost(transaction.getTxHost());
        productType.setTxUserId(transaction.getTxUserId());
        productType.setTxDate(transaction.getTxDate());
        productType.setStatus(1);
        //create
        productTypeDao.create(productType);
        Integer getLastId = transactionDao.getLastInsertId();
        productTypeDto.setProductTypeId(getLastId);
        return  productTypeDto;
    }

}
