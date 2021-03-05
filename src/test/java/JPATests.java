import org.jinq.jpa.JinqJPAStreamProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sitammatt.example_rest.dao.TaskDao;
import sitammatt.example_rest.dao.UserDao;
import sitammatt.example_rest.model.Task;
import sitammatt.example_rest.model.User;

import javax.persistence.Persistence;

public class JPATests {

    @Test
    public void Test(){
        var emf = Persistence.createEntityManagerFactory("TasksTest");
        var em = emf.createEntityManager();
        em.getTransaction().begin();
        var dao = new TaskDao(em);
        var task = new Task();
        task.setTitle("Granie");
        dao.add(task);
        Assertions.assertNotNull(task.getGuid());

        var user = new User();
        user.setLogin("Lodek");
        var udao = new UserDao(em);
        udao.add(user);
        Assertions.assertNotNull(user.getGuid());

        task.setUserId(user.getGuid());
        dao.update(task);
        em.getTransaction().commit();
//        dao.commit();

//        var t = dao.get(task.getGuid());

        var taks = dao.getAll();
        em.refresh(taks.get(0));
//        JinqJPAStreamProvider streams =
//                new JinqJPAStreamProvider(emf);

//        var tasks = streams.streamAll(em, Task.class).toList();
//        dao.commit();
    }
}
