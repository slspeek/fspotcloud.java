/*
 * Copyright 2010-2012 Steven L. Speek.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.server.control.task.actions.intern;

import static com.google.common.collect.Lists.newArrayList;
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
