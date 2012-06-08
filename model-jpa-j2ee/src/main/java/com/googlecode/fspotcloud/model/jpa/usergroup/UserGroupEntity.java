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
package com.googlecode.fspotcloud.model.jpa.usergroup;

import static com.google.common.collect.Lists.newArrayList;
import com.googlecode.fspotcloud.server.model.api.UserGroup;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;


/**
 * DOCUMENT ME!
 *
 * @author slspeek@gmail.com
*/
@Entity
public class UserGroupEntity implements UserGroup, Serializable {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @Basic
    private String owner;
    @Basic
    private ArrayList<String> grantedUsers;
    @Basic
    private String name;
    @Basic
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public List<String> getGrantedUsers() {
        return grantedUsers;
    }

    @Override
    public void setGrantedUsers(List<String> users) {
        this.grantedUsers = newArrayList(users);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String getOwner() {
        return owner;
    }
}
