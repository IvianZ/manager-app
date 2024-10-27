package org.example.managerapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TechReportDto {

    private String techTitle;
    private Integer numberOfEmployees;

}
