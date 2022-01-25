package webcrawler.prueba.webCrawler;

public class Proceso2 extends Thread {

    @Override
    public void run(){
        for (int i=0; i < 5; i++){
            System.out.println("Proceso 2");
        }

    }


}
