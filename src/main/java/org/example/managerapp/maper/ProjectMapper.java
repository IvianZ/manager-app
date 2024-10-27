package org.example.managerapp.maper;

import org.example.managerapp.dto.request.NewProjectDto;
import org.example.managerapp.dto.response.ProjectDto;
import org.example.managerapp.dto.response.ProjectReportDto;
import org.example.managerapp.model.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDto toProjectDto(Project project);

    Project toProject(NewProjectDto newProjectDto);

    ProjectReportDto toProjectReport(String projectTitle, Integer numberOfEmployees);

}
