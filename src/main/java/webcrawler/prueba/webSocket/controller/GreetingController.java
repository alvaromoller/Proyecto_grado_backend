package webcrawler.prueba.webSocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import webcrawler.prueba.dto.ProductDto;
import webcrawler.prueba.webCrawler.ComputerPageOne;
import webcrawler.prueba.webSocket.model.Greeting;
import webcrawler.prueba.webSocket.model.HelloMessage;

import java.util.List;

@Controller
public class GreetingController {


    //llamar al metodo run() para la actualizacion en el frontend
    //@MessageMapping("/helloProducts")
    @SendTo("/topic/products")
    public void updateProducts()throws Exception{
        System.out.println("Hilo de updateProducts");
        Thread.sleep(60000);
/**
        ComputerPageOne hilo1 = new ComputerPageOne(
                "https://compucenter.store/product/2502-equipo-asus-laptop-f512j-vivobook",
                "https://compucenter.store/product/2283-equipo-dell-laptop-inspiron-5593",
                "https://compucenter.store/product/2530-equipo-hp-laptop-15-dy1003ca",
                "https://compucenter.store/product/2520-equipo-hp-laptop-15-gw0007la",
                "https://compucenter.store/product/2548-equipo-hp-laptop-348-g7",
                "https://compucenter.store/product/2504-equipo-hp-laptop-14-dk1025wm" );
        hilo1.start();
 */
    }




    //La  anotación @MessageMapping  garantiza que si se envía un mensaje al destino "/hello",   se llama al método greeting()
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception{
        Thread.sleep(1000);
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }


}
