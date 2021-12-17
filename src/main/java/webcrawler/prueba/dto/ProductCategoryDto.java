package webcrawler.prueba.dto;

public class ProductCategoryDto {

    private Integer productCategoryId;  //tabla NxN
    private Integer productId;
    private Integer categoryId;

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ProductCategoryDto{" +
                "productCategoryId=" + productCategoryId +
                ", productId=" + productId +
                ", categoryId=" + categoryId +
                '}';
    }
}
