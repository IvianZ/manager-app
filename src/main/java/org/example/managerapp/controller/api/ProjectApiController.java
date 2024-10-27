package org.example.managerapp.controller.api;

import lombok.RequiredArgsConstructor;
import org.example.managerapp.dto.request.EditProjectDto;
import org.example.managerapp.dto.request.NewProjectDto;
import org.example.managerapp.dto.response.ProjectDto;
import org.example.managerapp.exception.ProjectNotFoundException;
import org.example.managerapp.maper.ProjectMapper;
import org.example.managerapp.model.Project;
import org.example.managerapp.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/projects")
public class ProjectApiController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @GetMapping
    public List<ProjectDto> getAllProjects() {
        return projectService.findAll().stream().map(projectMapper::toProjectDto).toList();
    }

    @GetMapping("/{projectId}")
    public ProjectDto getProject(@PathVariable int projectId) {
        return projectService.getById(projectId)
                .map(projectMapper::toProjectDto)
                .orElseThrow(ProjectNotFoundException::new);
    }

    @PostMapping
    public ProjectDto newProject(@RequestBody NewProjectDto request) {
        Project project = projectMapper.toProject(request);

        return projectMapper.toProjectDto(projectService.save(project));
    }


    @PutMapping("/{projectId}")
    public ProjectDto updateProject(@PathVariable int projectId, @RequestBody EditProjectDto request) {
        Project project = projectService.getById(projectId).orElseThrow(ProjectNotFoundException::new);

        project.setTitle(request.getTitle());

        return projectMapper.toProjectDto(projectService.save(project));
    }

    @DeleteMapping("/{projectId}")
    public ProjectDto deleteProject(@PathVariable int projectId) {
        Project project = projectService.getById(projectId).orElseThrow(ProjectNotFoundException::new);

        projectService.delete(project);

        return projectMapper.toProjectDto(project);
    }

}
