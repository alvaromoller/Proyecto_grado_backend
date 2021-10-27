package webcrawler.prueba.dto;

public class BrandDto {

    private Integer BrandId;
    private String name;

    public Integer getBrandId() {
        return BrandId;
    }

    public void setBrandId(Integer brandId) {
        BrandId = brandId;
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
                "BrandId=" + BrandId +
                ", name='" + name + '\'' +
                '}';
    }
}
