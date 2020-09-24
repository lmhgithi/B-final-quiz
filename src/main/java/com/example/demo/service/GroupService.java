package com.example.demo.service;


import com.example.demo.domain.Group;
import com.example.demo.domain.Trainee;
import com.example.demo.domain.Trainer;
import com.example.demo.dto.GroupDto;
import com.example.demo.dto.TraineeDto;
import com.example.demo.dto.TrainerDto;
import com.example.demo.exception.SimpleException;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.utils.ConvertTool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;
    //        同样因为那个数据库环境问题，分好的组就在内存存了
    private static List<GroupDto> groupDtoList;

    public GroupService(GroupRepository groupRepository, TraineeRepository traineeRepository, TrainerRepository trainerRepository) {
        this.groupRepository = groupRepository;
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }


    public List<GroupDto> getGroups() {
        return groupDtoList;
//        return groupRepository.findAll();
    }

    public List<GroupDto>group(){
        initGroup();
        List<Trainee> trainees = traineeRepository.findAll();
        Collections.shuffle(trainees);
        List<Trainer> trainers = trainerRepository.findAll();
        Collections.shuffle(trainers);
        int groupNum = trainers.size()/2;
        int groupSize = trainees.size()/groupNum;
        int restToAdd = 0;
        if (groupSize * groupNum < trainees.size()) {
            restToAdd = trainees.size() - groupSize*groupNum;
        }

        int totalTrainees = 0;
        int totalTrainers = 0;
        for (int i = 0; i < groupNum; i += 1) {
            List<TrainerDto> tmpTrainerDtoList = new ArrayList<>();
            for (int j = 0; j < 2; j+=1) {
                if (totalTrainers > trainers.size()) break;
                tmpTrainerDtoList.add(ConvertTool.convert(trainers.get(totalTrainers), TrainerDto.class));
                Trainer trainerToGroup = trainers.get(totalTrainers);
                trainerToGroup.setGrouped(true);
                trainerRepository.save(trainerToGroup);
                totalTrainers += 1;
            }


            List<TraineeDto> tmpTraineeDtoList = new ArrayList<>();
            int tmpAdd = restToAdd > 0 ? groupSize + 1 : groupSize;
            for (int j = 0; j < tmpAdd; j += 1) {
                if (totalTrainees <= trainees.size()) {
                    tmpTraineeDtoList.add(ConvertTool.convert(trainees.get(totalTrainees), TraineeDto.class));
                    Trainee traineeToGroup = trainees.get(totalTrainees);
                    traineeToGroup.setGrouped(true);
                    traineeRepository.save(traineeToGroup);
                    totalTrainees += 1;
                }
            }
            restToAdd -= 1;
            Group group = Group.builder()
                    .name(i+1 + "组")
                    .build();
            groupRepository.save(group);
            GroupDto groupDto = GroupDto.builder()
                    .id(group.getId())
                    .name(group.getName())
                    .traineeDtoList(tmpTraineeDtoList)
                    .trainerDtoList(tmpTrainerDtoList)
                    .build();
            groupDtoList.add(groupDto);
        }
        return groupDtoList;

    }

    private void initGroup(){
        groupDtoList = new ArrayList<>();
        groupRepository.deleteAll();
    }

    public void updateGroupName(Long id, GroupDto groupDto) {
        Optional<Group> group = groupRepository.findById(id);
        if (group.isPresent()) {
            groupDtoList.get(Math.toIntExact(id-1)).setName(groupDto.getName());
            group.get().setName(groupDto.getName());
            groupRepository.save(group.get());
        } else {
            throw new SimpleException("group id not exists");
        }
    }
}
