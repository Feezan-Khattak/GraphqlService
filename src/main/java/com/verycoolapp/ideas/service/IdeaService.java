package com.verycoolapp.ideas.service;

import com.verycoolapp.ideas.dto.IdeasDto;
import com.verycoolapp.ideas.entities.Ideas;
import com.verycoolapp.ideas.repo.IdeaRepo;
import com.verycoolapp.ideas.dto.Response;
import com.verycoolapp.ideas.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class IdeaService {
    private final IdeaRepo ideaRepo;
    private final ModelMapper modelMapper;
    private final ResponseUtil responseUtil;

    public Optional<Response> saveAndUpdateIdeas(IdeasDto ideasDto) {
        Response response;
        Ideas idea;
        try {
            Optional<Ideas> _idea = ideaRepo.findByIdeaId(ideasDto.getIdeaId());
            idea = _idea.map(ideas -> updateIdea(ideasDto, ideas)).orElseGet(() -> saveNewIdea(ideasDto));
            Type type = new TypeToken<IdeasDto>() {
            }.getType();
            IdeasDto mappedIdeas = modelMapper.map(idea, type);
            response = responseUtil.generateSuccessResponse(mappedIdeas);

        } catch (Exception er) {
            log.info("Failed to save and update the Idea: {}", er.getMessage());
            response = responseUtil.generateFailureResponse("Failed to save and update the Idea");
        }
        return Optional.of(response);
    }

    private Ideas updateIdea(IdeasDto ideasDto, Ideas _ideas) {
        log.info("Idea Already present with id: {}, updating existing record...", ideasDto.getIdeaId());
        ideasDto.setId(_ideas.getId());
        ideasDto.setIdeaId(_ideas.getIdeaId());
        ideasDto.setUpdatedAt(new Date(System.currentTimeMillis()));
        return ideaRepo.save(modelMapper.map(ideasDto, Ideas.class));
    }

    private Ideas saveNewIdea(IdeasDto ideasDto) {
        log.info("Adding new Idea....");
        ideasDto.setIdeaId(UUID.randomUUID().toString());
        ideasDto.setCreatedAt(new Date(System.currentTimeMillis()));
        return ideaRepo.save(modelMapper.map(ideasDto, Ideas.class));
    }

    public Optional<Response> getIdeaByIdeaId(String ideaId) {
        Response response;
        Optional<Ideas> _idea = ideaRepo.findByIdeaId(ideaId);
        if (_idea.isPresent()) {
            Type type = new TypeToken<IdeasDto>() {
            }.getType();
            IdeasDto mappedIdeas = modelMapper.map(_idea, type);
            response = responseUtil.generateSuccessResponse(mappedIdeas);
        } else {
            response = responseUtil.generateFailureResponse("Idea Not found using ID: " + ideaId);
        }
        return Optional.of(response);
    }

    public Optional<Response> getAllIdeas() {
        Response response;
        List<Ideas> _all = ideaRepo.findAll();
        if (!_all.isEmpty()) {
            Type type = new TypeToken<List<IdeasDto>>() {
            }.getType();
            List<IdeasDto> mappedIdeas = modelMapper.map(_all, type);
            response = responseUtil.generateSuccessResponse(mappedIdeas);

        } else {
            response = responseUtil.generateFailureResponse("No Idea found in the database, please insert some ideas first");
        }
        return Optional.of(response);
    }

    public Optional<Response> deleteByIdeaId(String ideaId) {
        Response response;
        log.info("Delete idea using Id: {}", ideaId);
        Optional<Ideas> _idea = ideaRepo.findByIdeaId(ideaId);
        if (_idea.isPresent()) {
            ideaRepo.deleteById(_idea.get().getId());
            response = responseUtil.generateSuccessResponse("Deleted");
        } else {
            response = responseUtil.generateFailureResponse("Failed to delete idea with ID: " + ideaId + " not found");
        }
        return Optional.of(response);
    }
}
