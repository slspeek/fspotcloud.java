/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.fspotcloud.server.control.task.actions.intern;

import static org.junit.Assert.*;
import static com.google.common.collect.Lists.*;
import com.googlecode.fspotcloud.test.EqualsTest;

/**
 *
 * @author steven
 */
public class ImportManyTagsPhotosActionTest extends EqualsTest<ImportManyTagsPhotosAction> {

    @Override
    protected ImportManyTagsPhotosAction getOne() {
        return new ImportManyTagsPhotosAction(newArrayList("2"));
    }

    @Override
    protected ImportManyTagsPhotosAction getTheOther() {
        return new ImportManyTagsPhotosAction(newArrayList("2"));
    }

    @Override
    protected ImportManyTagsPhotosAction getDifferentOne() {
        return new ImportManyTagsPhotosAction(newArrayList("2", "3"));
    }
    
  
}