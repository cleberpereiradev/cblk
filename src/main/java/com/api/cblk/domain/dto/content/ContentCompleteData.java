package com.api.cblk.domain.dto.content;
import com.api.cblk.domain.entity.Content;
import com.api.cblk.domain.type.ContentMediaType;

public record ContentCompleteData(Long id,
                                  String author,
                                  String shortDescription,
                                  String fullDescription,
                                  double rating,
                                  ContentMediaType mediaType,
                                  String contentLink) {

    public ContentCompleteData(Content content){
        this(
            content.getId(),
            content.getAuthor(),
            content.getShortDescription(),
            content.getFullDescription(),
            content.getRating(),
            content.getMediaType(),
            content.getContentLink()
        );
    }

    public ContentCompleteData(ContentUpdateData content) {
        this(
            content.id(),
            content.author(),
            content.shortDescription(),
            content.fullDescription(),
            content.rating(),
            content.mediaType(),
            content.contentLink()
        );
    }

}
