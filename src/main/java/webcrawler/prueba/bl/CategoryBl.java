package webcrawler.prueba.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.CategoryDao;
import webcrawler.prueba.dao.TransactionDao;
import webcrawler.prueba.dto.CategoryDto;
import webcrawler.prueba.model.Category;
import webcrawler.prueba.model.Transaction;

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
            categoryDto.setCategoryName(category.getCategoryName());

            categoryDtos.add(i, categoryDto);
        }
        return  categoryDtos;
    }

    //encontrar marca por ID
    public CategoryDto findCategoryById(Integer categoryId){
        Category category = categoryDao.findCategoryById(categoryId);
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryName(category.getCategoryName());
        return  categoryDto;
    }

    //Crear marca
    public CategoryDto createCategory(CategoryDto categoryDto, Transaction transaction){
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        category.setTxId(transaction.getTxId());
        category.setTxHost(transaction.getTxHost());
        category.setTxUserId(transaction.getTxUserId());
        category.setTxDate(transaction.getTxDate());
        category.setStatus(1);
        //create
        categoryDao.create(category);
        Integer getLastId = transactionDao.getLastInsertId();
        categoryDto.setCategoryId(getLastId);
        return  categoryDto;
    }


}
