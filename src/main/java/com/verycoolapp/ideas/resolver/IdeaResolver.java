package com.verycoolapp.ideas.resolver;

import com.verycoolapp.ideas.dto.IdeasDto;
import com.verycoolapp.ideas.dto.Response;
import com.verycoolapp.ideas.service.IdeaService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@GraphQLApi
@RequiredArgsConstructor
public class IdeaResolver {
    private final IdeaService ideaService;

    @GraphQLMutation(name = "saveIdea")
    public Optional<Response> saveIdea(@GraphQLArgument(name = "Idea") IdeasDto ideasDto) {
        return ideaService.saveAndUpdateIdeas(ideasDto);
    }

    @GraphQLMutation(name = "deleteIdea")
    public Optional<Response> deleteIdea(@GraphQLArgument(name = "ideaId") String ideaId) {
        return ideaService.deleteByIdeaId(ideaId);
    }

    @GraphQLQuery(name = "ideaById")
    public Optional<Response> ideaById(@GraphQLArgument(name = "Idea") String ideasDto) {
        return ideaService.getIdeaByIdeaId(ideasDto);
    }

    @GraphQLQuery(name = "allIdeas")
    public Optional<Response> allIdeas() {
        return ideaService.getAllIdeas();
    }

}
