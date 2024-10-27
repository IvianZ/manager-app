package org.example.managerapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDto {

    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String domainUser;
    private String computerName;
    private String phone;
    private String position;
    private List<ProjectDto> projects;
    private List<TechDto> techs;

}
