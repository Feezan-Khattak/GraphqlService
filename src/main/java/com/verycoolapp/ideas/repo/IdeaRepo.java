package com.verycoolapp.ideas.repo;

import com.verycoolapp.ideas.entities.Ideas;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdeaRepo extends MongoRepository<Ideas, String> {
    Optional<Ideas> findByIdeaId(String ideaId);

    void deleteByIdeaId(String ideaId);
}
