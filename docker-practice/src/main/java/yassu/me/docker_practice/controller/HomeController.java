package yassu.me.docker_practice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import yassu.me.docker_practice.mq.Producer;

@RestController
public class HomeController {

    private final Producer producer;

    public HomeController(Producer producer){
        this.producer = producer;
    }
    @GetMapping("/")
    public String sayHello(){
        return "<h1>Hello World!</h1>";
    }

    @GetMapping("/jpn")
    public String sayHelloJpn(){
        return "<h1>おはようございます<h1>";
    }

    @PostMapping("/")
    public ResponseEntity<String> sendMessage(@RequestParam String user){
        return producer.sendMessage(user);
    }
}
