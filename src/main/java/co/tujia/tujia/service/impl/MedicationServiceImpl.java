package co.tujia.tujia.service.impl;

import co.tujia.tujia.domain.Medication;
import co.tujia.tujia.repository.MedicationRepository;
import co.tujia.tujia.service.MedicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepo;

    @Override
    public Medication save(Medication medication) {
        return medicationRepo.save(medication);
    }

    @Override
    public Optional<Medication> update(String id, Medication medication) {
        return Optional.empty();
    }

    @Override
    public Optional<Medication> findById(String id) {
        return medicationRepo.findById(id);
    }

    @Override
    public List<Medication> findAll() {
        return medicationRepo.findAll();
    }

    @Override
    public void deleteById(String id) {
        medicationRepo.deleteById(id);
    }
}
