package webcrawler.prueba.dto;

import java.util.List;

public class ProductDto {
    //clase ProductoDto
    //sin status ni transaction
    private Integer productId;
    private String name;
    private String name2;
    private String description;
    private String description2;
    private String img;
    private List<String> img2;
    private String price;
    private String stock;    //disponible, agotado

    private String brand;       //encuesta, Marca
    private String ram;         //encuesta; ,memoria Ram
    private String processor;    //encuesta, procesador
    private String storage;     //encuesta, almacenamiento
    private String tarjetaGrafica;  //encuesta


    private Integer brandId;
    private Integer shopId;
    private String shopName;
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

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<String> getImg2() {
        return img2;
    }

    public void setImg2(List<String> img2) {
        this.img2 = img2;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    //encuesta
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getTarjetaGrafica() {
        return tarjetaGrafica;
    }

    public void setTarjetaGrafica(String tarjetaGrafica) {
        this.tarjetaGrafica = tarjetaGrafica;
    }

    //
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
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
                ", name2='" + name2 + '\'' +
                ", description='" + description + '\'' +
                ", description2='" + description2 + '\'' +
                ", img='" + img + '\'' +
                ", img2=" + img2 +
                ", price=" + price + '\'' +
                ", stock=" + stock + '\'' +
                ", brand=" + brand + '\'' +
                ", ram=" + ram + '\'' +
                ", processor=" + processor + '\'' +
                ", storage=" + storage + '\'' +
                ", tarjetaGrafica=" + tarjetaGrafica + '\'' +
                ", brandId=" + brandId +
                ", shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", productTypeId=" + productTypeId +
                ", categoryId=" + categoryId +
                '}';
    }


}
