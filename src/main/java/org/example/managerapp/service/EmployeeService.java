package org.example.managerapp.service;

import lombok.RequiredArgsConstructor;
import org.example.managerapp.dto.request.EditEmployeeDto;
import org.example.managerapp.dto.request.NewEmployeeDto;
import org.example.managerapp.exception.EmployeeNotFoundException;
import org.example.managerapp.maper.EmployeeMapper;
import org.example.managerapp.model.Employee;
import org.example.managerapp.model.Project;
import org.example.managerapp.model.Tech;
import org.example.managerapp.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ProjectService projectService;
    private final TechService techService;
    private final EmployeeMapper employeeMapper;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getById(int id) {
        return employeeRepository.findById(id);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee createNewEmployee(NewEmployeeDto request) {
        Employee employee = employeeMapper.toEmployee(request);

        employee.setProjects(getUpdatedProjects(employee.getProjects(), request.getProjectIds()));
        employee.setTechs(getUpdatedTechs(employee.getTechs(), request.getTechIds()));

        return save(employee);
    }

    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    public Employee updateEmployee(Integer employeeId, EditEmployeeDto request) {
        Employee employee = getById(employeeId).orElseThrow(EmployeeNotFoundException::new);

        employee.setFirstName(request.getFirstName());
        employee.setMiddleName(request.getMiddleName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setPosition(request.getPosition());
        employee.setDomainUser(request.getDomainUser());
        employee.setComputerName(request.getComputerName());
        employee.setProjects(getUpdatedProjects(employee.getProjects(), request.getProjectIds()));
        employee.setTechs(getUpdatedTechs(employee.getTechs(), request.getTechIds()));

        return employeeRepository.save(employee);
    }

    private List<Project> getUpdatedProjects(List<Project> employeeProjects, List<Integer> projectIds) {
        List<Project> existingProjects = employeeProjects == null ? new ArrayList<>() : employeeProjects;
        existingProjects = existingProjects.stream()
                .filter(project -> projectIds.contains(project.getId()))
                .toList();

        List<Integer> existingProjectIds = existingProjects.stream()
                .map(Project::getId)
                .toList();
        List<Integer> newProjectIdsOnly = projectIds.stream()
                .filter(id -> !existingProjectIds.contains(id))
                .toList();

        List<Project> newProjects = new ArrayList<>(existingProjects);
        newProjects.addAll(projectService.getAllByIds(newProjectIdsOnly));

        return newProjects;
    }

    private List<Tech> getUpdatedTechs(List<Tech> employeeTechs, List<Integer> techIds) {
        List<Tech> existingTechs = employeeTechs == null ? new ArrayList<>() : employeeTechs;
        existingTechs = existingTechs.stream()
                .filter(project -> techIds.contains(project.getId()))
                .toList();

        List<Integer> existingTechIds = existingTechs.stream()
                .map(Tech::getId)
                .toList();
        List<Integer> newTechIdsOnly = techIds.stream()
                .filter(id -> !existingTechIds.contains(id))
                .toList();

        List<Tech> newTechs = new ArrayList<>(existingTechs);
        newTechs.addAll(techService.getAllByIds(newTechIdsOnly));

        return newTechs;
    }

}
