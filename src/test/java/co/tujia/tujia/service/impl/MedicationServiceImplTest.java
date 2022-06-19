package co.tujia.tujia.service.impl;

import co.tujia.tujia.domain.Medication;
import co.tujia.tujia.repository.MedicationRepository;
import co.tujia.tujia.service.MedicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
class MedicationServiceImplTest {

    @Mock
    MedicationRepository medicationRepo;

    @InjectMocks
    MedicationService medicationService;

    Medication medication1 = new Medication();
    Medication medication2 = new Medication();

    List<Medication> medicationList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        medication1.setId("MED001");
        medication1.setName("Medication Name");
        medication1.setCompany("Medication Company");
        medication1.setDiagnosis("Diagnosis");
        medication1.setSerial("MED_001");
        medication1.setType("Medication Type");

        medication2.setId("MED002");
        medication2.setName("Medication Name");
        medication2.setCompany("Medication Company");
        medication2.setDiagnosis("Diagnosis");
        medication2.setSerial("MED_002");
        medication2.setType("Medication Type");

        medicationList.add(medication1);
        medicationList.add(medication2);
    }

    @Test
    void save() {
        Mockito.lenient().when(medicationRepo.save(medication1)).thenReturn(medication1);
        Medication medication = medicationRepo.save(medication1);
        assertThat(medication).isEqualTo(medication1);
    }

    @Test
    void findById() {
        Mockito.lenient().when(medicationRepo.findById(medication1.getId())).thenReturn(Optional.of(medication1));
        Optional<Medication> medication = medicationService.findById(medication1.getId());
        assertThat(medication).isEqualTo(medication1);
    }

    @Test
    void findAll() {
        given(medicationRepo.findAll()).willReturn(medicationList);
        List<Medication> medications = medicationService.findAll();
        assertEquals(medicationList, medications);
        verify(medicationRepo).findAll();
    }

    @Test
    void deleteById() {
        Mockito.lenient().when(medicationRepo.findById(medication1.getId())).thenReturn(Optional.of(medication1));
        medicationService.deleteById(medication1.getId());
    }
}