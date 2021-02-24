package sitammatt.example_rest.services;

import sitammatt.example_rest.dao.UserDao;
import sitammatt.example_rest.dto.UserDto;
import sitammatt.example_rest.mappers.UserMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserService {
    @Inject
    private UserDao userDao;

    public List<UserDto> getAll(){
        return userDao.getAll().stream()
                .map(UserMapper.INSTANCE::mapToDto)
                .collect(Collectors.toList());
    }

    public UserDto get(UUID guid){
        var entity = userDao.get(guid);
        if(entity == null) return null;
        return UserMapper.INSTANCE.mapToDto(entity);
    }

    public UserDto create(UserDto dto){
        var entity = UserMapper.INSTANCE.mapToEntity(dto);

        userDao.add(entity);

        return UserMapper.INSTANCE.mapToDto(entity);
    }

    public UserDto update(UUID guid, UserDto dto){
        var entity = userDao.get(guid);
        UserMapper.INSTANCE.mapToEntity(dto, entity);

        userDao.update(entity);
        userDao.commit();

        return UserMapper.INSTANCE.mapToDto(entity);
    }

    public void delete(UUID guid){
        var entity = userDao.get(guid);
        userDao.delete(entity);
    }
}
