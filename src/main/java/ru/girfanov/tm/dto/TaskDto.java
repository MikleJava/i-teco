package ru.girfanov.tm.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.girfanov.tm.enumeration.Status;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto extends AbstractEntityDto {
    @NonNull private String name;
    @NonNull private String description;
    @NonNull private Status status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NonNull private Date dateStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NonNull private Date dateEnd;
    @NonNull private String userId;
    @NonNull private String projectId;
}
