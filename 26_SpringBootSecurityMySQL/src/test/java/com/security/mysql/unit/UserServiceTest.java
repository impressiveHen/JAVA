package com.security.mysql.unit;

import com.security.mysql.entity.User;
import com.security.mysql.repository.RoleRepository;
import com.security.mysql.repository.UserRepository;
import com.security.mysql.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private RoleRepository mockRoleRepository;

    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;

    @Before
    public void setUp() {
        user = User.builder()
            .id(1)
            .userName("Harry")
            .firstName("Eric")
            .lastName("Chen")
            .email("test@test.com")
            .roles(new HashSet<>())
            .build();
    }

    @Test
    public void testFindUserByEmail() {
        String email = "test@test.com";
        when(mockUserRepository.findByEmail(anyString()))
            .thenReturn(user);

        User result = userService.findUserByEmail(email);
        assertEquals(email, result.getEmail());
    }

    @Test
    public void testSaveUser() {
        String email = "test@test.com";
        when(mockUserRepository.save(any(User.class)))
            .thenReturn(user);

        User result = userService.saveUser(user, "ADMIN");

        assertEquals(email, result.getEmail());
    }
}
