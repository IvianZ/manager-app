package org.example.managerapp.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProjectDto {

    private Integer id;
    private String title;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm", timezone = "Europe/Sofia")
    private Instant createdAt;

}
