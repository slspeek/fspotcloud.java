package fspotcloud.server.model.peerdatabase;

import com.google.guiceberry.junit4.GuiceBerryRule;
import fspotcloud.server.model.GaeModelGuiceberryEnv;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.shared.tag.TagNode;
import java.util.List;
import javax.inject.Inject;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class PeerDatabaseManagerTest {

    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(GaeModelGuiceberryEnv.class);
    @Inject
    private PeerDatabases manager;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGet() {
        PeerDatabase defaultPD = manager.get();
        assertNotNull(defaultPD);
        PeerDatabase secondInstance = manager.get();
        assertNotNull(secondInstance);
    }

    @Test
    public void testGetCachedTagTree() {
        PeerDatabase defaultPD = manager.get();
        List<TagNode> list = defaultPD.getCachedTagTree();
        //assertNull(list);
    }

    @Test
    public void testDefaultsForThumbDimension() {
        PeerDatabase defaultPD = manager.get();
        String dim = defaultPD.getThumbDimension();
        assertEquals("512x384", dim);
    }

    @Test
    public void testDefaultForGetTagCount() {
        PeerDatabase defaultPD = manager.get();
        int count = defaultPD.getTagCount();
        assertEquals(0, count);
    }
}
