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
package com.googlecode.fspotcloud.server.model.api;

import com.googlecode.simplejpadao.HasSetKey;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


public interface User extends HasSetKey<String>, Serializable {
    String getEmail();

    void setEmail(String email);

    String getNickname();

    void setNickname(String nickname);

    String getCredentials();

    void setCredentials(String credentials);

    boolean getEnabled();

    void setEnabled(boolean enabled);

    List<Long> getPeers();

    void setPeers(List<Long> peers);

    List<Long> getUserGroups();

    void setUserGroups(List<Long> userGroups);

    Type getUserType();

    void setUserType(Type userType);

    Date getLastLoginTime();

    void setLastLoginTime(Date loginTime);

    void touchLastLoginTime();

    String emailVerificationSecret();

    void setEmailVerificationSecret(String secret);
    enum Type {GOOGLE_USER, REGULAR_USER;
    }
}