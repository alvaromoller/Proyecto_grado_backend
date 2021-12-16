package webcrawler.prueba.dto;

public class ProductDetailDto {
    private Integer productDetailId;
    private String detail;
    private String quantity;
    private Integer productId;

    public Integer getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(Integer productDetailId) {
        this.productDetailId = productDetailId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
                ", detail=" + detail + '\'' +
                ", quantity=" + quantity + '\'' +
                ", productId=" + productId +
                '}';
    }
}
