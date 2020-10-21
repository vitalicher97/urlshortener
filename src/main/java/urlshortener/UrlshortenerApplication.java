package urlshortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import urlshortener.service.IDConverterService;

@SpringBootApplication
public class UrlshortenerApplication {

    @Autowired
    private static IDConverterService idConverterService;

    public static void main(String[] args) {
        SpringApplication.run(UrlshortenerApplication.class, args);

    }

}
