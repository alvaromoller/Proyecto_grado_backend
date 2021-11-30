package webcrawler.prueba.dto;

public class ProductDto {
    //clase ProductoDto
    //sin status ni transaction
    private Integer productId;
    private String name;
    private String description;
    private String img;
    private String procesador;
    private String memoriaRam;
    private String discoAlmacenamiento;
    private String tarjetaVideo;
    private String pantalla;
    private Integer brandId;
    private Integer shopId;
    private Integer productTypeId;

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
    //

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public String getMemoriaRam() {
        return memoriaRam;
    }

    public void setMemoriaRam(String memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public String getDiscoAlmacenamiento() {
        return discoAlmacenamiento;
    }

    public void setDiscoAlmacenamiento(String discoAlmacenamiento) {
        this.discoAlmacenamiento = discoAlmacenamiento;
    }

    public String getTarjetaVideo() {
        return tarjetaVideo;
    }

    public void setTarjetaVideo(String tarjetaVideo) {
        this.tarjetaVideo = tarjetaVideo;
    }

    public String getPantalla() {
        return pantalla;
    }

    public void setPantalla(String pantalla) {
        this.pantalla = pantalla;
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

    @Override
    public String toString() {
        return "ProductDto{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", procesador='" + procesador + '\'' +
                ", memoriaRam='" + memoriaRam + '\'' +
                ", discoAlmacenamiento='" + discoAlmacenamiento + '\'' +
                ", tarjetaVideo='" + tarjetaVideo + '\'' +
                ", pantalla='" + pantalla + '\'' +
                ", brandId=" + brandId +
                ", shopId=" + shopId +
                ", productTypeId=" + productTypeId +
                '}';
    }
}
