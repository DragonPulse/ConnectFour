package com.ninty8point6.connectfour.controllers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ninty8point6.connectfour.entities.Game;
import com.ninty8point6.connectfour.entities.GameState;
import com.ninty8point6.connectfour.exceptions.InvalidObjectException;
import com.ninty8point6.connectfour.models.GameInfo;
import com.ninty8point6.connectfour.models.GameRequest;
import com.ninty8point6.connectfour.service.GameService;
import com.ninty8point6.connectfour.service.MoveService;
import com.ninty8point6.connectfour.util.RequestValidator;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Game controller test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class GameControllerTest {

    @InjectMocks
    private GameController controller;

    @Mock
    private GameService gameService;

    @Mock
    private MoveService moveService;

    @Mock
    private RequestValidator requestValidator;

    private Game game;
    private GameState gameState;
    private GameRequest gameRequest;


    /**
     * Init.
     */
    @Before
    public void init(){
        gameRequest = new GameRequest();
        gameRequest.setColumns(4);
        gameRequest.setRows(4);
        gameRequest.setPlayers(Arrays.asList(
                new String("VJ"),
                new String("AJ")
        ));


        String gameId = UUID.randomUUID().toString();
        game = Game.createGame(gameRequest);
        GameInfo gameInfo = game.createInfo();

        //Send Mock Game Object when gameService createGame is called
        Mockito
                .doReturn(gameInfo)
                .when(gameService)
                .createGame(Mockito.any(GameRequest.class));

    }

    /**
     * Can reach get list of games.
     *
     * @throws Exception the exception
     */
    @Test
    public void canReachGetListOfGames() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        String url = "/drop_token";
        List<String> gamesInServerList = Arrays.asList(
                new String(UUID.randomUUID().toString()),
                new String(UUID.randomUUID().toString()),
                new String(UUID.randomUUID().toString())
        );
        String request = url+"";
        mockMvc.perform(get(url).contentType(APPLICATION_JSON_UTF8).content(request)).andExpect(status().isOk());
        // Verify that service is called only once
        Mockito
                .verify(gameService, Mockito.times(1))
                .getAllGames();
    }

    /**
     * Can reach create game.
     *
     * @throws Exception the exception
     */
    @Test
    public void canReachCreateGame() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        String url = "/drop_token";

        gameRequest.setColumns(4);
        gameRequest.setRows(4);
        gameRequest.setPlayers(Arrays.asList(
                new String("VJ"),
                new String("AJ")
        ));


        String gameId = UUID.randomUUID().toString();
        game = Game.createGame(gameRequest);
        GameInfo gameInfo = game.createInfo();

        GameInfo expectedGameInfo = controller.createGame(gameRequest);

        Assert.assertNotNull(expectedGameInfo.getGameId());

        String requestJson = toJsonString(gameRequest);


        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8).content(requestJson)).andExpect(status().isOk());
        // Verify that service is called only once
        Mockito
                .verify(gameService, Mockito.times(1))
                .createGame(gameRequest);
    }

    /**
     * Can reach get game status.
     */
    @Test
    public void canReachGetGameStatus(){

    }

    /**
     * To json string string.
     *
     * @param obj the obj
     * @return the string
     */
    public String toJsonString(final Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw InvalidObjectException.getInstance("Problem converting obj to JSON");
        }
    }
}