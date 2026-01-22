import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.jupiter.api.Assumptions;
import org.junit.runner.RunWith;
import sitammatt.example_rest.dao.UserDao;
import sitammatt.example_rest.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(Arquillian.class)
public class ATest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "arquillian-example.war")
                .addClass(User.class)
                .addClass(UserDao.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }

    @PersistenceContext
    EntityManager em;

    @Test
    public void shouldFindAllGamesUsingExplicitJpqlQuery() throws Exception {
        Assumptions.assumeFalse(false);
    }
}
