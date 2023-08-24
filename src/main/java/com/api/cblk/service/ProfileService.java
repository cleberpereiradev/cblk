package com.api.cblk.service;

import com.api.cblk.domain.dto.ProfileCompleteData;
import com.api.cblk.domain.dto.ProfileListData;
import com.api.cblk.domain.dto.ProfileUpdateData;
import com.api.cblk.domain.entity.Profile;
import com.api.cblk.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public List<ProfileListData> findAll() {
        return this.profileRepository.findAll().stream().map(ProfileListData::new).toList();
    }

    public ProfileCompleteData findById(Long id) {
        var profile = this.profileRepository.findById(id).get();
        return new ProfileCompleteData(profile);
    }

    public void save(Profile data) {
        this.profileRepository.save(data);
    }

    public void deleteById(Long id) {
        if(this.profileRepository.existsById(id)){
            this.profileRepository.deleteById(id);
        }else {
            throw new RuntimeException("ID Não encontrado!");
        }
    }

    public void update(ProfileUpdateData data){
        var profile = profileRepository.getReferenceById(data.id());
        profile.update(data);
    }


}
