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

import com.google.common.base.Objects;
import com.openpojo.business.annotation.BusinessKey;
import net.customware.gwt.dispatch.shared.Result;

import java.io.Serializable;


public class FullsizePhotoResult extends BusinessBase implements Result,
    Serializable {
    private static final long serialVersionUID = -4172714943981557358L;
    @BusinessKey
    private String photoId;
    @BusinessKey
    private byte[] fullsizeImageData;

    public FullsizePhotoResult(String photoId, byte[] fullsizeImageData) {
        this.photoId = photoId;
        this.fullsizeImageData = fullsizeImageData;
    }

    public byte[] getFullsizeImageData() {
        return fullsizeImageData;
    }

    public String getPhotoId() {
        return photoId;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("id", photoId)
                      .add("image len", fullsizeImageData.length).toString();
    }
}
