package webcrawler.prueba.dto;

import java.util.ArrayList;
import java.util.List;

public class ShopDto {

    private Integer shopId;
    private String name;
    private String description;
    private String location;
    private List<String> location2 = new ArrayList<String>();
    private  String img;

    public ShopDto(){
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    //List

    public List<String> getLocation2() {
        return location2;
    }

    public void setLocation2(List<String> location2) {
        this.location2 = location2;
    }
    //List end

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "ShopDto{" +
                "shopId=" + shopId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", location2='" + location2 + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
