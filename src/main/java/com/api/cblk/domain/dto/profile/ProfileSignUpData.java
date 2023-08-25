package com.api.cblk.domain.dto.profile;

import java.time.LocalDate;

public record ProfileSignUpData(String name, LocalDate birthday, String course, String imgUrl, String aboutText) {
}
