package org.example;

import com.github.chengyuxing.sql.Baki;
import com.github.chengyuxing.sql.spring.autoconfigure.mapping.XQLMapperScan;
import org.example.mappers.RabbitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@XQLMapperScan
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    Baki baki;

    @Autowired
    RabbitMapper rabbitMapper;

    @GetMapping("/hello")
    String hello() {
        var res = rabbitMapper.findRabbit();
        return res.getString(0) + ": Hello " + res.getString(1) + " mapper!";
    }

    @GetMapping("/world")
    String world() {
        return baki.query("&rabbit.findRabbit")
                .findFirst()
                .map(d -> d.getString(0) + ": Hello " + d.getString(1) + " baki!")
                .orElse("");
    }
}
