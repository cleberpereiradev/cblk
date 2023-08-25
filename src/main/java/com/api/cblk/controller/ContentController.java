package com.api.cblk.controller;

import com.api.cblk.domain.dto.content.ContentCompleteData;
import com.api.cblk.domain.dto.content.ContentListData;
import com.api.cblk.domain.dto.content.ContentRegistrationData;
import com.api.cblk.domain.dto.content.ContentUpdateData;
import com.api.cblk.domain.dto.profile.ProfileCompleteData;
import com.api.cblk.domain.dto.profile.ProfileListData;
import com.api.cblk.domain.dto.profile.ProfileSignUpData;
import com.api.cblk.domain.dto.profile.ProfileUpdateData;
import com.api.cblk.domain.entity.Content;
import com.api.cblk.domain.entity.Profile;
import com.api.cblk.service.ContentService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/contents")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping
    public ResponseEntity<List<ContentListData>> findAll() {
        List<ContentListData> list = this.contentService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        var content = this.contentService.findById(id);

        return ResponseEntity.ok(content);
    }

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody @Valid ContentRegistrationData data, UriComponentsBuilder uriComponentsBuilder) {
        var content = new Content(data);
        this.contentService.save(content);
        var uri = uriComponentsBuilder.path("/contents/{id}").buildAndExpand(content.getId()).toUri();
        return ResponseEntity.created(uri).body(new ContentCompleteData(content));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody ContentUpdateData data) {
        this.contentService.update(data);
        return ResponseEntity.ok(new ContentCompleteData(data));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.contentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
