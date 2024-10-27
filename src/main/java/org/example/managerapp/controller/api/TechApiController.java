package org.example.managerapp.controller.api;

import lombok.RequiredArgsConstructor;
import org.example.managerapp.dto.request.EditTechDto;
import org.example.managerapp.dto.request.NewTechDto;
import org.example.managerapp.dto.response.TechDto;
import org.example.managerapp.exception.ProjectNotFoundException;
import org.example.managerapp.exception.TechNotFoundException;
import org.example.managerapp.maper.TechMapper;
import org.example.managerapp.model.Tech;
import org.example.managerapp.service.TechService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/techs")
public class TechApiController {

    private final TechService techService;
    private final TechMapper techMapper;

    @GetMapping
    public List<TechDto> getAllTechs() {
        return techService.findAll().stream().map(techMapper::toTechDto).toList();
    }

    @GetMapping("/{techId}")
    public TechDto getTech(@PathVariable int techId) {
        return techService.getById(techId)
                .map(techMapper::toTechDto)
                .orElseThrow(ProjectNotFoundException::new);
    }

    @PostMapping
    public TechDto newProject(@RequestBody NewTechDto request) {
        Tech tech = techMapper.toTech(request);

        return techMapper.toTechDto(techService.save(tech));
    }


    @PutMapping("/{techId}")
    public TechDto updateTech(@PathVariable int techId, @RequestBody EditTechDto request) {
        Tech tech = techService.getById(techId).orElseThrow(TechNotFoundException::new);

        tech.setTitle(request.getTitle());

        return techMapper.toTechDto(techService.save(tech));
    }

    @DeleteMapping("/{techId}")
    public TechDto deleteTech(@PathVariable int techId) {
        Tech tech = techService.getById(techId).orElseThrow(TechNotFoundException::new);

        techService.delete(tech);

        return techMapper.toTechDto(tech);
    }

}
