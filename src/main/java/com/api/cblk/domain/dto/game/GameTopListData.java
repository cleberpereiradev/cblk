package com.api.cblk.domain.dto.game;

import com.api.cblk.domain.entity.Game;

public record GameTopListData(Long id, String title, Double rating) {

    public GameTopListData(GameTopListData gameTopListData) {
        this(
                gameTopListData.id(),
                gameTopListData.title(),
                gameTopListData.rating()
        );
    }
    public GameTopListData(Game game) {
        this(
                game.getId(),
                game.getTitle(),
                game.getRating()
        );
    }
}
