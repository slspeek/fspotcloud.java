package fspotcloud.test;

import junit.framework.TestCase;

import org.mockito.MockitoAnnotations;

public class MockitoTestCase extends TestCase {

	@Override
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		super.setUp();
	}

}
