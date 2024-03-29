package com.finance.authentication.service.infc;

import com.finance.authentication.dto.UserDto;
import java.util.List;

/**
 *
 * @author dhruvkumar
 */
public interface UserService {
    
    public UserDto getUserByEmail(String email);
    public UserDto saveUser(UserDto userDto);
    public List<UserDto> getAllUsers();
    public UserDto getUserById(Long id);

}
