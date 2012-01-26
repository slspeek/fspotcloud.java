package fspotcloud.server.model.tag;

import com.google.common.collect.ImmutableList;
import com.google.guiceberry.junit4.GuiceBerryRule;
import com.google.inject.Inject;
import fspotcloud.model.jpa.tag.TagEntity;
import fspotcloud.server.model.GaeModelGuiceberryEnv;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.tag.TagNode;
import java.util.List;
import java.util.logging.Logger;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class TagManagerTest {

    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(GaeModelGuiceberryEnv.class);
    @Inject
    private Tags tagManager;
    private static final Logger log = Logger.getLogger(TagManagerTest.class.getName());

    @After
    public void setUp() throws Exception {
        tagManager.deleteAll(tagManager.getTagKeys());
    }

   
    @Test
    public void testGetTags() {
        createSaveTag("21");
        List<TagNode> tags = tagManager.getTags();
        TagNode node = tags.get(0);
        assertEquals("21", node.getId());
    }

    @Test
    public void testDeleteAllTags() {
        createSaveTag("20");
        createSaveTag("21");
        createSaveTag("22");
        assertEquals(3, tagManager.getTagKeys().size());
        List<String> list = ImmutableList.of("20", "21", "22");
        tagManager.deleteAll(list);
        log.info("keys:" + tagManager.getTagKeys());
        assertTrue(tagManager.isEmpty());
        if(tagManager.getById("21") != null)
            fail();
        
    }

    @Test
    public void testDeleteAllTags2() {
        createSaveTag("20");
        createSaveTag("21");
        createSaveTag("22");
        assertEquals(3, tagManager.getTagKeys().size());
        List<String> list = ImmutableList.of("20", "21", "22");
        tagManager.deleteAll();
        log.info("keys after deleteAll:" + tagManager.getTagKeys());
        assertTrue(tagManager.isEmpty());
        if(tagManager.getById("21") != null)
            fail();
        
    }

    private Tag createSaveTag(String id) {
        Tag tag = new TagEntity();
        tag.setId(id);
        tagManager.save(tag);
        return tag;
    }

    @Test
    public void testIsEmpty() {
        assertTrue(tagManager.isEmpty());
    }

    @Test
    public void testIsNotEmpty() {
        createSaveTag("0");
        assertFalse(tagManager.isEmpty());
    }

    @Test
    public void testGetKeys() {
        createSaveTag("0");
        assertEquals("0", tagManager.getTagKeys().get(0));
    }
}
