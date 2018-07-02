package service;

import model.DBUser;
import org.apache.commons.io.IOUtils;
import persistence.UserPersistence;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Login extends HttpServlet {
    private static final UserPersistence userPersistence = new UserPersistence();

    @Override
    public void init() throws ServletException {
        userPersistence.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String passwordFromUser = request.getParameter("password");
        DBUser dbUser = userPersistence.queryUser(login);
        if(dbUser == null){
            response.getWriter().append("You are not registered!");
            return;
        }
        if(dbUser.getPassword().equals(passwordFromUser)){
            response.getWriter().append("Logged in");
        } else {
            response.sendRedirect("login.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.html");
    }
}
