package fspotcloud.client.main;

import fspotcloud.client.view.action.KeyStroke;
import fspotcloud.client.view.action.Shortcut;
import junit.framework.TestCase;

public class HelpContentGeneratorTest extends TestCase {

	private HelpContentGenerator gen;

	@Override
	protected void setUp() throws Exception {
		gen = new HelpContentGenerator();
		super.setUp();
	}

	public void testOne() {
		Shortcut s = new Shortcut("Start slideshow", new KeyStroke('s'), null);
		String row = gen.getHelpText(s);
		String desired = "<span class='fsc-help-key'>s</span></td>" 
			 + "<td><span class='fsc-help-separator'>:</span></td>"
			 + "<td><span class='fsc-help-description'>Start slideshow</span>";
		assertEquals(desired, row);
	}
}
