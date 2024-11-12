package org.example;

import com.github.chengyuxing.common.DataRow;
import com.github.chengyuxing.sql.Baki;
import com.github.chengyuxing.sql.spring.autoconfigure.Tx;
import com.github.chengyuxing.sql.spring.autoconfigure.mapping.XQLMapperScan;
import org.example.entity.Rabbit;
import org.example.mappers.RabbitMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
@XQLMapperScan
public class App implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    public App(Baki baki, RabbitMapper rabbitMapper, Tx tx) {
        this.baki = baki;
        this.rabbitMapper = rabbitMapper;
        this.tx = tx;
    }

    final Baki baki;
    final RabbitMapper rabbitMapper;
    final Tx tx;

    @GetMapping("/hello")
    String hello() {
        var rabbit = rabbitMapper.findRabbit(5);
        return rabbit.getBirthday() + ": Hello " + rabbit.getName() + " mapper!";
    }

    @GetMapping("/world")
    String world() {
        try (Stream<DataRow> s = baki.query("&rabbit.findRabbit").args("id", 3).stream()) {
            return s.findFirst()
                    .map(d -> d.getString(0) + ": Hello " + d.getString(1) + " baki!")
                    .orElse("");
        }
    }

    @GetMapping("/rabbits")
    List<Rabbit> rabbits() {
        return baki.query(Rabbit.class)
                .where(w -> w.gt(Rabbit::getId, 3).lt(Rabbit::getId, 8))
                .orderBy(o -> o.desc(Rabbit::getAge))
                .toList();
    }

    @Override
    public void run(String... args) throws Exception {
        baki.of("drop table if exists rabbit").execute();

        baki.of("&initdb.createTable").execute();

        List<Rabbit> rabbits = Stream.iterate(0, i -> i + 1)
                .limit(10)
                .map(i -> {
                    Rabbit rabbit = new Rabbit();
                    rabbit.setId(i);
                    rabbit.setName("rabbit" + i);
                    rabbit.setAge((int) (Math.random() * 90));
                    rabbit.setAddress("kunming");
                    return rabbit;
                }).toList();

        tx.using(() -> baki.insert(rabbits));
    }
}
