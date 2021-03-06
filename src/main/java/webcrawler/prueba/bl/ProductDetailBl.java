package webcrawler.prueba.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.ProductDetailDao;
import webcrawler.prueba.dao.TransactionDao;
import webcrawler.prueba.dto.ProductDetailDto;
import webcrawler.prueba.model.ProductDetail;
import webcrawler.prueba.model.Transaction;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDetailBl {

    private ProductDetailDao productDetailDao;
    private TransactionDao transactionDao;

    @Autowired
    public ProductDetailBl(ProductDetailDao productDetailDao, TransactionDao transactionDao){
        this.productDetailDao = productDetailDao;
        this.transactionDao = transactionDao;
    }

    //listado de ProductDetail
    public List<ProductDetailDto> selectProductDetail(){
        List<ProductDetail> productDetails = productDetailDao.getProductDetail();  //productDetails, se crea un for para recorrer products
        List<ProductDetailDto> productDetailDtos = new ArrayList<ProductDetailDto>(); //se crea productDetailDtos para tener el listado de products

        for(int i=0; i < productDetails.size(); i++){
            ProductDetail productDetail = productDetails.get(i);
            ProductDetailDto productDetailDto = new ProductDetailDto();

            productDetailDto.setProductDetailId(productDetail.getProductDetailId());
            productDetailDto.setDetail(productDetail.getDetail());
            productDetailDto.setQuantity(productDetail.getQuantity());
            //llaves foraneas
            productDetailDto.setProductId(productDetail.getProductId());

            productDetailDtos.add(i, productDetailDto);
        }
            return  productDetailDtos;
    }

    //Encontrar producto por ID
    public ProductDetailDto findDetailById(Integer productDetailId){
        ProductDetail productDetail = productDetailDao.findDetailById(productDetailId);
        ProductDetailDto productDetailDto = new ProductDetailDto();

        productDetailDto.setProductDetailId(productDetail.getProductDetailId());
        productDetailDto.setDetail(productDetail.getDetail());
        productDetailDto.setQuantity(productDetail.getQuantity());
        //llave foranea
        productDetailDto.setProductId(productDetail.getProductId());
        return productDetailDto;
    }

    //Crear producto
    public ProductDetailDto createDetail(ProductDetailDto productDetailDto, Transaction transaction){
        ProductDetail productDetail = new ProductDetail();
        productDetail.setDetail(productDetailDto.getDetail());
        productDetail.setQuantity(productDetailDto.getQuantity());
        //transaction
        productDetail.setTxId(transaction.getTxId());
        productDetail.setTxHost(transaction.getTxHost());
        productDetail.setTxUserId(transaction.getTxUserId());
        productDetail.setTxDate(transaction.getTxDate());
        productDetail.setStatus(1);
        //create
        productDetailDao.create(productDetail);
        Integer getLastId = transactionDao.getLastInsertId();
        productDetailDto.setProductDetailId(getLastId);
        return  productDetailDto;
    }

    //Actualizacion de productos
    public ProductDetailDto updateShop(ProductDetailDto productDetailDto, Transaction transaction){
        ProductDetail productDetail= new ProductDetail();
        productDetail.setProductDetailId(productDetailDto.getProductDetailId());
        productDetail.setDetail(productDetailDto.getDetail());
        productDetail.setQuantity(productDetailDto.getQuantity());
        //transaction
        productDetail.setTxId(transaction.getTxId());
        productDetail.setTxUserId(transaction.getTxUserId());
        productDetail.setTxHost(transaction.getTxHost());
        productDetail.setTxDate(transaction.getTxDate());
        productDetail.setStatus(1);
        productDetailDao.update(productDetail);
        return productDetailDto;
    }

}
