package com.rest.async.git;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

// https://spring.io/guides/gs/async-method/
@Component
public class GithubRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(GithubRunner.class);

    @Autowired
    GitHubLookupService gitHubLookupService;

    @Override
    public void run(String... args) throws Exception {
        long start = System.currentTimeMillis();
        CompletableFuture<User> page1 = gitHubLookupService.findUser("PivotalSoftware");

        CompletableFuture<User> page2 = gitHubLookupService.findUser("impressiveHen");

        CompletableFuture<User> page3 = gitHubLookupService.findUser("CloudFoundry");

        CompletableFuture.allOf(page1, page2, page3);
        logger.info("Elapsed time: " + (System.currentTimeMillis()-start));
        logger.info("page1: " + page1.get());
        logger.info("page2: " + page2.get());
        logger.info("page3: " + page3.get());
    }
}
