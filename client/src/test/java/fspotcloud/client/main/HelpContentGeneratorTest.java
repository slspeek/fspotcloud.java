package fspotcloud.client.main;

import junit.framework.TestCase;

public class HelpContentGeneratorTest extends TestCase {

	private HelpContentGenerator gen;

	@Override
	protected void setUp() throws Exception {
		gen = new HelpContentGenerator();
		super.setUp();
	}

	public void testOne() {
		String row = gen.getHelpRow("s", null, null, "Start slideshow");
		String desired = "<tr><td><span class='fsc-help-key'>s</span></td>" 
			 + "<td><span class='fsc-help-separator'>:</span></td>"
			 + "<td><span class='fsc-help-description'>Start slideshow</span></td></tr>";
		assertEquals(desired, row);
	}
}
