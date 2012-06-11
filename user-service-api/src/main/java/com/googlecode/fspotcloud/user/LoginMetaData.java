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
package com.googlecode.fspotcloud.user;

import static com.google.common.collect.Sets.newHashSet;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class LoginMetaData {
    private Type loginType;
    private Date lastTime;
    private String email;
    private HashSet<Long> grantedUserGroups = newHashSet();

    public HashSet<Long> getGrantedUserGroups() {
        return grantedUserGroups;
    }

    public void setGrantedUserGroups(HashSet<Long> grantedUserGroups) {
        this.grantedUserGroups = grantedUserGroups;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Type getLoginType() {
        return loginType;
    }

    public void setLoginType(Type loginType) {
        this.loginType = loginType;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
    public enum Type {REGULAR_LOGIN, OPEN_ID_LOGIN, GAE_LOGIN;
    }
}
