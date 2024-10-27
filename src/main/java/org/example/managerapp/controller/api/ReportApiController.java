package org.example.managerapp.controller.api;

import lombok.RequiredArgsConstructor;
import org.example.managerapp.dto.response.ProjectReportDto;
import org.example.managerapp.dto.response.TechReportDto;
import org.example.managerapp.maper.ProjectMapper;
import org.example.managerapp.maper.TechMapper;
import org.example.managerapp.service.ProjectService;
import org.example.managerapp.service.TechService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/reports")
public class ReportApiController {

    private final TechService techService;
    private final ProjectService projectService;

    private final TechMapper techMapper;
    private final ProjectMapper projectMapper;

    @GetMapping("/tech")
    public List<TechReportDto> getTechReport() {
        return techService.findAll()
                .stream()
                .map(tech -> techMapper.toTechReport(tech.getTitle(), tech.getEmployees().size()))
                .toList();
    }

    @GetMapping("/project")
    public List<ProjectReportDto> getProjectReport() {
        return projectService.findAll()
                .stream()
                .map(project -> projectMapper.toProjectReport(project.getTitle(), project.getEmployees().size()))
                .toList();
    }

}
