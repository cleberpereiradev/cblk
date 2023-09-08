package com.api.cblk.domain.entity;

import com.api.cblk.domain.dto.skill.SkillCreateData;
import com.api.cblk.domain.dto.skill.SkillUpdateData;
import com.api.cblk.domain.type.Level;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Skill")
@Table(name = "skills")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String skillImgUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Level level;


    public Skill (SkillCreateData data) {
        this.name = data.name();
        this.skillImgUrl = data.skillImgUrl();
        this.description = data.description();
        this.level = data.level();
    }

    public void update(SkillUpdateData data) {
        if(data.name() != null) {
            this.name = data.name();
        }
        if (data.skillImgUrl() != null) {
            this.skillImgUrl = data.skillImgUrl();
        }
        if(data.description() != null) {
            this.description = data.description();
        }
        if(data.level() != null) {
            this.level = data.level();
        }
    }
}
