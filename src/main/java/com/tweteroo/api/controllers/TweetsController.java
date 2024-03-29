package com.tweteroo.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dtos.TweetDTO;
import com.tweteroo.api.models.TweetModel;
import com.tweteroo.api.services.TweetsService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/tweets")
public class TweetsController {
    final TweetsService tweetsService;

    TweetsController(TweetsService tweetsService) {
        this.tweetsService = tweetsService;
    }

    @PostMapping
    public ResponseEntity<Object> createTweet(@RequestBody @Valid TweetDTO body) {
        Optional<TweetModel> tweet = tweetsService.save(body);

        if (!tweet.isPresent()) {
            return ResponseEntity.status(400).body("User not found!");
        }

        return ResponseEntity.status(201).body(tweet);
    }

    @GetMapping
    public ResponseEntity<List<TweetModel>> getTweets() {
        List<TweetModel> tweets = tweetsService.findAll();
        return ResponseEntity.status(200).body(tweets);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getTweetsByUserId(@PathVariable Long userId) {
        Optional<List<TweetModel>> tweets = tweetsService.findByUserId(userId);

        if (!tweets.isPresent()) {
            return ResponseEntity.status(400).body("User not found!");
        }

        return ResponseEntity.status(200).body(tweets);
    }

}
