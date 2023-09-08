package com.api.cblk.domain.dto.game;

import com.api.cblk.domain.type.Genres;

import java.time.LocalDate;

public record GameUpdateData(
        Long id,
        String title,
        LocalDate releaseDate,
        Genres gameGenre,
        String gameImgUrl,
        String shortDescription,
        String fullDescription,
        Double hoursPlayed,
        Double rating
) {
}
