package persistence;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.google.common.annotations.VisibleForTesting;
import model.DBUser;

public class UserPersistence {


    private Mapper<DBUser> mapper;
    private Session session;

    public void init(){
        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").withPort(9042).build();
        session = cluster.connect();
        MappingManager manager = new MappingManager(session);
        mapper = manager.mapper(DBUser.class);
    }

    public void saveUser(DBUser user){
       mapper.save(user);
    }

    public DBUser queryUser(String login){
        return mapper.get(login);
    }

    @VisibleForTesting
    protected void setMapper(Mapper<DBUser> mapper) {
        this.mapper = mapper;
    }
}
