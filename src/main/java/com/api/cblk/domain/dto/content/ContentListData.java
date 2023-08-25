package com.api.cblk.domain.dto.content;

import com.api.cblk.domain.entity.Content;
import com.api.cblk.domain.type.ContentMediaType;

public record ContentListData(Long id,
                              String author,
                              String shortDescription,
                              double rating,
                              ContentMediaType mediaType,
                              String contentLink) {

    public ContentListData(Content content){
        this(
                content.getId(),
                content.getAuthor(),
                content.getShortDescription(),
                content.getRating(),
                content.getMediaType(),
                content.getContentLink()
        );
    }
}
