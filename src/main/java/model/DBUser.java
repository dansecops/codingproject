package model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

/*
*
CREATE TABLE testdb.users (
    userid text PRIMARY KEY,
    emails set<text>,
    first_name text,
    last_name text,
    todo map<timestamp, text>,
    top_scores list<int>
)
* */



@Table(keyspace = "testdb", name = "users")
public class DBUser {

    @PartitionKey
    @Column
    private String userid;

    @Column
    //TODO: use hash to store password
    private String password;

    @Column
    private String email;


    public DBUser() {
    }

    public DBUser(String userid) {
        this.userid = userid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserid() {
        return userid;
    }

    public String getPassword() {
        return password;
    }
}
