package com.googlecode.fspotcloud.model.api.test.tag;

import com.google.guiceberry.junit4.GuiceBerryRule;
import com.google.inject.Inject;
import com.googlecode.fspotcloud.model.api.test.EmptyGuiceBerryEnv;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.photo.PhotoInfo;
import com.googlecode.fspotcloud.shared.tag.TagNode;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Logger;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;

public class TagManagerTest {

    private static final Logger log = Logger.getLogger(TagManagerTest.class.getName());
    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(EmptyGuiceBerryEnv.class);
    @Inject
    private Tags tagManager;

    @After
    public void setUp() throws Exception {
        tagManager.deleteBulk(100);
    }

    @Test
    public void testGetTags() {
        createSaveTag("21");
        List<TagNode> tags = tagManager.getTags();
        TagNode node = tags.get(0);
        assertEquals("21", node.getId());
    }

    private Tag createSaveTag(String id) {
        Tag tag = tagManager.findOrNew(id);
        tag.setId(id);
        tagManager.save(tag);
        return tag;
    }
    
    @Test 
    public void modifyPhotoList() {
        Tag subject = createSaveTag("9");
        TreeSet<PhotoInfo> list = subject.getCachedPhotoList();
        list.add(new PhotoInfo("1", "desc", new Date(10000)));
        subject.setCachedPhotoList(list);
        tagManager.save(subject);
        assertEquals("desc", tagManager.find("9").getCachedPhotoList().first().getDescription());
    }
    
    @Test public void getImportedTags() {
        Tag tag = createSaveTag("21");
        tag.setImportIssued(true);
        tagManager.save(tag);
        createSaveTag("22");
        List<Tag> importedTags = tagManager.getImportedTags();
        assertEquals(1, importedTags.size());
        assertEquals("21", importedTags.get(0).getId());
    }
    
}
