/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.model.jpa;

import com.google.inject.persist.PersistFilter;
import com.google.inject.servlet.ServletModule;

/**
 *
 * @author steven
 */
public class ModelServletModule extends ServletModule {

    @Override
    protected void configureServlets() {
        super.configureServlets();
        filter("/*").through(PersistFilter.class);
    }
}
