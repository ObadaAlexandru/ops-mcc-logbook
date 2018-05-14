package de.tum.moveii.ops.logbook;

import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootAmvnpplication
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .run(args);
    }
}
