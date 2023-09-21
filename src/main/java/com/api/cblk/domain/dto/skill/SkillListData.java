package com.api.cblk.domain.dto.skill;

import com.api.cblk.domain.entity.Skill;
import com.api.cblk.domain.type.Level;

public record SkillListData (Long id, String name, String skillImgUrl, String description, Level level){

    public SkillListData(Skill skill) {
        this(
                skill.getId(),
                skill.getName(),
                skill.getSkillImgUrl(),
                skill.getDescription(),
                skill.getLevel()
        );
    }
}
