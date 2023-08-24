package com.api.cblk.domain.dto;

import com.api.cblk.domain.entity.Profile;

import java.time.LocalDate;

public record ProfileListData(Long id, String name, LocalDate birthdayDate, String course, String imgUrl, String aboutText) {
    public ProfileListData(Profile profile){
        this(profile.getId(), profile.getName(), profile.getBirthday(), profile.getCourse(), profile.getImgUrl(), profile.getAboutText());
    }
}
