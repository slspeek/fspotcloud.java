package fspotcloud.client.view;

import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.Widget;

public class PagerViewDummy implements PagerView {

	final HasEnabled hasEnabled = new HasEnabled() {
		@Override
		public void setEnabled(boolean arg0) {
		}

		@Override
		public boolean isEnabled() {
			return false;
		}
	};

	@Override
	public HasEnabled getFirst() {
		return hasEnabled;
	}

	@Override
	public HasEnabled getLast() {
		return hasEnabled;

	}

	@Override
	public HasEnabled getNext() {
		return hasEnabled;
	}

	@Override
	public HasEnabled getPrevious() {
		return hasEnabled;
	}

	@Override
	public void setPresenter(PagerPresenter pagerPresenter) {
	}

	@Override
	public Widget asWidget() {
		return null;
	}
}
