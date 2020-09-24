package com.example.demo.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupDto {
    private long id;
    private String name;
    List<TrainerDto> trainerDtoList;
    List<TraineeDto> traineeDtoList;
}
