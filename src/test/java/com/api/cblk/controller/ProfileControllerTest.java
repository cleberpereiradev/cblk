package com.api.cblk.controller;

import com.api.cblk.domain.dto.profile.ProfileCompleteData;
import com.api.cblk.domain.dto.profile.ProfileListData;
import com.api.cblk.domain.dto.profile.ProfileSignUpData;
import com.api.cblk.domain.dto.profile.ProfileUpdateData;
import com.api.cblk.domain.entity.Profile;
import com.api.cblk.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfileController.class)
class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileService profileService;

    @Test
    void testFindAll() throws Exception {
        ProfileListData profile1 = new ProfileListData(1L, "John Doe",null, "DSM1", "imagem.com","Um textao enorme");
        ProfileListData profile2 = new ProfileListData(2L, "Jane Smith",null, "DSM1", "imagem.com","Um textao enorme");
        List<ProfileListData> profiles = Arrays.asList(profile1, profile2);

        when(profileService.findAll()).thenReturn(profiles);

        mockMvc.perform(MockMvcRequestBuilders.get("/profiles"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    void testFindById() throws Exception {
        ProfileCompleteData profile = new ProfileCompleteData(new Profile(1L, "John Doe", LocalDate.of(1990, 1, 15), "Computer Science", "image.png", "About John"));

        when(profileService.findById(1L)).thenReturn(profile);

        mockMvc.perform(MockMvcRequestBuilders.get("/profiles/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testSave() throws Exception {
        ProfileSignUpData signUpData = new ProfileSignUpData("John Doe", LocalDate.of(1990, 1, 15), "Computer Science", "image.png", "About John");
        Profile profile = new Profile(signUpData);
        ProfileCompleteData completeData = new ProfileCompleteData(profile);

        mockMvc.perform(MockMvcRequestBuilders.post("/profiles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"birthday\":\"1990-01-15\",\"course\":\"Computer Science\",\"imgUrl\":\"image.png\",\"aboutText\":\"About John\"}")
                )
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testUpdate() throws Exception {
        ProfileUpdateData updateData = new ProfileUpdateData(1L, "Updated Name", null, null, null, null);

        mockMvc.perform(MockMvcRequestBuilders.put("/profiles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Updated Name\"}")
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Name"));
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/profiles/1"))
                .andExpect(status().isNoContent());

        verify(profileService, times(1)).deleteById(1L);
    }
}
