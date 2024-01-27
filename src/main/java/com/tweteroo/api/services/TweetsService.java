package com.tweteroo.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tweteroo.api.dtos.TweetDTO;
import com.tweteroo.api.models.TweetModel;
import com.tweteroo.api.models.UserModel;
import com.tweteroo.api.repositories.TweetsRepository;
import com.tweteroo.api.repositories.UsersRepository;

@Service
public class TweetsService {
    final TweetsRepository tweetsRepository;
    final UsersRepository usersRepository;

    TweetsService(TweetsRepository tweetsRepository, UsersRepository usersRepository) {
        this.tweetsRepository = tweetsRepository;
        this.usersRepository = usersRepository;
    }

    public Optional<TweetModel> save(TweetDTO dto) {
        Optional<UserModel> user = usersRepository.findById(dto.getUserId());

        if (!user.isPresent()) {
            return Optional.empty();
        }

        TweetModel tweet = new TweetModel(dto, user.get());
        return Optional.of(tweetsRepository.save(tweet));
    }

    public List<TweetModel> findAll() {
        return tweetsRepository.findAll();
    }
}
