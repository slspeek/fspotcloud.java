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
package com.googlecode.fspotcloud.shared.main;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Date;


@GwtCompatible
public class PhotoInfo implements Serializable, Comparable<PhotoInfo> {
    private static final long serialVersionUID = -4084831085611916754L;
    private String id;
    private String description;
    private String exifData;
    private Date date;
    private int version;

    public PhotoInfo(String id, String description, Date date) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.version = 1;
    }

    public PhotoInfo(String id, String description, Date date, int version) {
        this(id, description, date);
        this.version = version;
    }

    public PhotoInfo(String id, String description, Date date, String exifData) {
        this(id, description, date);
        this.exifData = exifData;
    }

    @SuppressWarnings("unused")
    private PhotoInfo() {
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public int hashCode() {
        return id.hashCode();
    }

    public boolean equals(Object o) {
        String id = null;

        if (o instanceof PhotoInfo) {
            PhotoInfo photo = (PhotoInfo) o;
            id = photo.getId();
        }

        return this.id.equals(id);
    }

    public int compareTo(PhotoInfo o) {
        Date otherDate = o.getDate();
        final int dateComparison = this.date.compareTo(otherDate);

        if (dateComparison == 0) {
            String otherId = o.getId();

            return id.compareTo(otherId);
        } else {
            return dateComparison;
        }
    }

    public String toString() {
        return "PhotoInfo(" + id + ")";
    }

    public void setExifData(String exifData) {
        this.exifData = exifData;
    }

    public String getExifData() {
        return exifData;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }
}
