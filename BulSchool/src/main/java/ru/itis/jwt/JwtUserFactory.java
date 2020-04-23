package ru.itis.jwt;

import lombok.NoArgsConstructor;
import ru.itis.models.Status;
import ru.itis.models.User;

@NoArgsConstructor
public final class JwtUserFactory {

    public static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getNickname(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated()
        );
    }
}
