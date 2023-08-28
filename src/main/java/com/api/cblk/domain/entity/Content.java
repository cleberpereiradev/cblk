package com.api.cblk.domain.entity;

import com.api.cblk.domain.dto.content.ContentRegistrationData;
import com.api.cblk.domain.dto.content.ContentUpdateData;
import com.api.cblk.domain.type.ContentMediaType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Content")
@Table(name = "contents")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String author;

    @NotBlank
    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String fullDescription;

    private double rating;

    private ContentMediaType mediaType;

    private String contentLink;

    public Content (ContentRegistrationData data) {
        this.author = data.author();
        this.shortDescription = data.shortDescription();
        this.fullDescription = data.fullDescription();
        this.rating = data.rating();
        this.mediaType = data.mediaType();
        this.contentLink = data.contentLink();
    }

    public void update(ContentUpdateData data) {
        if(data.author() != null) {
            this.author = data.author();
        }
        if(data.shortDescription() != null) {
            this.shortDescription = data.shortDescription();
        }
        if(data.fullDescription() != null) {
            this.fullDescription = data.fullDescription();
        }
        if(data.rating() != 0) {
            this.rating = data.rating();
        }
        if(data.mediaType() != null) {
            this.mediaType = data.mediaType();
        }
        if(data.contentLink() != null) {
            this.contentLink = data.contentLink();
        }
    }

}
