package io.github.arthurpbelato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class IntroducaoSpringApplication {

    @GetMapping("/ola")
    public String olaMundo(){
        return "Olá, mundo!";
    }

    @GetMapping("/terra")
    public String terra(){
        return "A terra é redonda!!";
    }

    public static void main(String[] args) {
        SpringApplication.run(IntroducaoSpringApplication.class, args);
    }
}
