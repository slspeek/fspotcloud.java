/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.server.control.task.actions.intern;

import com.googlecode.fspotcloud.shared.peer.TagUpdate;
import com.googlecode.fspotcloud.test.EqualsTest;
import static com.google.common.collect.Lists.newArrayList;

/**
 *
 * @author steven
 */
public class TagUpdateActionEqualsTest extends EqualsTest<TagUpdateAction>{

    @Override
    protected TagUpdateAction getOne() {
        return new TagUpdateAction(newArrayList(new TagUpdate("1")));
    }

    @Override
    protected TagUpdateAction getTheOther() {
        return new TagUpdateAction(newArrayList(new TagUpdate("1")));
    }

    @Override
    protected TagUpdateAction getDifferentOne() {
        return new TagUpdateAction(newArrayList(new TagUpdate("1"), new TagUpdate("2")));
    }
    
    
    
}
