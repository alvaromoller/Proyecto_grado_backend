package webcrawler.prueba.webCrawler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Proceso1 extends Thread {

    @Override
    public void run(){

        for (int i=0; i < 5; i++){
            System.out.println("Proceso 1, "+ i);
        }

    }



}
