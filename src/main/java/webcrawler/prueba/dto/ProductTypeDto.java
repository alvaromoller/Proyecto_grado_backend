package webcrawler.prueba.dto;

public class ProductTypeDto {
    private Integer productTypeId;
    private String name;

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductTypeDto{" +
                "productTypeId=" + productTypeId +
                ", name='" + name + '\'' +
                '}';
    }
}
