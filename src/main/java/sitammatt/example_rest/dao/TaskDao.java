package sitammatt.example_rest.dao;

import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream;
import sitammatt.example_rest.model.Task;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class TaskDao extends BaseDao<Task>{

    @Inject
    public TaskDao(EntityManager em) {
        super(em, Task.class);
    }

    @Override
    public List<Task> getAll() {
        JinqJPAStreamProvider streams = new JinqJPAStreamProvider(em.getMetamodel());
        var result = streams.streamAll(em, Task.class)
                .leftOuterJoinFetch(x -> JinqStream.of(x.getUser()))
                .toList();
        return result;
//        var result = super.getAll();
//        return result;
    }
}
