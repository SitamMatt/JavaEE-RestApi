package sitammatt.example_rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import sitammatt.example_rest.dto.UserDto;
import sitammatt.example_rest.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "createdDate", source = "createdDate", qualifiedByName = "toDate")
    @Mapping(target = "modifiedDate", source = "modifiedDate", qualifiedByName = "toDate")
    UserDto mapToDto(User user);


    @Named("toDate")
    default String toDate(Date date){
        var formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        return date == null ? null : formatter.format(date);
    }

    @Mapping(target = "guid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    User mapToEntity(UserDto dto);

    @Mapping(target = "guid", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    void mapToEntity(UserDto dto, @MappingTarget User entity);
}
