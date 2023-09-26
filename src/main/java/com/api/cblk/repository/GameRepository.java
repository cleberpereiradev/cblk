package com.api.cblk.repository;


import com.api.cblk.domain.dto.game.GameRecommendedData;
import com.api.cblk.domain.dto.game.GameTopListData;
import com.api.cblk.domain.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("select g from Game g order by g.rating desc limit 5")
    List<GameTopListData> findTopGames();

    @Query("SELECT g FROM Game g ORDER BY RANDOM() LIMIT 5")
    List<GameRecommendedData> findRandomGames();
}
