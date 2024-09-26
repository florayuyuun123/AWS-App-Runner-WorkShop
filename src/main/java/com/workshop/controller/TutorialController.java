package com.workshop.controller;

import com.workshop.models.Tutorial;
import com.workshop.service.TutorialService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tutorials")
@Tag(name = "Tutorials", description = "API for managing the tutorials")
public class TutorialController {

    private final TutorialService tutorialService;

    public TutorialController(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTutorials() {
        List<Tutorial> tutorials = tutorialService.getAllTutotrials();

        if (!tutorials.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(tutorials);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No tutorials found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTutorialById(@PathVariable("id") long id) {
        Optional<Tutorial> optionalTutorial = tutorialService.getTutotrialById(id);

        if (optionalTutorial.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalTutorial.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tutorial with id "+id+" not found");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        Tutorial createdTutorial = tutorialService.createTutotrial(tutorial);
        return new ResponseEntity<>(createdTutorial, HttpStatus.CREATED);
    }

    @GetMapping("/published")
    public ResponseEntity<?> getPublished() {
        List<Tutorial> tutorials = tutorialService.getByPublished();

        if (tutorials.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No tutorial found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tutorials);
    }

}
