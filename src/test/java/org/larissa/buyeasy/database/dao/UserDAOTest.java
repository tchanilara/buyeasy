package org.larissa.buyeasy.database.dao;

import org.junit.jupiter.api.*;
import org.larissa.buyeasy.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserDAO userDao;

    @Test
    @Order(1)
    public void createNewOrderTest(){

        //given
        User user = new User();

        String encoded = passwordEncoder.encode("Test@1234");
        user.setFirstName("Test Firstname");
        user.setLastName("Test Lastname");
        user.setEmail("EmailTest@gmail.com");
        user.setPhone("235647891");
        user.setZipCode("12345");
        user.setPassword(encoded);

        //when
        user = userDao.save(user);

        //then
        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals("Test Firstname", user.getFirstName());
        Assertions.assertEquals("Test Lastname", user.getLastName());
        Assertions.assertEquals("EmailTest@gmail.com", user.getEmail());
        Assertions.assertEquals("235647891", user.getPhone());
        Assertions.assertEquals("12345", user.getZipCode());

    }


    @Test
    @Order(2)
    public void findByEmailIgnoreCaseTest() {
        // given
        String email = "EmailTest@gmail.com";

        // when
        User user = userDao.findByEmailIgnoreCase(email);

        // then
        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals("Test Firstname", user.getFirstName());
        Assertions.assertEquals("Test Lastname", user.getLastName());
        Assertions.assertEquals("EmailTest@gmail.com", user.getEmail());
        Assertions.assertEquals("235647891", user.getPhone());
        Assertions.assertEquals("12345", user.getZipCode());
    }
    @Test
    @Order(3)
    public  void deleteUserTest(){
        //given
        String email = "EmailTest@gmail.com";

        //when
        int deleted = userDao.deleteByEmailIgnoreCase(email);

        //then
        Assertions.assertEquals(1, deleted);

    }

    @Test
    @Order(4)
    public void shouldNotExistTest() {
        // given
        String email = "EmailTest@gmail.com";

        // when
        User user = userDao.findByEmailIgnoreCase(email);

        // then
        Assertions.assertNull(user);
    }
}
