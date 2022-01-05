package webcrawler.prueba.bl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.ProductDao;
import webcrawler.prueba.dao.TransactionDao;
import webcrawler.prueba.dto.ProductDto;
import webcrawler.prueba.model.Product;
import webcrawler.prueba.model.Transaction;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductBl {

    private ProductDao productDao;
    private TransactionDao transactionDao;

    @Autowired
    public ProductBl(ProductDao productDao, TransactionDao transactionDao){
        this.productDao = productDao;
        this.transactionDao = transactionDao;
    }

    //listado de productos
    public List<ProductDto> selectProducts(){
        List<Product> products = productDao.getProducts();      //productos, se crea un for para recorrer products
        List<ProductDto> productDtos = new ArrayList<ProductDto>(); // se crea productDtos para tener el listado de products

        for(int i=0; i < products.size(); i++){
            Product product = products.get(i);
            ProductDto productDto = new ProductDto();
            productDto.setProductId(product.getProductId());
            productDto.setName(product.getName());
            productDto.setName2(product.getName2());
            productDto.setDescription(product.getDescription());
            productDto.setImg(product.getImg());
            productDto.setPrice(product.getPrice());
            //llaves foraneas
            productDto.setBrandId(product.getBrandId());
            productDto.setShopId(product.getShopId());
            productDto.setProductTypeId(product.getProductTypeId());

            productDtos.add(i, productDto);
        }
        return  productDtos;
    }

    //listado de productos por categoria, JOIN de tabla product con productCategory
    public List<ProductDto> selectProductsByCategory(Integer categoryId){
        List<Product> products = productDao.getProductListByCategory(categoryId);      //productos, se crea un for para recorrer products
        List<ProductDto> productDtos = new ArrayList<ProductDto>();      //se crea productDtos para tener el listado de products

        for(int i=0; i < products.size(); i++){
            Product product = products.get(i);
            ProductDto productDto = new ProductDto();
            productDto.setProductId(product.getProductId());
            productDto.setName(product.getName());
            productDto.setName2(product.getName2());
            productDto.setDescription(product.getDescription());
            productDto.setImg(product.getImg());
            productDto.setPrice(product.getPrice());
            //llaves foraneas
            productDto.setBrandId(product.getBrandId());
            productDto.setShopId(product.getShopId());
            productDto.setProductTypeId(product.getProductTypeId());

            productDtos.add(i, productDto);
        }
        return  productDtos;
    }




    //Encontrar producto por ID
    public ProductDto findProductById(Integer productId){
        Product product = productDao.findByProductId(productId);
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setName(product.getName());
        productDto.setName2(product.getName2());
        productDto.setDescription(product.getDescription());
        productDto.setImg(product.getImg());
        productDto.setPrice(product.getPrice());
        //llaves foraneas
        productDto.setBrandId(product.getBrandId());
        productDto.setShopId(product.getShopId());
        productDto.setProductTypeId(product.getProductTypeId());
        return productDto;
    }

    //Crear producto
    public ProductDto createProduct(ProductDto productDto, Transaction transaction){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setName2(productDto.getName2());
        product.setDescription(productDto.getDescription());
        product.setImg(productDto.getImg());
        product.setPrice(productDto.getPrice());
        //transaction
        product.setTxId(transaction.getTxId());
        product.setTxHost(transaction.getTxHost());
        product.setTxUserId(transaction.getTxUserId());
        product.setTxDate(transaction.getTxDate());
        product.setStatus(1);
        //create
        productDao.create(product);
        Integer getLastId = transactionDao.getLastInsertId();
        productDto.setProductId(getLastId);
        return  productDto;
    }

    //Actualizacion de productos
    public ProductDto updateShop(ProductDto productDto, Transaction transaction){
        Product product= new Product();
        product.setProductId(productDto.getProductId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setImg(productDto.getImg());
        product.setPrice(productDto.getPrice());
        //transaction
        product.setTxId(transaction.getTxId());
        product.setTxUserId(transaction.getTxUserId());
        product.setTxHost(transaction.getTxHost());
        product.setTxDate(transaction.getTxDate());
        product.setStatus(1);
        productDao.update(product);
        return productDto;
    }


}
