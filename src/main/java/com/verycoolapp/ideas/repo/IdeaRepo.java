package com.verycoolapp.ideas.repo;

import com.verycoolapp.ideas.entities.Ideas;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface IdeaRepo extends MongoRepository<Ideas, Long> {
    Optional<Ideas> findByIdeaId(String ideaId);

    void deleteByIdeaId(String ideaId);
}
