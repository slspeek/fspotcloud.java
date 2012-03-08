package com.googlecode.fspotcloud.server.model.tag;

import java.util.List;

import com.google.common.collect.ImmutableList;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import com.googlecode.fspotcloud.server.main.TreeBuilder;
import com.googlecode.fspotcloud.shared.tag.TagNode;

public class TreeBuilderTest extends TestCase {

	public static TestSuite suite() {
		return new TestSuite(TreeBuilderTest.class);
	}

	List<TagNode> nodes;

	protected void setUp() throws Exception {
		TagNode root = new TagNode("1", "0");
		TagNode level1_a = new TagNode("2", "1");
		TagNode level2_b = new TagNode("3", "1");
		level2_b.setImportIssued(true);
		TagNode secondRoot = new TagNode("4", "0");
		secondRoot.setImportIssued(true);
		nodes = ImmutableList.of(root, level1_a, level2_b, secondRoot);
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testVerySimpleTree() {
		TreeBuilder builder = new TreeBuilder(nodes);
		List<TagNode> trees = builder.getRoots();
		assertEquals(2, trees.size());
		TagNode root = trees.get(0);
		List<TagNode> level_1s = root.getChildren();
		assertEquals(2, level_1s.size());
	}

	public void testPublicTreeSimpleTree() {
		TreeBuilder builder = new TreeBuilder(nodes);
		List<TagNode> trees = builder.getPublicRoots();
		assertEquals(2, trees.size());
		TagNode root = trees.get(0);
		List<TagNode> level_1s = root.getChildren();
		assertEquals(0, level_1s.size());
	}

}
