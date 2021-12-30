package webcrawler.prueba.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.CategoryDao;
import webcrawler.prueba.dao.TransactionDao;
import webcrawler.prueba.dto.CategoryDto;
import webcrawler.prueba.model.Category;
import webcrawler.prueba.model.Transaction;

import java.io.IOException;
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

//CATEGORIAS
    //Categoria 1
    public List<CategoryDto> selectCategories(){
        List<CategoryDto> categoryDtos = new ArrayList<CategoryDto>(); // para GUARDAR el listado de Category
        CategoryDto categoryDto = new CategoryDto();         //para acceder a los setCategoryId...

        categoryDto.setCategoryId(1);       //3 categorias
        categoryDto.setCategoryName("Categoria 1");

        categoryDtos.add(categoryDto);
        System.out.println("Categoria1: "+ categoryDtos);
        return  categoryDtos;
    }

    //Categoria 2
    public List<CategoryDto> selectCategories2(){
        List<CategoryDto> categoryDtos = new ArrayList<CategoryDto>(); // para GUARDAR el listado de Category
        CategoryDto categoryDto = new CategoryDto();         //para acceder a los setCategoryId...

        categoryDto.setCategoryId(2);       //3 categorias
        categoryDto.setCategoryName("Categoria 2");

        categoryDtos.add(categoryDto);
        System.out.println("Categoria2: "+ categoryDtos);
        return  categoryDtos;
    }

    //Categoria 3
    public List<CategoryDto> selectCategories3(){
        List<CategoryDto> categoryDtos = new ArrayList<CategoryDto>(); // para GUARDAR el listado de Category
        CategoryDto categoryDto = new CategoryDto();         //para acceder a los setCategoryId...

        categoryDto.setCategoryId(3);       //3 categorias
        categoryDto.setCategoryName("Categoria 3");

        categoryDtos.add(categoryDto);
        System.out.println("Categoria3: "+ categoryDtos);
        return  categoryDtos;
    }

    //Lista de las 3 categorias
    public List<CategoryDto> categoryListAll() {
        List<CategoryDto> categoryDtos = new ArrayList<CategoryDto>(); //se crea productDtos para tener el listado de products
        List<CategoryDto> categoryDtos2 = new ArrayList<CategoryDto>();
        List<CategoryDto> categoryDtos3 = new ArrayList<CategoryDto>();

        //categoria 1
        categoryDtos = selectCategories();
        //System.out.println(" categoria 1: " + categoryDtos);

        //categoria 2
        categoryDtos2 = selectCategories2();
        //System.out.println(" categoria 2: " + categoryDtos2);

        //categoria 3
        categoryDtos3 = selectCategories3();
        //System.out.println(" categoria 3: " + categoryDtos3);

        List<CategoryDto> categoryAll = new ArrayList<CategoryDto>(); // Lista para guardar todos los productos
        categoryAll.addAll(categoryDtos);
        categoryAll.addAll(categoryDtos2);
        categoryAll.addAll(categoryDtos3);
        //System.out.println("categoryAll: " + categoryAll);
        return  categoryAll;
    }

//FIN

    //encontrar categoria por ID
    public CategoryDto findCategoryById(Integer categoryId){
        //Category category = categoryDao.findCategoryById(categoryId);
        List<CategoryDto> categoryDtosFor = categoryListAll(); //se crea para el for y para llamar al metodo categoryListAll
        CategoryDto categoryAux = new CategoryDto();                          // para el return, para guardar el listado final

        for(int i=0; i < categoryDtosFor.size(); i++) {
            CategoryDto category = categoryDtosFor.get(i);  //guardar el recorrido del for
            CategoryDto categoryDto = new CategoryDto();

            if(category.getCategoryId() == categoryId) {    //listado: category.getCategoryId() ==  parametro introducido: Integer categoryId
                categoryDto.setCategoryId(category.getCategoryId());
                categoryDto.setCategoryName(category.getCategoryName());
                categoryAux = categoryDto;
            }//if
        }//for
        return  categoryAux;
    }

    //Crear categoria
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
