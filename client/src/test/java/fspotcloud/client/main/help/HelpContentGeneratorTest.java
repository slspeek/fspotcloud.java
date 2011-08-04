package fspotcloud.client.main.help;

import junit.framework.TestCase;
import fspotcloud.client.main.help.HelpContentGenerator;
import fspotcloud.client.view.action.KeyStroke;
import fspotcloud.client.view.action.Shortcut;

public class HelpContentGeneratorTest extends TestCase {

	private HelpContentGenerator gen;

	@Override
	protected void setUp() throws Exception {
		gen = new HelpContentGenerator();
		super.setUp();
	}

	public void testOne() {
		Shortcut s = new Shortcut("", "","Start slideshow", new KeyStroke('s'), null, null, null, null);
		String row = gen.getHelpText(s);
		String desired = "<span class='fsc-help-key'>s</span></td>" 
			 + "<td><span class='fsc-help-separator'>:</span></td>"
			 + "<td><span class='fsc-help-description'>Start slideshow</span>";
		assertEquals(desired, row);
	}
}
