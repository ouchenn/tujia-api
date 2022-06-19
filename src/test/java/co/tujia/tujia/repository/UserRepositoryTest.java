package co.tujia.tujia.repository;

import co.tujia.tujia.domain.User;
import co.tujia.tujia.enums.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class UserRepositoryTest {

    @Autowired
    MongoTemplate mongoTemplate;

    User user = new User();

    @BeforeEach
    void setUp() {
        user.setId("USR001");
        user.setName("User Name");
        user.setPhone("+212 677889900");
        user.setEmail("user_1@email.com");
        user.setPassword("aPassword");
        user.setIsActive(true);
        user.setRole(Role.USER);

        mongoTemplate.save(user);
    }

    @AfterEach
    void cleanUpDatabase() {
        mongoTemplate.remove(user);
    }

    @Test
    void save() {
        mongoTemplate.save(user);
        User userExists = mongoTemplate.findById(user.getId(), User.class);
        assert userExists != null;
        assertEquals(user, userExists);
        mongoTemplate.remove(userExists);
    }

    @Test
    void findById() {
        User userExists = mongoTemplate.findById(user.getId(), User.class);
        assert userExists != null;
        assertEquals(user, userExists);
        mongoTemplate.remove(userExists);
    }

    @Test
    void findAll() {
        assertNotNull(mongoTemplate.findAll(User.class));
        assertEquals(1, mongoTemplate.findAll(User.class).size());
    }

    @Test
    void deleteById() {
        mongoTemplate.remove(user);
        assertNull(mongoTemplate.findById(user.getId(), User.class));
    }
}