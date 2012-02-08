/**
 *
 */
package fspotcloud.model.jpa.gae.tag;

import fspotcloud.model.jpa.tag.TagEntityBase;
import fspotcloud.server.model.api.Tag;
import fspotcloud.shared.photo.PhotoInfo;
import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * Represents a Label in F-Spot
 *
 */
@Entity
public class TagEntity extends TagEntityBase {

     @Lob//@Column(columnDefinition = "BLOB")
    private TreeSet<PhotoInfo> cachedPhotoList = new TreeSet<PhotoInfo>();

    public TagEntity() {}


    @Override
    public void setCachedPhotoList(TreeSet<PhotoInfo> cachedPhotoList) {
        this.cachedPhotoList = cachedPhotoList;
    }

    @Override
    public TreeSet<PhotoInfo> getCachedPhotoList() {
        return cachedPhotoList;
    }

    }
