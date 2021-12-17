package webcrawler.prueba.dao;

import org.apache.ibatis.annotations.Mapper;
import webcrawler.prueba.model.Category;

import java.util.List;

@Mapper
public interface CategoryDao {

    public List<Category> getCategories();
    public Category findCategoryById(Integer categoryId);
    public void create(Category category);
}
