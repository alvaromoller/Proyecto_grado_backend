package webcrawler.prueba.webSocket.model;

public class HelloMessage {

    private String name;


    public HelloMessage(){}

    public HelloMessage(String name){
        this.name = name;
    }

    //Get and set
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
