/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.fspotcloud.server.control.task.actions.intern;

import org.junit.Test;
import static org.junit.Assert.*;
import javax.inject.Inject;
import org.jukito.JukitoRunner;
import org.junit.runner.RunWith;
import static com.google.common.collect.Lists.*;
import com.google.inject.name.Names;
import com.googlecode.fspotcloud.shared.peer.TagRemovedFromPeer;
import com.googlecode.fspotcloud.test.EqualsTest;
import java.util.List;
import org.jukito.JukitoModule;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.mockito.Mockito.*;

/**
 *
 * @author steven
 */
@RunWith(JukitoRunner.class)
public class RemoveTagsDeletedFromPeerActionTest extends EqualsTest<RemoveTagsDeletedFromPeerAction>{

    @Override
    protected RemoveTagsDeletedFromPeerAction getOne() {
        return new RemoveTagsDeletedFromPeerAction(newArrayList(new TagRemovedFromPeer("1")));
    }

    @Override
    protected RemoveTagsDeletedFromPeerAction getTheOther() {
        return new RemoveTagsDeletedFromPeerAction(newArrayList(new TagRemovedFromPeer("1")));
    }

    @Override
    protected RemoveTagsDeletedFromPeerAction getDifferentOne() {
        return new RemoveTagsDeletedFromPeerAction(newArrayList(new TagRemovedFromPeer("2")));
    }

}