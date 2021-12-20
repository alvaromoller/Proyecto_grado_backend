package webcrawler.prueba.bl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.CategoryDao;
import webcrawler.prueba.dao.ProductCategoryDao;
import webcrawler.prueba.dao.ProductDao;
import webcrawler.prueba.dao.TransactionDao;
import webcrawler.prueba.dto.ProductCategoryDto;
import webcrawler.prueba.model.Category;
import webcrawler.prueba.model.Product;
import webcrawler.prueba.model.ProductCategory;
import webcrawler.prueba.model.Transaction;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryBl {

    private ProductCategoryDao productCategoryDao;
    private TransactionDao transactionDao;

    private ProductDao productDao;
    private CategoryDao categoryDao;

    @Autowired
    public ProductCategoryBl (ProductCategoryDao productCategoryDao, ProductDao productDao, CategoryDao categoryDao, TransactionDao transactionDao){
        this.productCategoryDao = productCategoryDao;
        this.productDao = productDao;
        this.categoryDao = categoryDao;
        this.transactionDao = transactionDao;
    }

    //Listado de
    public List<ProductCategoryDto> selectProductCategory(){
        List<ProductCategory> productCategories = productCategoryDao.getProductCategory();
        List<ProductCategoryDto> productCategoryDtos = new ArrayList<ProductCategoryDto>();

        for (int i=0; i < productCategories.size(); i++){
            ProductCategory pc = productCategories.get(i);
            ProductCategoryDto pcDto = new ProductCategoryDto();

            pcDto.setProductCategoryId(pc.getProductCategoryId());  //ID de ProductCategory
            pcDto.setProductId(pc.getProductId());                  //ID de Product
            pcDto.setCategoryId(pc.getCategoryId());                //ID de Category

            productCategoryDtos.add(i, pcDto);                      //Lista de productCategoryDtos
        }
        return  productCategoryDtos;
    }

    //encontrar ProductCategory por ID
    public ProductCategoryDto findProductCategoryById(Integer productCategoryId){
        ProductCategory pc = productCategoryDao.findProductCategoryById(productCategoryId);
        ProductCategoryDto pcDto = new ProductCategoryDto();

        pcDto.setProductCategoryId(pc.getProductCategoryId());  //ID de ProductCategory
        pcDto.setProductId(pc.getProductId());                  //ID de Product
        pcDto.setCategoryId(pc.getCategoryId());                //ID de Category
        return  pcDto;
    }

    //Crear ProductCategory
    public ProductCategoryDto createProductCategory(ProductCategoryDto pcDto, Transaction transaction){
        //Datos ProductCategory
        ProductCategory pc = new ProductCategory();            // ProductCategory
        pc.setProductId(pcDto.getProductId());    //ID de Product
        pc.setCategoryId(pcDto.getCategoryId());  ////ID de Category
        //transaction
        pc.setTxId(transaction.getTxId());
        pc.setTxHost(transaction.getTxHost());
        pc.setTxUserId(transaction.getTxUserId());
        pc.setTxDate(transaction.getTxDate());
        pc.setStatus(1);
        //create
        productCategoryDao.create(pc);
        Integer getLastId = transactionDao.getLastInsertId();
        pcDto.setProductCategoryId(getLastId);

//crear product y category AQui????
        //Datos Product
/**
        Product product = new Product();
        product.setName(pcDto.getName());
        product.setDescription(pcDto.getDescription());
        product.setImg(pcDto.getImg());
        product.setPrice(pcDto.getPrice());
        //transaction
        product.setTxId(transaction.getTxId());
        product.setTxHost(transaction.getTxHost());
        product.setTxUserId(transaction.getTxUserId());
        product.setTxDate(transaction.getTxDate());
        product.setStatus(1);
        //create
        productDao.create(product);

        //Datos Categoria
        Category category = new Category();
        category.setCategoryName(pcDto.getCategoryName());
        //transaction
        category.setTxId(transaction.getTxId());
        category.setTxHost(transaction.getTxHost());
        category.setTxUserId(transaction.getTxUserId());
        category.setTxDate(transaction.getTxDate());
        category.setStatus(1);
        //create
  */
        return  pcDto;                             //create ProductCategory, return ProductCategoryDto = pcDto

    }




}
