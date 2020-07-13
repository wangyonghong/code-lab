package me.yonghong.springboot.lab;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
public class HibernateMemTestApplication implements CommandLineRunner {

    @Autowired
    private ArticleService articleService;

    public static void main(String[] args) {
        SpringApplication.run(HibernateMemTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws InterruptedException {
        long sum = 1_000_000;
        for (long i = 1; i <= sum; i++) {
            Article article = new Article();
            article.setAuthorId(i);
            article.setTitle(RandomStringUtils.random(20, true, true));
            article.setContent(RandomStringUtils.random(800, true, true));
            article.setCustomUrl(RandomStringUtils.random(20, true, true));
            article.setReads(RandomUtils.nextLong());
            article.setLikes(RandomUtils.nextLong());
            articleService.save(article);
            if (i % 1000 == 0) {
                log.info("save i = {}", i);
            }
        }
        for (long i = 1; i <= sum; i++) {
            List<Long> ids = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                ids.add(RandomUtils.nextLong(1, sum));
            }
            articleService.findAllById(ids);
            if (i % 100 == 0) {
                log.info("query i = {}", i);
                Thread.sleep(1000);
            }
            if (i == sum) {
                i = 1;
            }
        }
    }
}
