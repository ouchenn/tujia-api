package co.tujia.tujia.repository;

import co.tujia.tujia.domain.Schedule;
import co.tujia.tujia.enums.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ScheduleRepositoryTest {

    @Autowired
    MongoTemplate mongoTemplate;

    Schedule schedule = new Schedule();

    @BeforeEach
    void setUp() {
        schedule.setId("SCH001");
        schedule.setStatus(Status.ACTIVE);
        schedule.setDaily(true);
        schedule.setWeekly(false);
        schedule.setMonthly(false);
        schedule.setCustom(new ArrayList<>());
        schedule.setTimes(new ArrayList<>());
        schedule.setInstructions("instructions");
    }

    @AfterEach
    void cleanUpDatabase() {
        mongoTemplate.remove(schedule);
    }

    @Test
    void save() {
        mongoTemplate.save(schedule);
        Schedule scheduleExists = mongoTemplate.findById(schedule.getId(), Schedule.class);
        assert scheduleExists != null;
        assertEquals(schedule, scheduleExists);
        mongoTemplate.remove(scheduleExists);
    }

    @Test
    void findById() {
        Schedule scheduleExists = mongoTemplate.findById(schedule.getId(), Schedule.class);
        assert scheduleExists != null;
        assertEquals(schedule, scheduleExists);
        mongoTemplate.remove(scheduleExists);
    }

    @Test
    void findAll() {
        assertNotNull(mongoTemplate.findAll(Schedule.class));
        assertEquals(1, mongoTemplate.findAll(Schedule.class).size());
    }

    @Test
    void deleteById() {
        mongoTemplate.remove(schedule);
        assertNull(mongoTemplate.findById(schedule.getId(), Schedule.class));
    }

}