package com.api.cblk.domain.dto;

import com.api.cblk.domain.entity.Profile;

import java.time.LocalDate;
import java.util.Optional;

public record ProfileCompleteData(Long id, String name, LocalDate birthday, String course, String imgUrl, String aboutText) {
    public ProfileCompleteData(Profile profile){
        this(
                profile.getId(),
                profile.getName(),
                profile.getBirthday(),
                profile.getCourse(),
                profile.getImgUrl(),
                profile.getAboutText()
        );
    }

    public ProfileCompleteData(ProfileUpdateData data) {
        this(
                data.id(),
                data.name(),
                data.birthday(),
                data.course(),
                data.imgUrl(),
                data.aboutText()
        );
    }
}
