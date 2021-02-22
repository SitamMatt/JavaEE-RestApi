package sitammatt.example_rest.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import sitammatt.example_rest.dto.TaskDto;
import sitammatt.example_rest.dto.TaskPatchDto;
import sitammatt.example_rest.model.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "createdDate", source = "createdDate", qualifiedByName = "toDate")
    @Mapping(target = "modifiedDate", source = "modifiedDate", qualifiedByName = "toDate")
    TaskDto mapToDto(Task task);

    @Mapping(target = "guid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    Task mapToEntity(TaskDto task);

    @Mapping(target = "guid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    void mapToEntity(TaskDto source, @MappingTarget Task target);

    @Mapping(source = "description", target = "description", qualifiedByName = "unwrap")
    @Mapping(source = "title", target = "title", qualifiedByName = "unwrap")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "guid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    void mapToEntity(final TaskPatchDto source, @MappingTarget Task target);

    @Named("unwrap")
    default <T, S extends Optional<T>> T unwrap(S optional){
        return optional.orElse(null);
    }

    @Named("toDate")
    default String toDate(Date date){
        var formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        return date == null ? null : formatter.format(date);
    }
}
