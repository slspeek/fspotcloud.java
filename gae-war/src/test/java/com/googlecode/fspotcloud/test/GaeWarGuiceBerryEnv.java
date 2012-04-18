package com.googlecode.fspotcloud.test;

import com.google.inject.name.Names;

public class GaeWarGuiceBerryEnv extends SeleniumGuiceBerryEnv {

    @Override
    protected void configure() {
        super.configure();
        bind(ILogin.class).to(GaeLoginBot.class);
        bind(String.class).annotatedWith(Names.named("baseUrl")).toInstance("http://localhost:8080");
    }
}
