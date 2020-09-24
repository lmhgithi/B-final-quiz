package com.example.demo.controller;


import com.example.demo.domain.Trainee;
import com.example.demo.domain.Trainer;
import com.example.demo.dto.TraineeDto;
import com.example.demo.dto.TrainerDto;
import com.example.demo.service.TrainerService;
import com.example.demo.utils.ConvertTool;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/trainers")
@CrossOrigin
public class TrainerController {
    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping
    public ResponseEntity<TrainerDto> addTrainer(@RequestBody @Valid TrainerDto trainerDto) throws MethodArgumentNotValidException {
        Trainer trainer = ConvertTool.convert(trainerDto, Trainer.class);
        Trainer trainerAdded = trainerService.addTrainee(trainer);
        TrainerDto trainerDtoAdded = ConvertTool.convert(trainerAdded, TrainerDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(trainerDtoAdded);
    }

    @GetMapping
    public ResponseEntity<List<TrainerDto>> getTrainees() {
        List<Trainer> trainers  = trainerService.getTrainers();
        List<TrainerDto> trainerDtoList = new ArrayList<>();
        trainers.forEach(domain -> trainerDtoList.add(ConvertTool.convert(domain, TrainerDto.class)));

        return ResponseEntity.status(HttpStatus.OK).body(trainerDtoList);
    }

}
