// package com.app.letrachica.controller.rest;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.Mockito.*;

// import java.util.List;
// import java.util.Map;
// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import com.app.letrachica.core.domain.User;
// import com.app.letrachica.core.gateway.UserRepository;
// import com.app.letrachica.infra.repository.UserRepositoryImpl;
// import com.app.letrachica.infra.repository.jpa.UserJpaRepository;

// public class UserControllerTest {

//     private UserRepository userRepository;
//     private UserController userController;
//     private User user;

//     @BeforeEach
//     void setUp() {
//         userRepository = mock(UserRepository.class);
//         userController = new UserController(userRepository);
//         user = new User("Test User", "test@example.com");
//     }

//     @Test
//     void testGetAllUsersShouldReturnUsers() {
//         UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
//         UserRepositoryImpl repo = new UserRepositoryImpl(userJpaRepository);
//         repo.save(user);
//         UserController controller = new UserController(repo);

//         List<User> users = controller.getAllUsers();

//         assertEquals(1, users.size());
//         assertEquals("Test User", users.get(0).getName());
//     }

//     @Test
//     void testGetUserByIdShouldReturnUserInfo() {
//         when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

//         Map<String, Object> result = userController.getUserById(user.getId());

//         assertEquals(user.getId(), result.get("id"));
//         assertEquals(user.getName(), result.get("name"));
//         assertEquals(user.getEmail(), result.get("email"));
//     }

//     @Test
//     void testGetUserBiIdShouldThrowIfNotFound() {
//         when(userRepository.findById("no-id")).thenReturn(Optional.empty());
//         assertThrows(RuntimeException.class, () -> userController.getUserById("no-id"));
//     }
// }
