package com.api.cblk.domain.entity;

import com.api.cblk.domain.dto.profile.ProfileSignUpData;
import com.api.cblk.domain.dto.profile.ProfileUpdateData;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
@Entity(name = "Profile")
@Table(name = "profile")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")

public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotBlank
    private String course;

    private String imgUrl;

    @Column(columnDefinition = "TEXT")
    @NotBlank
    private String aboutText;

    public Profile (ProfileSignUpData data) {
        this.name = data.name();
        this.birthday = data.birthday();
        this.course = data.course();
        this.imgUrl = data.imgUrl();
        this.aboutText = data.aboutText();
    }

    public void update(ProfileUpdateData data) {
        if(data.name() != null) {
            this.name = data.name();
        }
        if(data.birthday() != null) {
            this.birthday = data.birthday();
        }
        if(data.course() != null) {
            this.course = data.course();
        }
        if(data.imgUrl() != null) {
            this.imgUrl = data.imgUrl();
        }
        if(data.aboutText() != null) {
            this.aboutText = data.aboutText();
        }
    }
}
