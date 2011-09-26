package fspotcloud.botdispatch.bot;

import net.customware.gwt.dispatch.shared.Action;

public class TestAction implements Action<TestResult> {

	private final String name;

	public TestAction(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
