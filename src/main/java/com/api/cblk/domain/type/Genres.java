package com.api.cblk.domain.type;

public enum Genres {
    ADVENTURE("Aventura"),
    RPG("RPG"),
    PLATFORM("Plataforma"),
    STRATEGY("Estratégia"),
    FPS("FPS"),
    MOBA("MOBA"),
    SPORTS("Esportes"),
    RACE("Race"),
    MMORPG("MMORPG"),
    PUZZLE("Puzzle");

    public String valorEnum;
    Genres(String value) {
        valorEnum = value;
    }

    public String getValorEnum(){
        return valorEnum;
    }
}
