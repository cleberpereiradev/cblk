package com.api.cblk.service;

import com.api.cblk.domain.dto.profile.ProfileCompleteData;
import com.api.cblk.domain.dto.profile.ProfileListData;
import com.api.cblk.domain.dto.profile.ProfileUpdateData;
import com.api.cblk.domain.entity.Profile;
import com.api.cblk.repository.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService profileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Profile> profileList = new ArrayList<>();
        profileList.add(new Profile(1L, "John Doe", LocalDate.of(1990, 1, 15), "Computer Science", "image.png", "About John"));
        profileList.add(new Profile(2L, "Jane Smith", LocalDate.of(1985, 6, 30), "Electrical Engineering", "profile.jpg", "About Jane"));

        when(profileRepository.findAll()).thenReturn(profileList);

        List<ProfileListData> result = profileService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void testFindById() {
        Profile profile = new Profile(1L, "John Doe", LocalDate.of(1990, 1, 15), "Computer Science", "image.png", "About John");
        when(profileRepository.findById(1L)).thenReturn(Optional.of(profile));

        ProfileCompleteData result = profileService.findById(1L);

        assertEquals("John Doe", result.name());
        assertEquals(LocalDate.of(1990, 1, 15), result.birthday());
    }

    @Test
    void testDeleteById() {
        when(profileRepository.existsById(1L)).thenReturn(true);

        profileService.deleteById(1L);

        verify(profileRepository, times(1)).deleteById(1L);
    }
    @Test
    void testSave() {
        Profile profileToSave = new Profile(null, "John Doe", LocalDate.of(1990, 1, 15), "Computer Science", "image.png", "About John");

        profileService.save(profileToSave);

        verify(profileRepository, times(1)).save(profileToSave);
    }

    @Test
    void testUpdate() {
        ProfileUpdateData updateData = new ProfileUpdateData(1L, "Updated Name", LocalDate.of(1995, 5, 20), "Updated Course", "updated-img.png", "Updated about text");

        Profile existingProfile = new Profile(1L, "John Doe", LocalDate.of(1990, 1, 15), "Computer Science", "image.png", "About John");
        when(profileRepository.getReferenceById(1L)).thenReturn(existingProfile);

        profileService.update(updateData);

        assertEquals("Updated Name", existingProfile.getName());
        assertEquals(LocalDate.of(1995, 5, 20), existingProfile.getBirthday());
        assertEquals("Updated Course", existingProfile.getCourse());
        assertEquals("updated-img.png", existingProfile.getImgUrl());
        assertEquals("Updated about text", existingProfile.getAboutText());
    }
}
