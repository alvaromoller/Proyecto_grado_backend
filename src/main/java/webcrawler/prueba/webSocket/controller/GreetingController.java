package webcrawler.prueba.webSocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import webcrawler.prueba.webSocket.model.Greeting;
import webcrawler.prueba.webSocket.model.HelloMessage;

@Controller
public class GreetingController {

    //La  anotación @MessageMapping  garantiza que si se envía un mensaje al destino "/hello",   se llama al método greeting()

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception{
        Thread.sleep(1000);
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }


}
