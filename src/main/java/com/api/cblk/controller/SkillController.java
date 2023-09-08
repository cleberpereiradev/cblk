package com.api.cblk.controller;

import com.api.cblk.domain.dto.skill.SkillCompleteData;
import com.api.cblk.domain.dto.skill.SkillCreateData;
import com.api.cblk.domain.dto.skill.SkillListData;
import com.api.cblk.domain.dto.skill.SkillUpdateData;
import com.api.cblk.domain.entity.Skill;
import com.api.cblk.service.SkillService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping
    public ResponseEntity<List<SkillListData>> findAll() {
        var skills = this.skillService.findAll();
        return ResponseEntity.ok(skills);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SkillCompleteData> findById(@PathVariable Long id) {
        var skill = this.skillService.findById(id);
        return ResponseEntity.ok(skill);
    }

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody @Valid SkillCreateData data, UriComponentsBuilder uriComponentsBuilder) {
        var skill = new Skill(data);
        this.skillService.save(skill);
        var uri = uriComponentsBuilder.path("/skills/{id}").buildAndExpand(skill.getId()).toUri();
        return ResponseEntity.created(uri).body(new SkillCompleteData(skill));
    }

    @PutMapping
    @Transactional
    public ResponseEntity save(@RequestBody SkillUpdateData data) {
        this.skillService.update(data);
        return ResponseEntity.ok(new SkillCompleteData(data));
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.skillService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
