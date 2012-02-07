/**
 *
 */
package fspotcloud.model.jpa.photo;

import fspotcloud.server.model.api.Photo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * @author slspeek@gmail.com
 *
 */
@Entity
public class PhotoEntity extends PhotoEntityBase implements Photo, Serializable {

    @Lob
    private byte[] image;
    @Lob
    private byte[] thumb;

    @Override
    public void setThumb(byte[] thumb) {
        this.thumb = thumb;
    }

    @Override
    public byte[] getThumb() {
        return thumb;
    }

    @Override
    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public byte[] getImage() {
        return image;
    }
}
