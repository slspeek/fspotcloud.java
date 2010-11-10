package fspotcloud.client.main.ui;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;
public class SimpleLayoutPanel extends LayoutPanel implements AcceptsOneWidget {
    private IsWidget widget = null;
    @Override
    public void setWidget(IsWidget w) {
        if (widget != null) {
            super.remove(widget);
        }
        widget = w;
        super.add(w);
    }
    // We do this to prevent the LayoutPanel from being used
    // as a normal LayoutPanel (which can hold multiple widgets)
    // and will mess up our logic in setWidget() above.
    public void add(Widget w) {
        setWidget(w);
    }
    // This isn't supported either with the SimpleLayoutPanel,
    // so we just call setWidget() and ignore the index entirely.
    @Override
    public void insert(Widget w, int beforeIndex) {
        setWidget(w);
    }
    // There are other methods inherited from ComplexPanel
    // that to be complete should be overridden, but this should
    // suffice for now.

}
