/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.model.jpa.peerdatabase;

import com.google.common.collect.Lists;
import fspotcloud.shared.tag.TagNode;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author steven
 */
public class PeerDatabaseEntityTest {
    

    @Test
    public void testGetCachedTagTree() {
        System.out.println("getCachedTagTree");
        PeerDatabaseEntity instance = new PeerDatabaseEntity();
        List expResult = null;
        List result = instance.getCachedTagTree();
        assertEquals(expResult, result);
        TagNode node = new TagNode("1");
        List<TagNode> tree = Lists.newArrayList();
        tree.add(node);
        instance.setCachedTagTree(tree);
        List<TagNode> rTree = instance.getCachedTagTree();
        TagNode rNode = rTree.get(0);
        assertEquals("1", rNode.getId());
    }
}
