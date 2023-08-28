package com.api.cblk.domain.dto.game;

import com.api.cblk.domain.type.Genres;

public record GameRegistrationData(
        String title,
        Integer releaseDate,
        Genres gameGenre,
        String gameImgUrl,
        String shortDescription,
        String fullDescription,
        Double hoursPlayed,
        Double rating
) {
}
