package fspotcloud.client.view;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

public class AppActivityMapperTest extends TestCase {

	MainWindowActivityMapper mapper;
	Mockery context = new Mockery();

	protected void setUp() throws Exception {
		super.setUp();

	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	public void testGetTagActivity() {
		final TagView.TagPresenter activity = context.mock(TagView.TagPresenter.class);
		final TagViewingPlace place = new TagViewingPlace("1", "1");
		context.checking(new Expectations() {
			{
				oneOf(activity).setPlace(with(place));
			}
		});
		mapper = new MainWindowActivityMapper(activity, null);
		mapper.getActivity(place);
		context.assertIsSatisfied();
	}

}
