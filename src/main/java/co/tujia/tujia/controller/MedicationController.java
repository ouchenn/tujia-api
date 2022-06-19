package co.tujia.tujia.controller;

import co.tujia.tujia.domain.Medication;
import co.tujia.tujia.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/medication")
@RequiredArgsConstructor
public class MedicationController {

    private final MedicationService medicationService;

    /**
     *
     * @param medication
     * @return
     */
    @PostMapping()
    public ResponseEntity<Medication> saveMedication(@RequestBody Medication medication){
        try {
            return new ResponseEntity<>(medicationService.save(medication), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<List<Medication>> getAllMedications() {
        try {
            return new ResponseEntity<>(medicationService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Medication> getMedicationById(@PathVariable("id") String id) {
        Optional<Medication> medication = medicationService.findById(id);
        if (medication.isPresent()) {
            return new ResponseEntity<>(medication.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *
     * @param id
     * @param medication
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Medication> updateMedication(@PathVariable("id") String id, @RequestBody Medication medication) {
        Optional<Medication> getMedication = medicationService.findById(id);

        if (getMedication.isPresent()) {
            return new ResponseEntity<>(medicationService.save(medication), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMedication(@PathVariable("id") String id) {
        try {
            medicationService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
