package co.tujia.tujia.domain;

import co.tujia.tujia.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("schedules")
public class Schedule {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Boolean daily;
    private Boolean weekly;
    private Boolean monthly;
    private ArrayList<String> custom;
    private ArrayList<Long> times;
    private String instructions;
}
