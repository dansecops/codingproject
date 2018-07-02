package persistence;


import com.datastax.driver.mapping.Mapper;
import model.DBUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserPersistenceTest {

    @Mock
    private Mapper<DBUser> mapper;

    private UserPersistence up = new UserPersistence();

    @Before
    public void setUp() throws Exception {
        up.setMapper(mapper);
    }

    @Test
    public void saveUser() {
        DBUser testuser = new DBUser("1234");
        up.saveUser(testuser);
        verify(mapper, times(1)).save(eq(testuser));
           }

    @Test
    public void queryUser() {
        String login = "1234";
        when(mapper.get(eq(login))).thenReturn(new DBUser(login));
        DBUser dbUser = up.queryUser(login);
        Assert.assertNotNull(dbUser);
        Assert.assertEquals(login, dbUser.getUserid());
    }
}
