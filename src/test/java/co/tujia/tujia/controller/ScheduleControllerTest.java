package co.tujia.tujia.controller;

import co.tujia.tujia.domain.Schedule;
import co.tujia.tujia.enums.Status;
import co.tujia.tujia.service.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleService scheduleService;

    String url = "/api/schedule";
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
    void saveSchedule() throws Exception {
        when(scheduleService.save(schedule1)).thenReturn(schedule1);

        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(schedule1)))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllSchedules() throws Exception {
        when(scheduleService.findAll()).thenReturn(scheduleList);

        mockMvc.perform(get(url + "/all"))
                .andExpect(status().isOk());
    }

    @Test
    void getScheduleById() throws Exception {
        when(scheduleService.findById(schedule1.getId())).thenReturn(java.util.Optional.ofNullable(schedule1));

        mockMvc.perform(get(url + schedule1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateSchedule() throws Exception {
        when(scheduleService.save(schedule1)).thenReturn(schedule1);

        mockMvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(schedule1)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteSchedule() throws Exception {
        doNothing().when(scheduleService).deleteById(schedule1.getId());

        mockMvc.perform(delete(url + schedule1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}