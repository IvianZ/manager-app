package org.example.managerapp.service;

import lombok.RequiredArgsConstructor;
import org.example.managerapp.model.Project;
import org.example.managerapp.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public List<Project> getAllByIds(List<Integer> ids) {
        return projectRepository.findAllById(ids);
    }

    public Optional<Project> getById(int id) {
        return projectRepository.findById(id);
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public void delete(Project project) {
        projectRepository.delete(project);
    }

}
