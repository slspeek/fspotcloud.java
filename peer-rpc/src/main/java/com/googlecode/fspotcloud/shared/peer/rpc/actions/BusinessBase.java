/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.shared.peer.rpc.actions;

import com.openpojo.business.BusinessIdentity;

/**
 *
 * @author steven
 */
public abstract class BusinessBase {
     @Override
    public boolean equals(Object obj) {
        return BusinessIdentity.areEqual(this, obj);
    }

    @Override
    public int hashCode() {
        return BusinessIdentity.getHashCode(this);
    }

    @Override
    public String toString() {
        return BusinessIdentity.toString(this);
    }
    
}
