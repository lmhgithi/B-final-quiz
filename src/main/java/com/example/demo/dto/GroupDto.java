package com.example.demo.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    private Integer id;
    private String name;
    List<TrainerDto> trainerDtoList;
    List<TraineeDto> traineeDtoList;
}
