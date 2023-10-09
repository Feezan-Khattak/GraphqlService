package com.verycoolapp.ideas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.verycoolapp.ideas.dto.IdeasDto;
import com.verycoolapp.ideas.dto.Response;
import com.verycoolapp.ideas.entities.Ideas;
import com.verycoolapp.ideas.repo.IdeaRepo;
import com.verycoolapp.ideas.util.ResponseUtil;

import java.lang.reflect.Type;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ContextConfiguration(classes = {IdeaService.class})
@ExtendWith(SpringExtension.class)
class IdeaServiceTest {

    @MockBean
    private IdeaRepo ideaRepo;

    @Autowired
    private IdeaService ideaService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private ResponseUtil responseUtil;

    /**
     * Method under test: {@link IdeaService#saveAndUpdateIdeas(IdeasDto)}
     */
    @Test
    void testSaveAndUpdateIdeas() {
        Ideas ideas = new Ideas();
        ideas.setComment("Comment");
        ideas.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        ideas.setDescription("The characteristics of someone or something");
        ideas.setId("42");
        ideas.setIdeaId("42");
        ideas.setImage("Image");
        ideas.setLikes(1L);
        ideas.setName("Name");
        ideas.setTag("Tag");
        ideas.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Ideas ideas2 = new Ideas();
        ideas2.setComment("Comment");
        ideas2.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        ideas2.setDescription("The characteristics of someone or something");
        ideas2.setId("42");
        ideas2.setIdeaId("42");
        ideas2.setImage("Image");
        ideas2.setLikes(1L);
        ideas2.setName("Name");
        ideas2.setTag("Tag");
        ideas2.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        Optional<Ideas> ofResult = Optional.of(ideas2);
        when(ideaRepo.save(Mockito.<Ideas>any())).thenReturn(ideas);
        when(ideaRepo.findByIdeaId(Mockito.<String>any())).thenReturn(ofResult);

        Ideas ideas3 = new Ideas();
        ideas3.setComment("Comment");
        ideas3.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        ideas3.setDescription("The characteristics of someone or something");
        ideas3.setId("42");
        ideas3.setIdeaId("42");
        ideas3.setImage("Image");
        ideas3.setLikes(1L);
        ideas3.setName("Name");
        ideas3.setTag("Tag");
        ideas3.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        IdeasDto ideasDto = new IdeasDto();
        ideasDto.setComment("Idea Already present with id: {}, updating existing record...");
        ideasDto.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        ideasDto.setDescription("The characteristics of someone or something");
        ideasDto.setId("42");
        ideasDto.setIdeaId("42");
        ideasDto.setImage("Idea Already present with id: {}, updating existing record...");
        ideasDto.setLikes(1L);
        ideasDto.setName("Idea Already present with id: {}, updating existing record...");
        ideasDto.setTag("Idea Already present with id: {}, updating existing record...");
        ideasDto.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Ideas>>any())).thenReturn(ideas3);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Type>any())).thenReturn(ideasDto);
        Response.ResponseBuilder builderResult = Response.builder();
        when(responseUtil.generateSuccessResponse(Mockito.<Object>any()))
                .thenReturn(builderResult.data(new ArrayList<>()).error("An error occurred").status("Status").build());

        IdeasDto ideasDto2 = new IdeasDto();
        ideasDto2.setComment("Comment");
        ideasDto2.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        ideasDto2.setDescription("The characteristics of someone or something");
        ideasDto2.setId("42");
        ideasDto2.setIdeaId("42");
        ideasDto2.setImage("Image");
        ideasDto2.setLikes(1L);
        ideasDto2.setName("Name");
        ideasDto2.setTag("Tag");
        ideasDto2.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        assertTrue(ideaService.saveAndUpdateIdeas(ideasDto2).isPresent());
        verify(ideaRepo).save(Mockito.<Ideas>any());
        verify(ideaRepo).findByIdeaId(Mockito.<String>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Ideas>>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Type>any());
        verify(responseUtil).generateSuccessResponse(Mockito.<Object>any());
        assertEquals("42", ideasDto2.getIdeaId());
        assertEquals("42", ideasDto2.getId());
    }

    /**
     * Method under test: {@link IdeaService#getIdeaByIdeaId(String)}
     */
    @Test
    void testGetIdeaByIdeaId() {
        Optional<Ideas> emptyResult = Optional.empty();
        when(ideaRepo.findByIdeaId(Mockito.<String>any())).thenReturn(emptyResult);
        Response.ResponseBuilder builderResult = Response.builder();
        when(responseUtil.generateFailureResponse(Mockito.<String>any()))
                .thenReturn(builderResult.data(new ArrayList<>()).error("An error occurred").status("Status").build());
        assertTrue(ideaService.getIdeaByIdeaId("42").isPresent());
        verify(ideaRepo).findByIdeaId(Mockito.<String>any());
        verify(responseUtil).generateFailureResponse(Mockito.<String>any());
    }

    /**
     * Method under test: {@link IdeaService#getIdeaByIdeaId(String)}
     */
    @Test
    void testGetIdeaByIdeaId2() {
        Ideas ideas = mock(Ideas.class);
        doNothing().when(ideas).setComment(Mockito.<String>any());
        doNothing().when(ideas).setCreatedAt(Mockito.<Date>any());
        doNothing().when(ideas).setDescription(Mockito.<String>any());
        doNothing().when(ideas).setId(Mockito.<String>any());
        doNothing().when(ideas).setIdeaId(Mockito.<String>any());
        doNothing().when(ideas).setImage(Mockito.<String>any());
        doNothing().when(ideas).setLikes(Mockito.<Long>any());
        doNothing().when(ideas).setName(Mockito.<String>any());
        doNothing().when(ideas).setTag(Mockito.<String>any());
        doNothing().when(ideas).setUpdatedAt(Mockito.<Date>any());
        ideas.setComment("Comment");
        ideas.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        ideas.setDescription("The characteristics of someone or something");
        ideas.setId("42");
        ideas.setIdeaId("42");
        ideas.setImage("Image");
        ideas.setLikes(1L);
        ideas.setName("Name");
        ideas.setTag("Tag");
        ideas.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        Optional<Ideas> ofResult = Optional.of(ideas);
        when(ideaRepo.findByIdeaId(Mockito.<String>any())).thenReturn(ofResult);

        IdeasDto ideasDto = new IdeasDto();
        ideasDto.setComment("Comment");
        ideasDto.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        ideasDto.setDescription("The characteristics of someone or something");
        ideasDto.setId("42");
        ideasDto.setIdeaId("42");
        ideasDto.setImage("Image");
        ideasDto.setLikes(1L);
        ideasDto.setName("Name");
        ideasDto.setTag("Tag");
        ideasDto.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Type>any())).thenReturn(ideasDto);
        Response.ResponseBuilder builderResult = Response.builder();
        when(responseUtil.generateSuccessResponse(Mockito.<Object>any()))
                .thenReturn(builderResult.data(new ArrayList<>()).error("An error occurred").status("Status").build());
        assertTrue(ideaService.getIdeaByIdeaId("42").isPresent());
        verify(ideaRepo).findByIdeaId(Mockito.<String>any());
        verify(ideas).setComment(Mockito.<String>any());
        verify(ideas).setCreatedAt(Mockito.<Date>any());
        verify(ideas).setDescription(Mockito.<String>any());
        verify(ideas).setId(Mockito.<String>any());
        verify(ideas).setIdeaId(Mockito.<String>any());
        verify(ideas).setImage(Mockito.<String>any());
        verify(ideas).setLikes(Mockito.<Long>any());
        verify(ideas).setName(Mockito.<String>any());
        verify(ideas).setTag(Mockito.<String>any());
        verify(ideas).setUpdatedAt(Mockito.<Date>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Type>any());
        verify(responseUtil).generateSuccessResponse(Mockito.<Object>any());
    }

    /**
     * Method under test: {@link IdeaService#getAllIdeas()}
     */
    @Test
    void testGetAllIdeas() {
        when(ideaRepo.findAll()).thenReturn(new ArrayList<>());
        Response.ResponseBuilder builderResult = Response.builder();
        when(responseUtil.generateFailureResponse(Mockito.<String>any()))
                .thenReturn(builderResult.data(new ArrayList<>()).error("An error occurred").status("Status").build());
        assertTrue(ideaService.getAllIdeas().isPresent());
        verify(ideaRepo).findAll();
        verify(responseUtil).generateFailureResponse(Mockito.<String>any());
    }

    /**
     * Method under test: {@link IdeaService#getAllIdeas()}
     */
    @Test
    void testGetAllIdeas2() {
        Ideas ideas = mock(Ideas.class);
        doNothing().when(ideas).setComment(Mockito.<String>any());
        doNothing().when(ideas).setCreatedAt(Mockito.<Date>any());
        doNothing().when(ideas).setDescription(Mockito.<String>any());
        doNothing().when(ideas).setId(Mockito.<String>any());
        doNothing().when(ideas).setIdeaId(Mockito.<String>any());
        doNothing().when(ideas).setImage(Mockito.<String>any());
        doNothing().when(ideas).setLikes(Mockito.<Long>any());
        doNothing().when(ideas).setName(Mockito.<String>any());
        doNothing().when(ideas).setTag(Mockito.<String>any());
        doNothing().when(ideas).setUpdatedAt(Mockito.<Date>any());
        ideas.setComment("No Idea found in the database, please insert some ideas first");
        ideas.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        ideas.setDescription("The characteristics of someone or something");
        ideas.setId("42");
        ideas.setIdeaId("42");
        ideas.setImage("No Idea found in the database, please insert some ideas first");
        ideas.setLikes(1L);
        ideas.setName("No Idea found in the database, please insert some ideas first");
        ideas.setTag("No Idea found in the database, please insert some ideas first");
        ideas.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        ArrayList<Ideas> ideasList = new ArrayList<>();
        ideasList.add(ideas);
        when(ideaRepo.findAll()).thenReturn(ideasList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Type>any())).thenReturn(new ArrayList<>());
        Response.ResponseBuilder builderResult = Response.builder();
        when(responseUtil.generateSuccessResponse(Mockito.<Object>any()))
                .thenReturn(builderResult.data(new ArrayList<>()).error("An error occurred").status("Status").build());
        assertTrue(ideaService.getAllIdeas().isPresent());
        verify(ideaRepo).findAll();
        verify(ideas).setComment(Mockito.<String>any());
        verify(ideas).setCreatedAt(Mockito.<Date>any());
        verify(ideas).setDescription(Mockito.<String>any());
        verify(ideas).setId(Mockito.<String>any());
        verify(ideas).setIdeaId(Mockito.<String>any());
        verify(ideas).setImage(Mockito.<String>any());
        verify(ideas).setLikes(Mockito.<Long>any());
        verify(ideas).setName(Mockito.<String>any());
        verify(ideas).setTag(Mockito.<String>any());
        verify(ideas).setUpdatedAt(Mockito.<Date>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Type>any());
        verify(responseUtil).generateSuccessResponse(Mockito.<Object>any());
    }

    /**
     * Method under test: {@link IdeaService#deleteByIdeaId(String)}
     */
    @Test
    void testDeleteByIdeaId() {
        Ideas ideas = new Ideas();
        ideas.setComment("Comment");
        ideas.setCreatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        ideas.setDescription("The characteristics of someone or something");
        ideas.setId("42");
        ideas.setIdeaId("42");
        ideas.setImage("Image");
        ideas.setLikes(1L);
        ideas.setName("Name");
        ideas.setTag("Tag");
        ideas.setUpdatedAt(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        Optional<Ideas> ofResult = Optional.of(ideas);
        doNothing().when(ideaRepo).deleteById(Mockito.<String>any());
        when(ideaRepo.findByIdeaId(Mockito.<String>any())).thenReturn(ofResult);
        Response.ResponseBuilder builderResult = Response.builder();
        when(responseUtil.generateSuccessResponse(Mockito.<Object>any()))
                .thenReturn(builderResult.data(new ArrayList<>()).error("An error occurred").status("Status").build());
        assertTrue(ideaService.deleteByIdeaId("42").isPresent());
        verify(ideaRepo).findByIdeaId(Mockito.<String>any());
        verify(ideaRepo).deleteById(Mockito.<String>any());
        verify(responseUtil).generateSuccessResponse(Mockito.<Object>any());
    }

    /**
     * Method under test: {@link IdeaService#deleteByIdeaId(String)}
     */
    @Test
    void testDeleteByIdeaId2() {
        Optional<Ideas> emptyResult = Optional.empty();
        when(ideaRepo.findByIdeaId(Mockito.<String>any())).thenReturn(emptyResult);
        Response.ResponseBuilder builderResult = Response.builder();
        when(responseUtil.generateFailureResponse(Mockito.<String>any()))
                .thenReturn(builderResult.data(new ArrayList<>()).error("An error occurred").status("Status").build());
        assertTrue(ideaService.deleteByIdeaId("42").isPresent());
        verify(ideaRepo).findByIdeaId(Mockito.<String>any());
        verify(responseUtil).generateFailureResponse(Mockito.<String>any());
    }
}