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
            
package com.googlecode.fspotcloud.server.control.callback;

import com.google.inject.Inject;
import com.googlecode.botdispatch.SerializableAsyncCallback;
import com.googlecode.fspotcloud.server.model.api.Photo;
import com.googlecode.fspotcloud.server.model.api.PhotoDao;
import com.googlecode.fspotcloud.shared.peer.FullsizePhotoResult;
import com.googlecode.simpleblobstore.BlobKey;
import com.googlecode.simpleblobstore.BlobService;


public class FullsizePhotoCallback implements SerializableAsyncCallback<FullsizePhotoResult> {
    private static final long serialVersionUID = 246810426240427570L;
    @Inject
    private transient PhotoDao photoManager;
    @Inject
    private transient BlobService blobService;

    public FullsizePhotoCallback(PhotoDao photoManager, BlobService blobService) {
        this.photoManager = photoManager;
        this.blobService = blobService;
    }

    @Override
    public void onFailure(Throwable caught) {
    }

    @Override
    public void onSuccess(FullsizePhotoResult fullsizePhotoResult) {
        Photo photo = photoManager.find(fullsizePhotoResult.getPhotoId());
        byte[] image = fullsizePhotoResult.getFullsizeImageData();
        BlobKey key = blobService.save("img/jpeg", image);
        photo.setFullsizeImageBlobKey(key.getKeyString());
        photo.setFullsizeLoaded(true);
        photoManager.save(photo);
    }
}
