package com.api.cblk.service;

import com.api.cblk.domain.dto.skill.SkillCompleteData;
import com.api.cblk.domain.dto.skill.SkillListData;
import com.api.cblk.domain.dto.skill.SkillUpdateData;
import com.api.cblk.domain.entity.Skill;
import com.api.cblk.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<SkillListData> findAll() {
        return this.skillRepository.findAll().stream().map(SkillListData::new).toList();
    }

    public SkillCompleteData findById(Long id) {
        var skill = this.skillRepository.findById(id).get();
        return new SkillCompleteData(skill);
    }

    public void save(Skill data) {
        this.skillRepository.save(data);
    }

    public void deleteById(Long id) {
        if(this.skillRepository.existsById(id)){
            this.skillRepository.deleteById(id);
        }else {
            throw new RuntimeException("ID Não encontrado!");
        }
    }

    public void update(SkillUpdateData data){
        var skill = skillRepository.getReferenceById(data.id());
        skill.update(data);
    }

}
