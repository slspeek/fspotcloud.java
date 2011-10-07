package fspotcloud.botdispatch.model;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.command.CommandManager;

public class MinimalCommandModelModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Commands.class).to(CommandManager.class).in(Singleton.class);
	}
}
