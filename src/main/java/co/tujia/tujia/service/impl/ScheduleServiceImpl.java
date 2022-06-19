package co.tujia.tujia.service.impl;

import co.tujia.tujia.domain.Schedule;
import co.tujia.tujia.repository.ScheduleRepository;
import co.tujia.tujia.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepo;

    @Override
    public Schedule save(Schedule schedule) {
        return scheduleRepo.save(schedule);
    }

    @Override
    public Optional<Schedule> update(String id, Schedule schedule) {
        return Optional.empty();
    }

    @Override
    public Optional<Schedule> findById(String id) {
        return scheduleRepo.findById(id);
    }

    @Override
    public List<Schedule> findAll() {
        return scheduleRepo.findAll();
    }

    @Override
    public void deleteById(String id) {
        scheduleRepo.deleteById(id);
    }

    @Override
    public void changeStatus(String status) {

    }
}
