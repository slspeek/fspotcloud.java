/*
 * Copyright 2010-2012 Steven L. Speek.
 * This program is free software; you can redistribute it
                and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free
                Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is
                distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied
                warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public
                License for more details.
 * You should have received a copy of the GNU General Public License
 * along
                with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330,
                Boston, MA 02111-1307, USA.
 *
 */

package com.googlecode.fspotcloud.shared.peer;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static com.googlecode.fspotcloud.test.Serialization.testSerialization;

public class GetPhotoDataActionTest {
    private static final ImageSpecs SPECS = new ImageSpecs(1024, 768, 512, 378);
    private static final List<String> keys = ImmutableList.of("1", "2");
    GetPhotoDataAction action = new GetPhotoDataAction(SPECS, keys);

    @Test
    public void testSerialize2() throws Exception {
        testSerialization(action);
    }
}
