package co.tujia.tujia.controller;

import co.tujia.tujia.domain.Medication;
import co.tujia.tujia.domain.Schedule;
import co.tujia.tujia.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     *
     * @param schedule
     * @return
     */
    @PostMapping()
    public ResponseEntity<Schedule> saveSchedule(@RequestBody Schedule schedule){
        try {
            return new ResponseEntity<>(scheduleService.save(schedule), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        try {
            return new ResponseEntity<>(scheduleService.findAll(), HttpStatus.OK);
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
    public ResponseEntity<Schedule> getScheduleById(@PathVariable("id") String id) {
        Optional<Schedule> schedule = scheduleService.findById(id);
        if (schedule.isPresent()) {
            return new ResponseEntity<>(schedule.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *
     * @param id
     * @param schedule
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable("id") String id, @RequestBody Schedule schedule) {
        Optional<Schedule> getSchedule = scheduleService.findById(id);

        if (getSchedule.isPresent()) {
            return new ResponseEntity<>(scheduleService.save(schedule), HttpStatus.OK);
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
    public ResponseEntity<HttpStatus> deleteSchedule(@PathVariable("id") String id) {
        try {
            scheduleService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
