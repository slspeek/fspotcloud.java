package fspotcloud.test;

import junit.framework.TestCase;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
public class Mocktest extends TestCase {

	Foo fooMock;
	Foo foo;
	Foo fooSpy;
	
	protected void setUp() throws Exception {
		super.setUp();
		fooMock = mock(Foo.class);
		foo = new Foo();
		fooSpy = spy(new Foo());
		
	}

	public void testne() {
		fooMock.climb();
		verify(fooMock).climb();
	}
	
	public void testOne() {
		foo.climb();
	}
	public void testSpy() {
		fooSpy.climb();
		verify(fooSpy).climb();
	}
}

class Foo {
	void climb() {System.out.println("climbing in class");};
}