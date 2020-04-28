package ru.itis.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.dto.UserDto;
import ru.itis.models.User;

@Component
public class EntityToEntityDtoConverter implements Converter<UserDto, User> {

    @Override
    public User convert(UserDto o) {
        User user = new User();
        user.setId(o.getId());
        user.setNickname(o.getNickname());
        user.setEmail(o.getEmail());
        return user;
    }
}
