package org.example.managerapp.controller.api;

import lombok.RequiredArgsConstructor;
import org.example.managerapp.dto.request.EditEmployeeDto;
import org.example.managerapp.dto.request.NewEmployeeDto;
import org.example.managerapp.dto.response.EmployeeDto;
import org.example.managerapp.exception.EmployeeNotFoundException;
import org.example.managerapp.maper.EmployeeMapper;
import org.example.managerapp.model.Employee;
import org.example.managerapp.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeApiController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.findAll().stream().map(employeeMapper::toEmployeeDto).toList();
    }

    @GetMapping("/{employeeId}")
    public EmployeeDto getEmployee(@PathVariable int employeeId) {
        return employeeService.getById(employeeId)
                .map(employeeMapper::toEmployeeDto)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @PostMapping
    public EmployeeDto newEmployee(@RequestBody NewEmployeeDto request) {
        return employeeMapper.toEmployeeDto(employeeService.createNewEmployee(request));
    }


    @PutMapping("/{employeeId}")
    public EmployeeDto updateEmployee(@PathVariable int employeeId, @RequestBody EditEmployeeDto request) {
        Employee updatedEmployee = employeeService.updateEmployee(employeeId, request);

        return employeeMapper.toEmployeeDto(updatedEmployee);
    }

    @DeleteMapping("/{employeeId}")
    public EmployeeDto deleteEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.getById(employeeId).orElseThrow(EmployeeNotFoundException::new);

        employeeService.delete(employee);

        return employeeMapper.toEmployeeDto(employee);
    }

}
