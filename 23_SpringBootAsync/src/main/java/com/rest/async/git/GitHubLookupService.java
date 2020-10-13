package com.rest.async.git;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class GitHubLookupService {
    private static final Logger logger = LoggerFactory.getLogger(GitHubLookupService.class);

    @Autowired
    RestTemplate restTemplate;

    // Spring’s @Async annotation, indicating that it should run on a separate thread.
    @Async
    public CompletableFuture<User> findUser(String user) throws InterruptedException {
        logger.info("Looking up " + user);
        String url = String.format("https://api.github.com/users/%s", user);
        //  convert the answer into a User object.
        User results = restTemplate.getForObject(url, User.class);
        // deliberate delay
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(results);
    }
}
