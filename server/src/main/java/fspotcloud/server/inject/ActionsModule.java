package fspotcloud.server.inject;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import fspotcloud.server.actions.GetMetaDataHandler;
import fspotcloud.shared.actions.GetMetaData;

public class ActionsModule extends ActionHandlerModule {

    @Override
    protected void configureHandlers() {
        bindHandler(GetMetaData.class, GetMetaDataHandler.class);
    }

}