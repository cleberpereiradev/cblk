package com.api.cblk.controller;

import com.api.cblk.domain.dto.profile.ProfileCompleteData;
import com.api.cblk.domain.dto.profile.ProfileListData;
import com.api.cblk.domain.dto.profile.ProfileSignUpData;
import com.api.cblk.domain.dto.profile.ProfileUpdateData;
import com.api.cblk.domain.entity.Profile;
import com.api.cblk.service.ProfileService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<List<ProfileListData>> findAll() {
        List<ProfileListData> profiles = this.profileService.findAll();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping(value = "/{id}")
    @Transactional
    public ResponseEntity findById(@PathVariable Long id) {
        var profile = this.profileService.findById(id);

        return ResponseEntity.ok(profile);
    }

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody @Valid ProfileSignUpData data, UriComponentsBuilder uriComponentsBuilder) {
        var profile = new Profile(data);
        this.profileService.save(profile);
        var uri = uriComponentsBuilder.path("/profiles/{id}").buildAndExpand(profile.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProfileCompleteData(profile));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody ProfileUpdateData data) {
        this.profileService.update(data);
        return ResponseEntity.ok(new ProfileCompleteData(data));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.profileService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
