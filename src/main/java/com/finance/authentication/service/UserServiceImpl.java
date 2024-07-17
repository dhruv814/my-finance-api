package com.finance.authentication.service;

import com.finance.authentication.dto.UserDto;
import com.finance.authentication.entity.Role;
import com.finance.authentication.entity.User;
import com.finance.authentication.repository.UserRepository;
import com.finance.authentication.service.infc.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        return mapToUserDto(user);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return mapToUserDto(user);
    }

    @Override
    @Transactional
    public UserDto saveUser(UserDto userDto) {

        UserDto existingUser = getUserByEmail(userDto.getEmail());
        if (existingUser != null) {
            // TODO use exception 
            return existingUser;
        }
        if (userDto.getRole() == null) {
            userDto.setRole(Role.USER);
        }

        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(userDto.getRole())
                .build();

        return mapToUserDto(userRepository.save(user));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user) {
        if (user == null) {
            return null;
        }
        return modelMapper.map(user,
                UserDto.class);
    }
}
