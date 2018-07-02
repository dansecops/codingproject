import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class RegisterIT {

    private CloseableHttpClient client = HttpClientBuilder.create().build();

    @After
    public void tearDown() throws Exception {
        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").withPort(9042).build();
        Session session = cluster.connect();
        session.execute("TRUNCATE testdb.users;");
    }

    @Test
    public void testRegistration() throws IOException {
        HttpPost request = new HttpPost("http://127.0.0.1:8085/codingproject/register");
        request.setEntity(new StringEntity("{\"login\":\"dan35\",\"password\":\"igor\"}"));
        try (CloseableHttpResponse response = client.execute(request)){
            String rsp = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(rsp,"Registered");
        }

        //qa

        //prd

    }
}
