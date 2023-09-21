package com.api.cblk.domain.entity;

import com.api.cblk.domain.dto.game.GameRegistrationData;
import com.api.cblk.domain.dto.game.GameUpdateData;
import com.api.cblk.domain.type.Genres;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Game")
@Table(name = "games")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private int releaseDate;

    private Genres gameGenre;

    private String gameImgUrl;

    @NotBlank
    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String fullDescription;

    private Double hoursPlayed;

    private Double rating;


    public Game (GameRegistrationData data) {
        this.title = data.title();
        this.releaseDate = data.releaseDate();
        this.gameGenre = data.gameGenre();
        this.gameImgUrl = data.gameImgUrl();
        this.shortDescription = data.shortDescription();
        this.fullDescription = data.fullDescription();
        this.hoursPlayed = data.hoursPlayed();
        this.rating = data.rating();
    }

    public void update(GameUpdateData data) {
        if(data.title() != null) {
            this.title = data.title();
        }
        if (data.releaseDate() != 0) {
            this.releaseDate = data.releaseDate();
        }
        if(data.gameGenre() != null) {
            this.gameGenre = data.gameGenre();
        }
        if(data.gameImgUrl() != null) {
            this.gameImgUrl = data.gameImgUrl();
        }
        if(data.shortDescription() != null) {
            this.shortDescription = data.shortDescription();
        }
        if(data.fullDescription() != null) {
            this.fullDescription = data.fullDescription();
        }
        if(data.hoursPlayed() != null) {
            this.hoursPlayed = data.hoursPlayed();
        }
    }

}

