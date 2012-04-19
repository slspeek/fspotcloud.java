package com.googlecode.fspotcloud.test;

import com.google.inject.name.Names;

public class J2eeWarGuiceBerryEnv extends SeleniumGuiceBerryEnv {

    @Override
    protected void configure() {
        super.configure();
        bind(ILogin.class).to(NoLoginBot.class);
        bind(String.class).annotatedWith(Names.named("baseUrl")).toInstance("http://localhost:8080/j2ee-war-noauth");
    }
}
