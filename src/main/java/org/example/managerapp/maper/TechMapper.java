package org.example.managerapp.maper;

import org.example.managerapp.dto.request.NewTechDto;
import org.example.managerapp.dto.response.TechDto;
import org.example.managerapp.dto.response.TechReportDto;
import org.example.managerapp.model.Tech;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechMapper {

    TechDto toTechDto(Tech tech);

    Tech toTech(NewTechDto newTechDto);

    TechReportDto toTechReport(String techTitle, Integer numberOfEmployees);

}
