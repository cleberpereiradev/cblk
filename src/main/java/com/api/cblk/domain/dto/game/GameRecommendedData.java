package com.api.cblk.domain.dto.game;

import com.api.cblk.domain.entity.Game;
import com.api.cblk.domain.type.Genres;

public record GameRecommendedData(Long id,
                                  String title,
                                  Genres gameGenre,
                                  String gameImgUrl,
                                  Double rating) {

    public GameRecommendedData(Game game) {
        this(
                game.getId(),
                game.getTitle(),
                game.getGameGenre(),
                game.getGameImgUrl(),
                game.getRating()
        );
    }

    public GameRecommendedData(GameRecommendedData game) {
        this(
                game.id(),
                game.title(),
                game.gameGenre(),
                game.gameImgUrl(),
                game.rating()
        );
    }
}
