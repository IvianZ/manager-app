package org.example.managerapp.service;

import lombok.RequiredArgsConstructor;
import org.example.managerapp.model.Tech;
import org.example.managerapp.repository.TechRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TechService {

    private final TechRepository techRepository;

    public List<Tech> findAll() {
        return techRepository.findAll();
    }

    public Optional<Tech> getById(int id) {
        return techRepository.findById(id);
    }

    public Tech save(Tech tech) {
        return techRepository.save(tech);
    }

    public void delete(Tech tech) {
        techRepository.delete(tech);
    }

    public List<Tech> getAllByIds(List<Integer> ids) {
        return techRepository.findAllById(ids);
    }

}
