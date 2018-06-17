import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.util.Set;

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
    private Set<String> emails;

    public DBUser(String userid, Set<String> emails) {
        this.userid = userid;
        this.emails = emails;
    }
}
