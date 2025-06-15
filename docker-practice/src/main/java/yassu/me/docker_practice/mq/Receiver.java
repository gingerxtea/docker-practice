package yassu.me.docker_practice.mq;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final CountDownLatch latch = new CountDownLatch(1);

    public void receiveMassage(String user){
        logger.info("message received from queue: {}", user);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    public CountDownLatch getLatch() {
		return latch;
	}
}
