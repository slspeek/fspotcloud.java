package fspotcloud.model.jpa.peerdatabase;

import fspotcloud.shared.tag.TagNode;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.apache.commons.lang.SerializationUtils;

/**
 * Represents a whole F-Spot instance and stores application state
 *
 */
@Entity
public class PeerDatabaseEntity extends PeerDatabaseEntityBase {

    @Lob
    byte[] cachedTagTreeData;
    transient private ArrayList<TagNode> cachedTagTree = null;

    public PeerDatabaseEntity() {
    }

    @Override
    public void setCachedTagTree(List<TagNode> cachedTagTree) {
        if (cachedTagTree == null) {
            this.cachedTagTree = null;
            this.cachedTagTreeData = null;
        } else {
            this.cachedTagTree = new ArrayList<TagNode>(cachedTagTree);
            this.cachedTagTreeData = SerializationUtils.serialize(this.cachedTagTree);
        }
    }

    @Override
    public List<TagNode> getCachedTagTree() {
        if (cachedTagTree == null && cachedTagTreeData != null) {
            cachedTagTree = (ArrayList<TagNode>) SerializationUtils.deserialize(cachedTagTreeData);
        }
        return cachedTagTree;
    }
}
