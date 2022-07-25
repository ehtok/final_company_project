package by.roman.company.Converter.implement;

import by.roman.company.Converter.Converter;
import by.roman.company.DTO.UserDTO;
import by.roman.company.Entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverterImpl implements Converter<User, UserDTO> {
    @Override
    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .description(user.getDescription())
                .build();
    }

    @Override
    public List<UserDTO> toListDTO(List<User> users) {
        return users.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public User toEntity(UserDTO user) {
        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .description(user.getDescription())
                .build();
    }
}
