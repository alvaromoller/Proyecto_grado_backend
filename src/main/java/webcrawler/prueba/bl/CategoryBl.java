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

//CATEGORIAS sin base de datos
    //Categoria 1
    public List<CategoryDto> selectCategories(){
        List<CategoryDto> categoryDtos = new ArrayList<CategoryDto>(); // para GUARDAR el listado de Category
        CategoryDto categoryDto = new CategoryDto();         //para acceder a los setCategoryId...

        categoryDto.setCategoryId(1);       //3 categorias
        categoryDto.setCategoryName("Para el hogar");
        categoryDto.setImg("data:image/jpeg;base64,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");

        categoryDtos.add(categoryDto);

        //System.out.println("Categoria1: "+ categoryDtos);
        return  categoryDtos;
    }

    //Categoria 2
    public List<CategoryDto> selectCategories2(){
        List<CategoryDto> categoryDtos = new ArrayList<CategoryDto>(); // para GUARDAR el listado de Category
        CategoryDto categoryDto = new CategoryDto();         //para acceder a los setCategoryId...

        categoryDto.setCategoryId(2);       //3 categorias
        categoryDto.setCategoryName(" Para Gamers");
        categoryDto.setImg("data:image/jpeg;base64,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");
        categoryDtos.add(categoryDto);

        //System.out.println("Categoria2: "+ categoryDtos);
        return  categoryDtos;
    }

    //Categoria 3
    public List<CategoryDto> selectCategories3(){
        List<CategoryDto> categoryDtos = new ArrayList<CategoryDto>(); // para GUARDAR el listado de Category
        CategoryDto categoryDto = new CategoryDto();         //para acceder a los setCategoryId...

        categoryDto.setCategoryId(3);       //3 categorias
        categoryDto.setCategoryName(" Para el trabajo ");
        categoryDto.setImg("https://us.123rf.com/450wm/mitay20/mitay201301/mitay20130100167/17483879-computadora-port%C3%A1til-que-muestra-una-hoja-de-c%C3%A1lculo-con-algunos-gr%C3%A1ficos-3d-sobre-ella.jpg?ver=6");
        categoryDtos.add(categoryDto);

        //System.out.println("Categoria3: "+ categoryDtos);
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

    //Categoria por ID de categoryListAll
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
//FIN





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
