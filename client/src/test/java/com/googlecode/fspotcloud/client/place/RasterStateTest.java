package com.googlecode.fspotcloud.client.place;

import com.googlecode.fspotcloud.client.place.RasterState;
import junit.framework.TestCase;

public class RasterStateTest extends TestCase {

	RasterState state;
	final int WIDTH = 3;
	final int HEIGHT = 2;
	@Override
	protected void setUp() throws Exception {
		state = new RasterState();
		super.setUp();
	}

	public void testSetWidth() {
		state.setColumnCount(WIDTH);
		assertEquals(WIDTH, state.getColumnCount());
	}

	public void testSetHeight() {
		state.setRowCount(HEIGHT);
		assertEquals(HEIGHT, state.getRowCount());
	}


}
