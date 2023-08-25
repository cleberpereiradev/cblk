package com.api.cblk.service;

import com.api.cblk.domain.dto.content.ContentCompleteData;
import com.api.cblk.domain.dto.content.ContentListData;
import com.api.cblk.domain.dto.content.ContentUpdateData;
import com.api.cblk.domain.entity.Content;
import com.api.cblk.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    public List<ContentListData> findAll() {
        return this.contentRepository.findAll().stream().map(ContentListData::new).toList();
    }

    public ContentCompleteData findById(Long id) {
        var content = this.contentRepository.findById(id).get();
        return new ContentCompleteData(content);
    }

    public void save(Content data) {
        this.contentRepository.save(data);
    }

    public void deleteById(Long id) {
        if(this.contentRepository.existsById(id)){
            this.contentRepository.deleteById(id);
        }else {
            throw new RuntimeException("ID Não encontrado!");
        }
    }

    public void update(ContentUpdateData data){
        var content = contentRepository.getReferenceById(data.id());
        content.update(data);
    }
}
