package fspotcloud.botdispatch.model;

import javax.jdo.PersistenceManager;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.command.CommandManager;

public class ModelModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Commands.class).to(CommandManager.class).in(Singleton.class);
		bind(PersistenceManager.class).toProvider(
				PersistenceManagerProvider.class);
	}
}
