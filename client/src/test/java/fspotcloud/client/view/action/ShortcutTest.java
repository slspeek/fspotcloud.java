package fspotcloud.client.view.action;

import junit.framework.TestCase;

import com.google.gwt.event.dom.client.KeyCodes;

public class ShortcutTest extends TestCase {

	String id = "debug-id";
	KeyStroke key1 = new KeyStroke('h');
	KeyStroke key2 = new KeyStroke(KeyCodes.KEY_BACKSPACE);
	String caption = "Help";
	String description = "Press h to see help";
	UserActionImpl userActionImpl;

	public void testShortcut() {
		userActionImpl = new UserActionImpl(id, caption, description, key1, key2, null, null, null);
		assertNotNull(userActionImpl);
	}

	public void testGetDescription() {
		userActionImpl = new UserActionImpl(id, caption, description, key1, key2, null, null, null);
		assertEquals(description, userActionImpl.getDescription());
	}

	public void testGetKey() {
		userActionImpl = new UserActionImpl(id, caption, description, key1, key2, null, null, null);
		assertEquals(key1, userActionImpl.getKey());
	}

	public void testGetAlternateKey() {
		userActionImpl = new UserActionImpl(id, caption, description, key1, key2, null, null, null);
		assertEquals(key2, userActionImpl.getAlternateKey());
	}

	public void testGetIcon() {
		userActionImpl = new UserActionImpl(id, caption, description, key1, key2, null, null, null);
		assertNull(userActionImpl.getIcon());
	}

	public void testGetCaption() {
		userActionImpl = new UserActionImpl(id, caption, description, key1, key2, null, null, null);
		assertEquals(caption, userActionImpl.getCaption());
	}

	public void testGetId() {
		userActionImpl = new UserActionImpl(id, caption, description, key1, key2, null, null, null);
		assertEquals(id, userActionImpl.getId());
	}

}
