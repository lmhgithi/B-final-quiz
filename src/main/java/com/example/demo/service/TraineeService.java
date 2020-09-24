package com.example.demo.service;


import com.example.demo.domain.Trainee;
import com.example.demo.repository.TraineeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraineeService {

    private final TraineeRepository traineeRepository;


    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }


    public List<Trainee> getTrainees() {
        return traineeRepository.findAll();
    }

    public List<Trainee> getTraineesByGrouped(boolean grouped) {
        return traineeRepository.findByIsGrouped(grouped);
    }

    public Trainee addTrainee(Trainee trainee) {
        traineeRepository.save(trainee);
        return traineeRepository.findById(trainee.getId()).get();
    }

    public void deleteTrainee(Long id) {
        traineeRepository.deleteById(id);
    }
}
