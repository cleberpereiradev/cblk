package com.api.cblk.domain.dto.content;

import com.api.cblk.domain.type.ContentMediaType;

public record ContentRegistrationData(
        String author,
        String shortDescription,
        String fullDescription,
        double rating,
        ContentMediaType mediaType,
        String contentLink
) { }
