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
/**
 *
 */
package com.googlecode.fspotcloud.model.jpa.gae.user;

import com.googlecode.fspotcloud.server.model.api.User;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;


/**
 * DOCUMENT ME!
 *
 * @author slspeek@gmail.com
*/
@Entity
public class UserEntity implements User, Serializable {
    @Id
    String id; //email-address
    private String credentials;
    private String nickname;
    private String emailVerificationSecret;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastLoginTime;
    private boolean enabled;

    //Persistence demands this
    UserEntity() {
    }

    public UserEntity(String email) {
        setEmail(email);
    }

    public String getEmail() {
        return getId();
    }

    public void setEmail(String email) {
        setId(email);
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public List<Long> getPeers() {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setPeers(List<Long> peers) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Long> getUserGroups() {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setUserGroups(List<Long> userGroups) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Type getUserType() {
        return null; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setUserType(Type userType) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Date getLastLoginTime() {
        return lastLoginTime; //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setLastLoginTime(Date loginTime) {
        this.lastLoginTime = loginTime;
    }

    @Override
    public void touchLastLoginTime() {
        setLastLoginTime(new Date());
    }

    @Override
    public String emailVerificationSecret() {
        return emailVerificationSecret;
    }

    @Override
    public void setEmailVerificationSecret(String secret) {
        this.emailVerificationSecret = secret;
    }

    @Override
    public void setId(String aLong) {
        this.id = aLong;
    }

    @Override
    public String getId() {
        return id;
    }
}
