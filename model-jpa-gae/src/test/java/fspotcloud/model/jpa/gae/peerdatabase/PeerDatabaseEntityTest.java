/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.model.jpa.gae.peerdatabase;

import fspotcloud.shared.tag.TagNode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author steven
 */
public class PeerDatabaseEntityTest {

    public PeerDatabaseEntityTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testResetCachedTagTree() {
        List<TagNode> cachedTagTree = new ArrayList<TagNode>();
        PeerDatabaseEntity instance = new PeerDatabaseEntity();
        instance.setCachedTagTree(cachedTagTree);
        instance.setCachedTagTree(null);
        assertNull(instance.getCachedTagTree());
    }
}
