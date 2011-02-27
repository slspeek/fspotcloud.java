package fspotcloud.client.view;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.gwt.user.client.rpc.AsyncCallback;

import junit.framework.TestCase;
import fspotcloud.client.data.DataManager;
import fspotcloud.client.data.DataManagerImpl;
import fspotcloud.client.data.IndexingUtil;
import fspotcloud.client.main.TagServiceAsyncTestImpl;
import fspotcloud.shared.tag.TagNode;

public class PagerActivityMapperTest extends TestCase {

	Mockery context;
	
	protected void setUp() throws Exception {
		super.setUp();
		context = new Mockery();
	}

	private DataManager createDataManager() {
		TagServiceAsyncTestImpl testRpc = new TagServiceAsyncTestImpl();
		DataManager dm = new DataManagerImpl(testRpc, new IndexingUtil());
		dm.getTagTree(new AsyncCallback<List<TagNode>>() {
			@Override
			public void onSuccess(List<TagNode> arg0) {
			}
			@Override
			public void onFailure(Throwable arg0) {
			}
		});
		return dm;
	}
	
	private PagerActivity createPagerActivity(PlaceGoTo goTo) {
		return new PagerActivity(null, goTo);
	}
	
	private PagerActivityMapper create(PlaceGoTo goTo) {
		return new PagerActivityMapper(createPagerActivity(goTo), createDataManager());
	}
	
	public void testGetActivity() {
		final PlaceGoTo goTo = context.mock(PlaceGoTo.class);
		ImageViewingPlace imageViewingPlace = new ImageViewingPlace("1", "2");
		PagerActivityMapper mapper = create(goTo);
		PagerActivity pager = (PagerActivity) mapper.getActivity(imageViewingPlace);
		context.checking(new Expectations() {
			{
				oneOf(goTo).goTo(with(new ImageViewingPlace("1", "3")));
			}
		});
		pager.go(true);
		context.assertIsSatisfied();
	}

}
