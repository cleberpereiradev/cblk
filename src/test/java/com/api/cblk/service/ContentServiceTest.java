package com.api.cblk.service;
import com.api.cblk.domain.dto.content.ContentCompleteData;
import com.api.cblk.domain.dto.content.ContentListData;
import com.api.cblk.domain.dto.content.ContentUpdateData;
import com.api.cblk.domain.entity.Content;
import com.api.cblk.domain.type.ContentMediaType;
import com.api.cblk.repository.ContentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContentServiceTest {

    @Mock
    private ContentRepository contentRepository;

    @InjectMocks
    private ContentService contentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Content> contentList = new ArrayList<>();
        contentList.add(new Content(1L, "Author 1", "Short Desc 1", "Full Desc 1", 4.5, ContentMediaType.VIDEO, "link1"));
        contentList.add(new Content(2L, "Author 2", "Short Desc 2", "Full Desc 2", 3.8, ContentMediaType.BLOG, "link2"));

        when(contentRepository.findAll()).thenReturn(contentList);

        List<ContentListData> result = contentService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void testFindById() {
        Content content = new Content(1L, "Author", "Short Desc", "Full Desc", 4.0, ContentMediaType.VIDEO, "link");
        when(contentRepository.findById(1L)).thenReturn(Optional.of(content));

        ContentCompleteData result = contentService.findById(1L);

        assertEquals("Author", result.author());
        assertEquals("Short Desc", result.shortDescription());
    }

    @Test
    void testSave() {
        Content contentToSave = new Content(null, "Author", "Short Desc", "Full Desc", 4.0, ContentMediaType.VIDEO, "link");

        contentService.save(contentToSave);

        verify(contentRepository, times(1)).save(contentToSave);
    }

    @Test
    void testUpdate() {
        ContentUpdateData updateData = new ContentUpdateData(1L, "Updated Author", "Updated Short Desc", "Updated Full Desc", 4.0, ContentMediaType.BLOG, "umlink.com");

        Content existingContent = new Content(1L, "Author", "Short Desc", "Full Desc", 4.0, ContentMediaType.VIDEO, "link");
        when(contentRepository.getReferenceById(1L)).thenReturn(existingContent);

        contentService.update(updateData);

        assertEquals("Updated Author", existingContent.getAuthor());
        assertEquals("Updated Short Desc", existingContent.getShortDescription());
        assertEquals("Updated Full Desc", existingContent.getFullDescription());
    }
}
