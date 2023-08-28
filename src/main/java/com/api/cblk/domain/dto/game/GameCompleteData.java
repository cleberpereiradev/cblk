package com.api.cblk.domain.dto.game;

import com.api.cblk.domain.entity.Game;
import com.api.cblk.domain.type.Genres;

public record GameCompleteData(
        Long id,
        String title,
        Integer releaseDate,
        Genres gameGenre,
        String gameImgUrl,
        String shortDescription,
        String fullDescription,
        Double hoursPlayed,
        Double rating
) {
    public GameCompleteData(Game game) {
        this(
                game.getId(),
                game.getTitle(),
                game.getReleaseDate(),
                game.getGameGenre(),
                game.getGameImgUrl(),
                game.getShortDescription(),
                game.getFullDescription(),
                game.getHoursPlayed(),
                game.getRating()
        );
    }

    public GameCompleteData(GameUpdateData data) {
        this(
                data.id(),
                data.title(),
                data.releaseDate(),
                data.gameGenre(),
                data.gameImgUrl(),
                data.shortDescription(),
                data.fullDescription(),
                data.hoursPlayed(),
                data.rating()
        );
    }
}
