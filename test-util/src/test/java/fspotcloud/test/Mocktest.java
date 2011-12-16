package fspotcloud.test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.Spy;
import org.testng.annotations.Test;
public class Mocktest  extends MockitoTestCase {

	@Mock
	Foo fooMock;
	Foo foo = new Foo();
	@Spy
	Foo fooSpy;

	@Test
	public void testne() {
		fooMock.climb();
		verify(fooMock).climb();
	}
	
	@Test
	public void testOne() {
		foo.climb();
	}
	
	@Test
	public void testSpy() {
		fooSpy.climb();
		verify(fooSpy).climb();
	}
}

class Foo {
	void climb() {System.out.println("climbing in class");};
}