package co.tujia.tujia.service.impl;

import co.tujia.tujia.domain.Schedule;
import co.tujia.tujia.enums.Status;
import co.tujia.tujia.repository.ScheduleRepository;
import co.tujia.tujia.service.ScheduleService;
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
class ScheduleServiceImplTest {

    @Mock
    ScheduleRepository scheduleRepo;

    @InjectMocks
    ScheduleService scheduleService;

    Schedule schedule1 = new Schedule();
    Schedule schedule2 = new Schedule();

    List<Schedule> scheduleList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        schedule1.setId("SCH001");
        schedule1.setStatus(Status.ACTIVE);
        schedule1.setDaily(true);
        schedule1.setWeekly(false);
        schedule1.setMonthly(false);
        schedule1.setCustom(new ArrayList<>());
        schedule1.setTimes(new ArrayList<>());
        schedule1.setInstructions("instructions");

        schedule2.setId("SCH002");
        schedule2.setStatus(Status.ACTIVE);
        schedule2.setDaily(false);
        schedule2.setWeekly(true);
        schedule2.setMonthly(false);
        schedule2.setCustom(new ArrayList<>());
        schedule2.setTimes(new ArrayList<>());
        schedule2.setInstructions("instructions");

        scheduleList.add(schedule1);
        scheduleList.add(schedule2);
    }

    @Test
    void save() {
        Mockito.lenient().when(scheduleService.save(schedule1)).thenReturn(schedule1);
        Schedule schedule = scheduleService.save(schedule1);
        assertThat(schedule).isEqualTo(schedule1);
    }

    @Test
    void findById() {
        Mockito.lenient().when(scheduleRepo.findById(schedule1.getId())).thenReturn(Optional.of(schedule1));
        Optional<Schedule> schedule = scheduleService.findById(schedule1.getId());
        assertThat(schedule).isEqualTo(schedule1);
    }

    @Test
    void findAll() {
        given(scheduleRepo.findAll()).willReturn(scheduleList);
        List<Schedule> schedules = scheduleService.findAll();
        assertEquals(scheduleList, schedules);
        verify(scheduleService).findAll();
    }

    @Test
    void deleteById() {
        Mockito.lenient().when(scheduleRepo.findById(schedule1.getId())).thenReturn(Optional.of(schedule1));
        scheduleService.deleteById(schedule1.getId());
    }
}