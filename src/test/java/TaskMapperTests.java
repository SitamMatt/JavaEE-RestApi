import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sitammatt.example_rest.dto.TaskDto;
import sitammatt.example_rest.dto.TaskPatchDto;
import sitammatt.example_rest.mappers.TaskMapper;
import sitammatt.example_rest.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

public class TaskMapperTests {

    @Test
    void entityToDtoTest() throws ParseException {
        Task task = new Task();
        task.setDescription("aaa");
        task.setTitle("tit");
        task.setGuid(UUID.fromString("fbcd2e0a-85fd-4e9d-8245-9efae5d587a8"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.ENGLISH);

        String createdDateString = "22-01-2015 10:15:55 AM";
        task.setCreatedDate(formatter.parse(createdDateString));

        String modifiedDateString = "24-06-2016 12:35:40 AM";
        task.setModifiedDate(formatter.parse(modifiedDateString));

        var mapper = TaskMapper.INSTANCE;

        var dto = mapper.mapToDto(task);

        Assertions.assertEquals(task.getGuid(), dto.guid);
        Assertions.assertEquals(task.getTitle(), dto.title);
        Assertions.assertEquals(task.getDescription(), dto.description);
        Assertions.assertEquals(task.getCreatedDate(), dto.createdDate);
        Assertions.assertEquals(task.getModifiedDate(), dto.modifiedDate);
    }

    @Test
    public void dtoToEntityTest() throws ParseException {
        // dto
        var dto = new TaskDto();
        dto.guid = UUID.fromString("fbcd2e0a-85fd-4e9d-8245-9efae5d587a8");
        dto.description = "aaa";
        dto.title = "tit";

        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.ENGLISH);

        String createdDateString = "22-01-2015 10:15:55 AM";
        dto.createdDate = formatter.parse(createdDateString);

        String modifiedDateString = "24-06-2016 12:35:40 AM";
        dto.modifiedDate = formatter.parse(modifiedDateString);

        // entity
        Task task = new Task();
        task.setDescription("bbb");
        task.setTitle("iti");
        task.setGuid(UUID.fromString("108a3acf-f72d-4884-b103-ea18a0f03542"));

        String createdDateString2 = "22-01-2013 10:15:55 AM";
        task.setCreatedDate(formatter.parse(createdDateString2));

        String modifiedDateString2 = "24-06-2014 12:35:40 AM";
        task.setModifiedDate(formatter.parse(modifiedDateString2));

        var mapper = TaskMapper.INSTANCE;

        mapper.mapToEntity(dto, task);

        Assertions.assertEquals(task.getTitle(), "tit");
        Assertions.assertEquals(task.getDescription(), "aaa");
        Assertions.assertEquals(task.getGuid(), UUID.fromString("108a3acf-f72d-4884-b103-ea18a0f03542"));
        Assertions.assertEquals(task.getCreatedDate(), formatter.parse(createdDateString2));
        Assertions.assertEquals(task.getModifiedDate(), formatter.parse(modifiedDateString2));
    }

    @Test
    public void hashMapToEntityTest() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.ENGLISH);

        // patch
        var patch = new TaskPatchDto();
        patch.description = Optional.of("aaa");

        // entity
        Task task = new Task();
        task.setDescription("bbb");
        task.setTitle("iti");
        task.setGuid(UUID.fromString("108a3acf-f72d-4884-b103-ea18a0f03542"));

        String createdDateString2 = "22-01-2013 10:15:55 AM";
        task.setCreatedDate(formatter.parse(createdDateString2));

        String modifiedDateString2 = "24-06-2014 12:35:40 AM";
        task.setModifiedDate(formatter.parse(modifiedDateString2));

        var mapper = TaskMapper.INSTANCE;

        mapper.mapToEntity(patch, task);

        Assertions.assertEquals(task.getTitle(), "iti");
        Assertions.assertEquals(task.getDescription(), "aaa");
        Assertions.assertEquals(task.getGuid(), UUID.fromString("108a3acf-f72d-4884-b103-ea18a0f03542"));
        Assertions.assertEquals(task.getCreatedDate(), formatter.parse(createdDateString2));
        Assertions.assertEquals(task.getModifiedDate(), formatter.parse(modifiedDateString2));
    }
}
