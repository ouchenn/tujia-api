package co.tujia.tujia.repository;

import co.tujia.tujia.domain.Medication;
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
class MedicationRepositoryTest {

    @Autowired
    MongoTemplate mongoTemplate;

    Medication medication = new Medication();

    @BeforeEach
    void setUp() {
        medication.setId("MED001");
        medication.setName("Medication Name");
        medication.setCompany("Medication Company");
        medication.setDiagnosis("Diagnosis");
        medication.setSerial("MED_001");
        medication.setType("Medication Type");
    }

    @AfterEach
    void cleanUpDatabase() {
        mongoTemplate.remove(medication);
    }

    @Test
    void save() {
        mongoTemplate.save(medication);
        Medication medicationExists = mongoTemplate.findById(medication.getId(), Medication.class);
        assert medicationExists != null;
        assertEquals(medication, medicationExists);
        mongoTemplate.remove(medicationExists);
    }

    @Test
    void findById() {
        Medication medicationExists = mongoTemplate.findById(medication.getId(), Medication.class);
        assert medicationExists != null;
        assertEquals(medication, medicationExists);
        mongoTemplate.remove(medicationExists);
    }

    @Test
    void findAll() {
        assertNotNull(mongoTemplate.findAll(Medication.class));
        assertEquals(1, mongoTemplate.findAll(Medication.class).size());
    }

    @Test
    void deleteById() {
        mongoTemplate.remove(medication);
        assertNull(mongoTemplate.findById(medication.getId(), Medication.class));
    }

}