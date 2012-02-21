package fspotcloud.model.api.test.peerdatabase;



import com.google.guiceberry.junit4.GuiceBerryRule;
import fspotcloud.model.api.test.EmptyGuiceBerryEnv;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.shared.tag.TagNode;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class PeerDatabaseManagerTest {

    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(EmptyGuiceBerryEnv.class);
    @Inject
    private PeerDatabases manager;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void resetCachedTagTree() {
        PeerDatabase defaultPD = manager.get();
        List<TagNode> list = defaultPD.getCachedTagTree();
        assertNull(list);
        list = new ArrayList<TagNode>();
        defaultPD.setCachedTagTree(list);
        manager.save(defaultPD);
        defaultPD = null;
        defaultPD = manager.get();
        list = defaultPD.getCachedTagTree();
        assertNotNull(list);
        defaultPD.setCachedTagTree(null);
        assertNull(defaultPD.getCachedTagTree());
        manager.save(defaultPD);
        defaultPD = null;
        defaultPD = manager.get();
        list = defaultPD.getCachedTagTree();
        assertNull(list);
        
    }
    @Test
    public void testGet() {
        PeerDatabase defaultPD = manager.get();
        assertNotNull(defaultPD);
        PeerDatabase secondInstance = manager.get();
        assertNotNull(secondInstance);
    }
    
    @Test public void testSave() {
        PeerDatabase defaultPD = manager.get();
        manager.save(defaultPD);
    }

    @Test
    public void testGetCachedTagTree() {
        PeerDatabase defaultPD = manager.get();
        List<TagNode> list = defaultPD.getCachedTagTree();
        assertNull(list);
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
