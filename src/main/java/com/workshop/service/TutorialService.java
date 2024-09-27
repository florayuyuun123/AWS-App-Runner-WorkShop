package com.workshop.service;

import com.workshop.repository.TutorialRepository;
import com.workshop.models.Tutorial;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorialService {

    private final TutorialRepository tutorialRepository;

    public TutorialService(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    public List<Tutorial> getAllTutotrials() {
        return tutorialRepository.findAll();
    }

    public Optional<Tutorial> getTutotrialById(long id) {
        return tutorialRepository.findById(id);
    }

    public Tutorial createTutotrial(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    public List<Tutorial> getByPublished() {
        return tutorialRepository.findByPublished(true);
    }
}
