package com.api.cblk.domain.dto.game;

import com.api.cblk.domain.type.Genres;

public record GameUpdateData(
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
}
