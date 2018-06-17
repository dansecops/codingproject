import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mrbeans.DBUser;
import mrbeans.ViewUser;
import org.apache.commons.io.IOUtils;
import persistence.UserPersistence;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Register extends HttpServlet {

    public static final Gson GSON = new GsonBuilder().create();
    private static final UserPersistence userPersistence = new UserPersistence();

    @Override
    public void init() throws ServletException {
        userPersistence.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletInputStream inputStream = req.getInputStream();
        String jsonString = IOUtils.toString(inputStream, "utf-8");
        ViewUser viewUser = GSON.fromJson(jsonString, ViewUser.class);
        if(viewUser.getLogin() != null && viewUser.getPassword() != null){
            if(checkAndSave(viewUser)) {
                resp.getWriter().append("Registered");
                resp.setStatus(201);
            }
            else {
                resp.getWriter().append("Did not register");
                resp.setStatus(802);
            }
        } else {
            resp.getWriter().append("Rejected");
            resp.setStatus(802);
        }
    }

    private boolean checkAndSave(ViewUser viewUser) {
        DBUser dbUser = userPersistence.queryUser(viewUser.getLogin());
        if(dbUser == null) {
            userPersistence.saveUser(viewUser.convert());
            return true;
        }
        else return false;
    }
}
