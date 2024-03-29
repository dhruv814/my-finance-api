package com.finance;

import com.finance.authentication.dto.UserDto;
import com.finance.authentication.entity.Role;
import com.finance.authentication.service.infc.UserService;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyFinanceApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void testSaveEntity() {
        UserDto user = new UserDto();
        user.setFirstName("Demo");
        user.setLastName("User");
        user.setEmail("test@gmail.com");
        user.setPassword("admin");
        user.setRole(Role.USER);

        UserDto existingUser = userService.getUserByEmail(user.getEmail());
        if (existingUser != null) {
            assertNotNull(existingUser.getCreateTime());
            assertNotNull(existingUser.getUpdateTime());
        } else {
            UserDto savedEntity = userService.saveUser(user);
            assertNotNull(savedEntity.getCreateTime());
            assertNotNull(savedEntity);
        }
    }
}
