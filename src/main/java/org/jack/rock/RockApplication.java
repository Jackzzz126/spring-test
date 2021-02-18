package org.jack.rock;

import lombok.extern.slf4j.Slf4j;
import org.jack.rock.util.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Slf4j
@ServletComponentScan
@EnableCaching
public class RockApplication {

    public static void main(String[] args) {
        SpringApplication.run(RockApplication.class, args);
    }

    //@Bean
    //public RestTemplate restTemplate(RestTemplateBuilder builder) {
    //    return builder.build();
    //}

    //@Bean
    //public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
    //    return args ->
    //    {
    //        Quote quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
    //        log.info(quote.toString());
    //    };
    //}

    @Bean
    public CommandLineRunner initUploadDir(StorageService storageService) throws Exception {
        return args ->
        {
            //storageService.deleteAll();
            storageService.init();
        };
    }
}
