package fspotcloud.client.admin;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Admin implements EntryPoint {

		private DecoratorPanel layout = new DecoratorPanel(); 
	  private VerticalPanel mainPanel = new VerticalPanel();
	  private HorizontalPanel titlePanel = new HorizontalPanel();
	  private HorizontalPanel actionPanel = new HorizontalPanel();
	  private Button photoCountButton = new Button("Get count");
	  private Label photoCountLabel = new Label();
	  private Label titleLabel = new Label("F-Spot Cloud Admin");
	  private final AdminServiceAsync tagService = GWT.create(AdminService.class);
	@Override
	public void onModuleLoad() {
		titlePanel.add(titleLabel);
		titleLabel.addStyleDependentName("title");
		DecoratorPanel titleDecPanel = new DecoratorPanel();
		titleDecPanel.setWidget(titlePanel);
		mainPanel.add(titleDecPanel);
		mainPanel.add(actionPanel);
		layout.setWidget(mainPanel);
		photoCountLabel.setWidth("10cm");
		actionPanel.add(photoCountLabel);
		actionPanel.add(photoCountButton);
		photoCountButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				tagService.getPhotoCount(new AsyncCallback<Integer>() {
					
					@Override
					public void onSuccess(Integer result) {
						// TODO Auto-generated method stub
						photoCountLabel.setTitle("Succes");
						photoCountLabel.setText("Photo count: " + result);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						photoCountLabel.setText("Error in remote communication");
					}
				});
			}
		});
		 RootPanel.get().add(layout);

		
	}
}