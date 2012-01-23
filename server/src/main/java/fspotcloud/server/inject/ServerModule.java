package fspotcloud.server.inject;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class ServerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Integer.class).annotatedWith(Names.named("maxTicks")).toInstance(
                new Integer(100));
    }
}
