package com.googlecode.fspotcloud.server.main.handler;

import com.googlecode.fspotcloud.shared.main.TagNode;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TagTreeHelperTest {

    public static final String ROOT = "ROOT";
    public static final String CHILD = "CHILD";

    @Test
    public void testGetSubTree() throws Exception {
        TagNode root = new TagNode(ROOT);
        TagNode child = new TagNode(CHILD, ROOT);
        root.addChild(child);
        child.setParent(root);

        TagTreeHelper helper = new TagTreeHelper(newArrayList(root), newHashSet(ROOT) );
        List<TagNode> result = helper.getSubTree();
        assertEquals(1, result.size());
        TagNode root1 = result.get(0);
        assertTrue(root1.getChildren().isEmpty());
        assertEquals(ROOT, root1.getId());
    }

    @Test
    public void testGetSubTree2() throws Exception {
        TagNode root = new TagNode(ROOT);
        TagNode child = new TagNode(CHILD, ROOT);
        root.addChild(child);
        child.setParent(root);

        TagTreeHelper helper = new TagTreeHelper(newArrayList(root), newHashSet(ROOT, CHILD));
        List<TagNode> result = helper.getSubTree();
        assertEquals(1, result.size());
        TagNode root1 = result.get(0);
        assertEquals(ROOT, root1.getId());
        TagNode child1 = root1.getChildren().get(0);
        assertEquals(CHILD, child1.getId());
    }
}
