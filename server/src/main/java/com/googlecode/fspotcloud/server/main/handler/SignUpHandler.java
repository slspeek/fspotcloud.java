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
package com.googlecode.fspotcloud.server.main.handler;

import com.google.inject.Inject;
import com.googlecode.fspotcloud.server.mail.IMail;
import com.googlecode.fspotcloud.server.model.api.User;
import com.googlecode.fspotcloud.server.model.api.UserDao;
import com.googlecode.fspotcloud.shared.main.SignUpAction;
import com.googlecode.fspotcloud.shared.main.SignUpResult;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;


public class SignUpHandler extends SimpleActionHandler<SignUpAction, SignUpResult> {
    private final UserDao userDao;
    private final IMail mailer;

    @Inject
    public SignUpHandler(UserDao userDao, IMail mailer) {
        this.userDao = userDao;
        this.mailer = mailer;
    }

    @Override
    public SignUpResult execute(SignUpAction action, ExecutionContext context)
        throws DispatchException {
        User mayBeExisted = userDao.findOrNew(action.getEmail());

        if (!mayBeExisted.hasRegistered()) {
            mayBeExisted.setNickname(action.getNickname());
            mayBeExisted.setCredentials(action.getPassword());
            mayBeExisted.setRegistered(true);
            userDao.save(mayBeExisted);
            mailer.send(action.getEmail(), "Hi", "Hello mail!");
            return new SignUpResult(true);
        } else {
            return new SignUpResult(false);
        }
    }
}
