package webcrawler.prueba.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Shop {
    private Integer shopId;
    private String name;
    private String description;
    private String location;
    private List<String> location2 = new ArrayList<String>();
    private String img;
    private Integer status;

    private Transaction transaction;

    public Shop(){
        this.transaction= new Transaction();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    //transaction
    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Integer getTxId() {
        return transaction.getTxId();
    }

    public void setTxId(Integer txId) {
        this.transaction.setTxId(txId);
    }

    public String getTxHost() {
        return transaction.getTxHost();
    }

    public void setTxHost(String txHost) {
        this.transaction.setTxHost(txHost);
    }

    public Integer getTxUserId() {
        return transaction.getTxUserId();
    }

    public void setTxUserId(Integer txUserId) {
        this.transaction.setTxUserId(txUserId);
    }

    public Date getTxDate() {
        return transaction.getTxDate();
    }

    public void setTxDate(Date txDate) {
        this.transaction.setTxDate(txDate);
    }

    //fin transaction


    @Override
    public String toString() {
        return "Shop{" +
                "shopId=" + shopId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", location2='" + location2 + '\'' +
                ", img='" + img + '\'' +
                ", status=" + status +
                ", txId=" + transaction.getTxId() +
                ", txHost='" + transaction.getTxHost() + '\'' +
                ", txUserId=" + transaction.getTxUserId() +
                ", txDate=" + transaction.getTxDate() +
                '}';
    }
}
