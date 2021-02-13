package sitammatt.example_rest.services;

import sitammatt.example_rest.dao.TaskDao;
import sitammatt.example_rest.dto.TaskDto;
import sitammatt.example_rest.model.Task;

import javax.inject.Inject;

public class TaskService {
    @Inject
    private TaskDao taskDao;

    public TaskDto create(TaskDto task){
        var entity = new Task();
        // mapping
        entity.setTitle(task.title);
        entity.setDescription(task.description);
        taskDao.add(entity);
        // mapping todo create new object
        task.id = entity.getId();
        return task;
    }
}
