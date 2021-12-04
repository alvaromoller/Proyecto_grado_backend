package webcrawler.prueba.dto;

public class ProductDetailDto {
    private Integer productDetailId;
    private Number price;
    private Integer quantity;
    private Integer productId;

    public Integer getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(Integer productDetailId) {
        this.productDetailId = productDetailId;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
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
                ", quantity=" + quantity +
                ", productId=" + productId +
                '}';
    }
}
