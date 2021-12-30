package webcrawler.prueba.dto;

public class ProductDto {
    //clase ProductoDto
    //sin status ni transaction
    private Integer productId;
    private String name;
    private String description;
    private String img;
    private String price;
    private Integer brandId;
    private Integer shopId;
    private Integer productTypeId;
    private Integer categoryId;


    /** img
    public java.lang.String getImg() {
        return img;
    }

    public void setImg(java.lang.String img) {
        this.img = img;
    }
     */

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    //
    //

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    //categoryId

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    //

    @Override
    public String toString() {
        return "ProductDto{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", price=" + price + '\'' +
                ", brandId=" + brandId +
                ", shopId=" + shopId +
                ", productTypeId=" + productTypeId +
                ", categoryId=" + categoryId +
                '}';
    }
}
