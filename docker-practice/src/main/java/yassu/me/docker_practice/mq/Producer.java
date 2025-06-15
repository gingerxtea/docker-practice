package yassu.me.docker_practice.mq;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    Logger logger = LoggerFactory.getLogger(getClass());

    public final String exchange = "default";
    public final String routingKey = "a.b.c";
    
    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Producer(
        RabbitTemplate rabbitTemplate,
        Receiver receiver
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
    }

    public ResponseEntity<String> sendMessage(String user){
        logger.info("sending message . . .");
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, user);
            // receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
            return new ResponseEntity<>("Successfully sent the message", HttpStatus.OK); 
        } catch (AmqpException e) {
            logger.error("exception occurred at sending message: {}", e.getMessage());
            return new ResponseEntity<>("please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
