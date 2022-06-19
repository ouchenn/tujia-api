package co.tujia.tujia.service;

import co.tujia.tujia.domain.Medication;

import java.util.List;
import java.util.Optional;

public interface MedicationService {

    Medication save(Medication medication);

    Optional<Medication> update(String id, Medication medication);

    Optional<Medication> findById(String id);

    List<Medication> findAll();

    void deleteById(String id);
}
