package sitammatt.example_rest.services;

import sitammatt.example_rest.dao.TaskDao;
import sitammatt.example_rest.dto.TaskDto;
import sitammatt.example_rest.mappers.TaskMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TaskService {
    @Inject
    private TaskDao taskDao;

    public List<TaskDto> getAll(){
        return taskDao.getAll().stream().map(x -> TaskMapper.INSTANCE.mapToDto(x)).collect(Collectors.toList());
    }

    public TaskDto get(UUID guid){
        var task = taskDao.get(guid);
        if(task == null) return null;
        return TaskMapper.INSTANCE.mapToDto(task);
    }

    public TaskDto create(TaskDto task){
        var entity = TaskMapper.INSTANCE.mapToEntity(task);

        taskDao.add(entity);

        return TaskMapper.INSTANCE.mapToDto(entity);
    }

    public TaskDto update(UUID guid, TaskDto task){
        var entity = taskDao.get(guid);
        TaskMapper.INSTANCE.mapToEntity(task, entity);

        taskDao.update(entity);

        return TaskMapper.INSTANCE.mapToDto(entity);
    }

    public void delete(UUID guid){
        var entity = taskDao.get(guid);
        taskDao.delete(entity);
    }
}
