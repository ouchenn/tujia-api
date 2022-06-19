package co.tujia.tujia.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("medications")
public class Medication {
    @Id
    private String id;
    private String name;
    private String company;
    private String diagnosis;
    @Indexed(unique = true)
    private String serial;
    private String type;
}