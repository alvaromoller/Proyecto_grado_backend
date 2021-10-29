package webcrawler.prueba.dto;

public class BrandDto {

    private Integer brandId;
    private String name;

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BrandDto{" +
                "brandId=" + brandId +
                ", name='" + name + '\'' +
                '}';
    }
}
