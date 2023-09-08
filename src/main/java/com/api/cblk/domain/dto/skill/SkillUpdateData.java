package com.api.cblk.domain.dto.skill;

import com.api.cblk.domain.type.Level;

public record SkillUpdateData(
        Long id,
        String name,
        String skillImgUrl,
        String description,
        Level level
) {
}
