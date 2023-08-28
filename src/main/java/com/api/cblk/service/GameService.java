package com.api.cblk.service;

import com.api.cblk.domain.dto.game.GameCompleteData;
import com.api.cblk.domain.dto.game.GameListData;
import com.api.cblk.domain.dto.game.GameUpdateData;
import com.api.cblk.domain.entity.Game;
import com.api.cblk.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<GameListData> findAll() {
        return this.gameRepository.findAll().stream().map(GameListData::new).toList();
    }

    public GameCompleteData findById(Long id) {
        var game = this.gameRepository.findById(id).get();
        return new GameCompleteData(game);
    }

    public void save(Game data) {
        this.gameRepository.save(data);
    }

    public void deleteById(Long id) {
        if(this.gameRepository.existsById(id)){
            this.gameRepository.deleteById(id);
        }else {
            throw new RuntimeException("ID Não encontrado!");
        }
    }
    public void update(GameUpdateData data){
        var game = gameRepository.getReferenceById(data.id());
        game.update(data);
    }
}
