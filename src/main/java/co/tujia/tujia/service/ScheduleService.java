package co.tujia.tujia.service;

import co.tujia.tujia.domain.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {

    Schedule save(Schedule schedule);

    Optional<Schedule> update(String id, Schedule schedule);

    Optional<Schedule> findById(String id);

    List<Schedule> findAll();

    void deleteById(String id);

    void changeStatus(String status);
}
