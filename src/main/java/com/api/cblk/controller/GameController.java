package com.api.cblk.controller;

import com.api.cblk.domain.dto.game.GameCompleteData;
import com.api.cblk.domain.dto.game.GameListData;
import com.api.cblk.domain.dto.game.GameRegistrationData;
import com.api.cblk.domain.dto.game.GameUpdateData;
import com.api.cblk.domain.entity.Game;
import com.api.cblk.service.GameService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameListData>> findAll() {
        List<GameListData> list = this.gameService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        var game = this.gameService.findById(id);

        return ResponseEntity.ok(game);
    }

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody @Valid GameRegistrationData data, UriComponentsBuilder uriComponentsBuilder) {
        var game = new Game(data);
        this.gameService.save(game);
        var uri = uriComponentsBuilder.path("/contents/{id}").buildAndExpand(game.getId()).toUri();
        return ResponseEntity.created(uri).body(new GameCompleteData(game));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody GameUpdateData data) {
        this.gameService.update(data);
        return ResponseEntity.ok(new GameCompleteData(data));
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.gameService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
