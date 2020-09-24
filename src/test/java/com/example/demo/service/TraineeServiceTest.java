package com.example.demo.service;

import com.example.demo.domain.Trainee;
import com.example.demo.repository.TraineeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TraineeServiceTest {

    private TraineeService traineeService;
    @Mock
    private TraineeRepository traineeRepository;

    private Trainee trainee;
    private List<Trainee> trainees;
    @BeforeEach
    public void init() {
        traineeService = new TraineeService(traineeRepository);
        trainees = new ArrayList<>();
        trainee = Trainee.builder()
                .id(1L)
                .name("xiaobai")
                .github("123")
                .office("shenzhen")
                .isGrouped(false)
                .email("a@b.com")
                .build();
        trainees.add(trainee);
    }

    @Test
    public void should_get_trainees(){
        when(traineeRepository.findAll()).thenReturn(trainees);
        List<Trainee> trainees = traineeService.getTrainees();
        assertEquals("xiaobai", trainees.get(0).getName());
    }


}