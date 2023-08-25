package com.api.cblk.controller;

import com.api.cblk.domain.dto.content.ContentCompleteData;
import com.api.cblk.domain.dto.content.ContentListData;
import com.api.cblk.domain.dto.content.ContentRegistrationData;
import com.api.cblk.domain.dto.content.ContentUpdateData;
import com.api.cblk.domain.type.ContentMediaType;
import com.api.cblk.service.ContentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContentController.class)
class ContentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContentService contentService;

    @Test
    void testFindAll() throws Exception {
        ContentListData content1 = new ContentListData(1L, "Author 1", "Short Description 1",4.5,ContentMediaType.VIDEO,"umlink.com");
        ContentListData content2 = new ContentListData(2L, "Author 2", "Short Description 2",4,ContentMediaType.VIDEO,"umlink.com");
        List<ContentListData> contents = Arrays.asList(content1, content2);

        when(contentService.findAll()).thenReturn(contents);

        mockMvc.perform(MockMvcRequestBuilders.get("/contents"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    void testFindById() throws Exception {
        ContentCompleteData content = new ContentCompleteData(1L, "Author", "Short Description", "Full Description", 4.5, ContentMediaType.VIDEO,"umlink.com");

        when(contentService.findById(1L)).thenReturn(content);

        mockMvc.perform(MockMvcRequestBuilders.get("/contents/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Author"));
    }

    @Test
    void testSave() throws Exception {
        ContentRegistrationData registrationData = new ContentRegistrationData("Author", "Short Description", "Full Description", 4.5,ContentMediaType.VIDEO,"umlink.com");
        ContentCompleteData completeData = new ContentCompleteData(1L, "Author", "Short Description", "Full Description", 4.5,ContentMediaType.VIDEO,"umlink.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/contents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"author\":\"Author\",\"shortDescription\":\"Short Description\",\"fullDescription\":\"Full Description\",\"rating\":4.5}")
                )
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Author"));
    }

    @Test
    void testUpdate() throws Exception {
        ContentUpdateData updateData = new ContentUpdateData(1L, "Updated Author", "Updated Short Description","aloalo full description", 4.5, ContentMediaType.VIDEO,"umlink.com");

        mockMvc.perform(MockMvcRequestBuilders.put("/contents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"author\":\"Updated Author\",\"shortDescription\":\"Updated Short Description\"}")
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Updated Author"));
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/contents/1"))
                .andExpect(status().isNoContent());

        verify(contentService, times(1)).deleteById(1L);
    }
}
