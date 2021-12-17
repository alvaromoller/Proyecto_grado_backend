package webcrawler.prueba.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.CategoryDao;
import webcrawler.prueba.dao.TransactionDao;
import webcrawler.prueba.dto.CategoryDto;
import webcrawler.prueba.model.Category;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryBl {

    private CategoryDao categoryDao;
    private TransactionDao transactionDao;

    @Autowired
    public CategoryBl (CategoryDao categoryDao, TransactionDao transactionDao){
        this.categoryDao = categoryDao;
        this.transactionDao = transactionDao;
    }

    //Listado de marca
    public List<CategoryDto> selectCategories(){
        List<Category> categories = categoryDao.getCategories();
        List<CategoryDto> categoryDtos = new ArrayList<CategoryDto>();

        for (int i=0; i < categories.size(); i++){
            Category category = categories.get(i);
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setCategoryId(category.getCategoryId());
            categoryDto.setName(category.getName());

            categoryDtos.add(i, categoryDto);
        }
        return  categoryDtos;
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
        //create
        brandDao.create(brand);
        Integer getLastId = transactionDao.getLastInsertId();
        brandDto.setBrandId(getLastId);
        return  brandDto;
    }


}
