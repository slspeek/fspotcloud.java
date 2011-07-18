package fspotcloud.client.view.action.api;

public interface NavigationActions extends ActionGroup {
	UserAction back();
	UserAction next();
	UserAction home();
	UserAction end();
}
