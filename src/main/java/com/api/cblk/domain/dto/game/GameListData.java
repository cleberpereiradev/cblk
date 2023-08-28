package com.api.cblk.domain.dto.game;

import com.api.cblk.domain.entity.Game;
import com.api.cblk.domain.type.Genres;

public record GameListData(Long id,
                           String title,
                           Integer releaseDate,
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