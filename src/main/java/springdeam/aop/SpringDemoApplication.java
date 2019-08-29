package springdeam.aop;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringDemoApplication {


    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringDemoApplication.class);

        BuyService buyService = context.getBean(BuyService.class);
        buyService.buyItem(1);
        ChatService chatService = context.getBean(ChatService.class);
        chatService.chat(124);
    }

}
