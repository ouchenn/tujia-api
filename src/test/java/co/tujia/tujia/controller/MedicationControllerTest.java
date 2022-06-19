package co.tujia.tujia.controller;

import co.tujia.tujia.domain.Medication;
import co.tujia.tujia.service.MedicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MedicationController.class)
class MedicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicationService medicationService;

    String url = "/api/medication";
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
    void saveMedication() throws Exception {
        when(medicationService.save(medication1)).thenReturn(medication1);

        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(medication1)))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllMedications() throws Exception {
        when(medicationService.findAll()).thenReturn(medicationList);

        mockMvc.perform(get(url + "/all"))
                .andExpect(status().isOk());
    }

    @Test
    void getMedicationById() throws Exception {
        when(medicationService.findById(medication1.getId())).thenReturn(java.util.Optional.ofNullable(medication1));

        mockMvc.perform(get(url + medication1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateMedication() throws Exception {
        when(medicationService.save(medication1)).thenReturn(medication1);

        mockMvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(medication1)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteMedication() throws Exception {
        doNothing().when(medicationService).deleteById(medication1.getId());

        mockMvc.perform(delete(url + medication1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}