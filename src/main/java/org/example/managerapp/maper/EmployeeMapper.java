package org.example.managerapp.maper;

import org.example.managerapp.dto.request.NewEmployeeDto;
import org.example.managerapp.dto.response.EmployeeDto;
import org.example.managerapp.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toEmployeeDto(Employee employee);

    Employee toEmployee(NewEmployeeDto newEmployeeDto);

}
