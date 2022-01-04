package webcrawler.prueba.dto;

public class CategoryDto {

    private Integer categoryId;
    private Integer shopId; //llave foranea
    private Integer productId;
    private String categoryName;
    private String img;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "categoryId=" + categoryId +
                "shopId=" + shopId +
                "productId=" + productId +
                ", categoryName='" + categoryName + '\'' +
                ", img='" + img + '\'' +

                '}';
    }
}
