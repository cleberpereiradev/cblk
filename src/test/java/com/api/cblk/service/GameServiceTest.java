package com.api.cblk.service;

import com.api.cblk.domain.dto.content.ContentUpdateData;
import com.api.cblk.domain.dto.game.*;
import com.api.cblk.domain.entity.Content;
import com.api.cblk.domain.entity.Game;
import com.api.cblk.domain.type.ContentMediaType;
import com.api.cblk.domain.type.Genres;
import com.api.cblk.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Crie uma lista de jogos de exemplo
        List<Game> games = List.of(
                new Game(1L, "CyberPunk2077", 2020, Genres.RPG, "umaimagem.com", "Pequena descrição", "Grande enorme descrição", 150.5, 4.8),
                new Game(2L, "Valorant", 2021, Genres.FPS, "umaimagem.com", "Pequena descrição", "Grande enorme descrição", 150.5, 4.3)
        );

        // Simule o comportamento do repositório para retornar a lista de jogos de exemplo
        when(gameRepository.findAll()).thenReturn(games);

        // Chame o método findAll() do serviço
        List<GameListData> gameList = gameService.findAll();

        // Verifique se o resultado não está vazio e possui o tamanho correto
        assertFalse(gameList.isEmpty());
        assertEquals(games.size(), gameList.size());
    }

    @Test
    void testFindById() {
        // Crie um jogo de exemplo com um ID específico
        Long gameId = 1L;
        Game game = new Game(1L,"CyberPunk2077", 2020, Genres.RPG, "umaimagem.com", "Pequena descrição", "Grande enorme descrição", 150.5, 4.8);

        // Simule o comportamento do repositório para retornar o jogo de exemplo
        when(gameRepository.existsById(gameId)).thenReturn(true);
        when(gameRepository.findById(gameId)).thenReturn(Optional.of(game));

        // Chame o método findById() do serviço
        GameCompleteData gameCompleteData = gameService.findById(gameId);

        // Verifique se o resultado não é nulo e possui o ID correto
        assertNotNull(gameCompleteData);
        assertEquals(gameId, gameCompleteData.id());
    }

    @Test
    void testSave() {
        // Crie um objeto GameRegistrationData para simular os dados de entrada
        Game registrationData = new Game(1L,"CyberPunk2077", 2020, Genres.RPG, "umaimagem.com", "Pequena descrição", "Grande enorme descrição", 150.5, 4.8);

        // Chame o método save() do serviço
        gameService.save(registrationData);

        // Verifique se o repositório foi chamado para salvar o jogo
        verify(gameRepository, times(1)).save(any(Game.class));
    }

    @Test
    void testUpdate() {
        GameUpdateData updateData = new GameUpdateData(1L, "Updated Game title", 2021, Genres.PLATFORM, "outraimagem.com", "Updated Short Desc", "Updated Full Desc", 150.0, 5.0);

        Game existingGame = new Game(1L, "Game title", 2021, Genres.PLATFORM, "umaimagem.com", "Short Desc", "Full Desc", 150.0, 5.0);
        when(gameRepository.getReferenceById(1L)).thenReturn(existingGame);

        gameService.update(updateData);

        assertEquals("Updated Game title", existingGame.getTitle());
        assertEquals("Updated Short Desc", existingGame.getShortDescription());
        assertEquals("Updated Full Desc", existingGame.getFullDescription());
    }

    // Você pode continuar implementando testes para os outros métodos, como update(), deleteById(), findTopGames(), etc.
}
