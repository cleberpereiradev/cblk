package com.api.cblk.domain.dto;

import java.time.LocalDate;

public record ProfileUpdateData(Long id, String name, LocalDate birthday, String course, String imgUrl, String aboutText) {
}
