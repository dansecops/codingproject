package mrbeans;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.util.Set;


public class ViewUser {

    private String login;
    private String password;



    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DBUser convert(){
        DBUser dbUser = new DBUser(login);
        dbUser.setPassword(password);
        dbUser.setEmail(email);
        return dbUser;

    }

    @Override
    public String toString() {
        return "ViewUser{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
