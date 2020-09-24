package com.example.demo.controller;


import com.example.demo.domain.Group;
import com.example.demo.dto.GroupDto;
import com.example.demo.dto.TraineeDto;
import com.example.demo.dto.TrainerDto;
import com.example.demo.exception.SimpleException;
import com.example.demo.service.GroupService;
import com.example.demo.utils.ConvertTool;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/groups")
@CrossOrigin
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/auto-grouping")
    public ResponseEntity<List<GroupDto>> group() {
        List<Group> groups = groupService.group();
        List<GroupDto> groupDtoList = new ArrayList<>();
        List<TraineeDto> traineeDtos = new ArrayList<>();
        List<TrainerDto> trainerDtos = new ArrayList<>();
        groups.forEach(group -> {
            traineeDtos.clear();
            trainerDtos.clear();
            group.getTrainees().forEach(trainee -> traineeDtos.add(ConvertTool.convert(trainee, TraineeDto.class)));
            group.getTrainers().forEach(trainer -> trainerDtos.add(ConvertTool.convert(trainer, TrainerDto.class)));
            groupDtoList.add(GroupDto.builder()
                    .id(group.getId())
                    .name(group.getName())
                    .traineeDtoList(traineeDtos)
                    .trainerDtoList(trainerDtos)
                    .build());
        });
        return ResponseEntity.status(HttpStatus.OK).body(groupDtoList);
    }


    @GetMapping
    public ResponseEntity<List<GroupDto>> getGroups() {
        List<Group> groups = groupService.getGroups();
        List<GroupDto> groupDtoList = new ArrayList<>();
        List<TraineeDto> traineeDtos = new ArrayList<>();
        List<TrainerDto> trainerDtos = new ArrayList<>();
        groups.forEach(group -> {
            traineeDtos.clear();
            trainerDtos.clear();
            group.getTrainees().forEach(trainee -> traineeDtos.add(ConvertTool.convert(trainee, TraineeDto.class)));
            group.getTrainers().forEach(trainer -> trainerDtos.add(ConvertTool.convert(trainer, TrainerDto.class)));
            groupDtoList.add(GroupDto.builder()
                    .id(group.getId())
                    .name(group.getName())
                    .traineeDtoList(traineeDtos)
                    .trainerDtoList(trainerDtos)
                    .build());
        });
        return ResponseEntity.status(HttpStatus.OK).body(groupDtoList);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGroupName(@PathVariable Long id, @RequestBody GroupDto groupDto) {
        groupService.updateGroupName(id, groupDto);
    }
}
