package ru.itis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String nickname;
    private String email;
    private String password;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setNickname(nickname);
        user.setEmail(email);
        return user;
    }

    public static UserDto fromUser(User user) {
        return UserDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .build();
    }

}
