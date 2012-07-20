package com.googlecode.fspotcloud.user.emailconfirmation;

import com.google.common.annotations.VisibleForTesting;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.googlecode.fspotcloud.server.model.api.User;
import com.googlecode.fspotcloud.server.model.api.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@Singleton
public class ConfirmationServlet extends HttpServlet {
    @VisibleForTesting
    @Inject
    UserDao userDao;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String secret = request.getParameter("secret");
        String email = request.getParameter("email");
        User user = userDao.find(email);
        final String storedSecret = user.emailVerificationSecret();
        Logger.getAnonymousLogger().info("EM: " + email + " secret: " + secret + " stored-s: " + storedSecret);
        PrintWriter out = response.getWriter();
        if (secret.equals(storedSecret)) {
            user.setEnabled(true);
            userDao.save(user);
            out.println("Success");
            out.close();
        } else {
            out.println("Failure");
            out.close();
        }

    }

}
