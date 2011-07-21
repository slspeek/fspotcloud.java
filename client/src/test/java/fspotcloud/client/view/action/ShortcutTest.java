package fspotcloud.client.view.action;

import junit.framework.TestCase;

import com.google.gwt.event.dom.client.KeyCodes;

public class ShortcutTest extends TestCase {

	String id = "debug-id";
	KeyStroke key1 = new KeyStroke('h');
	KeyStroke key2 = new KeyStroke(KeyCodes.KEY_BACKSPACE);
	String caption = "Help";
	String description = "Press h to see help";
	Shortcut shortcut;

	public void testShortcut() {
		shortcut = new Shortcut(id, caption, description, key1, key2, null, null, null);
		assertNotNull(shortcut);
	}

	public void testGetDescription() {
		shortcut = new Shortcut(id, caption, description, key1, key2, null, null, null);
		assertEquals(description, shortcut.getDescription());
	}

	public void testGetKey() {
		shortcut = new Shortcut(id, caption, description, key1, key2, null, null, null);
		assertEquals(key1, shortcut.getKey());
	}

	public void testGetAlternateKey() {
		shortcut = new Shortcut(id, caption, description, key1, key2, null, null, null);
		assertEquals(key2, shortcut.getAlternateKey());
	}

	public void testGetIcon() {
		shortcut = new Shortcut(id, caption, description, key1, key2, null, null, null);
		assertNull(shortcut.getIcon());
	}

	public void testGetCaption() {
		shortcut = new Shortcut(id, caption, description, key1, key2, null, null, null);
		assertEquals(caption, shortcut.getCaption());
	}

	public void testGetId() {
		shortcut = new Shortcut(id, caption, description, key1, key2, null, null, null);
		assertEquals(id, shortcut.getId());
	}

}
