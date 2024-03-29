package com.api.cblk.domain.dto.skill;

import com.api.cblk.domain.entity.Skill;
import com.api.cblk.domain.type.Level;

public record SkillCompleteData(Long id, String name, String skillImgUrl, String description, Level level) {
    public SkillCompleteData(Skill skill) {
        this(
                skill.getId(),
                skill.getName(),
                skill.getSkillImgUrl(),
                skill.getDescription(),
                skill.getLevel()
        );
    }

    public SkillCompleteData(SkillUpdateData data) {
        this(
                data.id(),
                data.name(),
                data.skillImgUrl(),
                data.description(),
                data.level()
        );

    }
}
