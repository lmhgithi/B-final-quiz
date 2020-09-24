package com.example.demo.service;


import com.example.demo.domain.Trainee;
import com.example.demo.domain.Trainer;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;


    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public Trainer addTrainee(Trainer trainee) {
        trainerRepository.save(trainee);
        return trainerRepository.findById(trainee.getId()).get();
    }

    public List<Trainer> getTrainees() {
        return trainerRepository.findAll();
    }

    public void deleteTrainee(Long id) {
        trainerRepository.deleteById(id);
    }

    public List<Trainer> getTrainers() {
        return trainerRepository.findAll();
    }
}
