package webcrawler.prueba.dto;

public class ProductDetailDto {
    private Integer productDetailId;
    private Double price;
    private String quantity;
    private Integer productId;

    public Integer getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(Integer productDetailId) {
        this.productDetailId = productDetailId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductDetailDto{" +
                "productDetailId=" + productDetailId +
                ", price=" + price +
                ", quantity=" + quantity + '\'' +
                ", productId=" + productId +
                '}';
    }
}
