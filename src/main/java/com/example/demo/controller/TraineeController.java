package com.example.demo.controller;


import com.example.demo.domain.Trainee;
import com.example.demo.dto.TraineeDto;
import com.example.demo.exception.SimpleException;
import com.example.demo.service.TraineeService;
import com.example.demo.utils.ConvertTool;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/trainees")
@CrossOrigin
public class TraineeController {
    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @GetMapping
    public ResponseEntity<List<TraineeDto>> getTrainees() {
        List<Trainee> trainees  = traineeService.getTrainees();
        List<TraineeDto> traineeDtoList = new ArrayList<>();
        trainees.forEach(domain -> traineeDtoList.add(ConvertTool.convert(domain, TraineeDto.class)));

        return ResponseEntity.status(HttpStatus.OK).body(traineeDtoList);
    }

    @PostMapping
    public ResponseEntity<TraineeDto> addTrainee(@RequestBody @Valid TraineeDto traineeDto) throws SimpleException {
        Trainee trainee = ConvertTool.convert(traineeDto, Trainee.class);
        Trainee traineeAdded = traineeService.addTrainee(trainee);
        TraineeDto traineeDtoAdded = ConvertTool.convert(traineeAdded, TraineeDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(traineeDtoAdded);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainee(@PathVariable Long id) throws SimpleException {
        traineeService.deleteTrainee(id);
    }


}
