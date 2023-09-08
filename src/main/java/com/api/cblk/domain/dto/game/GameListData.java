package com.api.cblk.domain.dto.game;

import com.api.cblk.domain.entity.Game;
import com.api.cblk.domain.type.Genres;

import java.time.LocalDate;

public record GameListData(Long id,
                           String title,
                           LocalDate releaseDate,
                           Genres gameGenre,
                           String gameImgUrl,
                           String shortDescription,
                           Double rating) {

    public GameListData(Game game) {
        this(
                game.getId(),
                game.getTitle(),
                game.getReleaseDate(),
                game.getGameGenre(),
                game.getGameImgUrl(),
                game.getShortDescription(),
                game.getRating()
        );
    }

}
