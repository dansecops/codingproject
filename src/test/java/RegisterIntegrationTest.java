import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@RunWith
public class RegisterIntegrationTest {

    @Mock
    private HttpServletRequest req;

    @Test
    public void doPost() {


        Register register = new Register();
        register.doPost(new HttpServletRequest());
    }
}
