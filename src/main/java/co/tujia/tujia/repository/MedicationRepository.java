package co.tujia.tujia.repository;

import co.tujia.tujia.domain.Medication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends MongoRepository<Medication, String> {
}
