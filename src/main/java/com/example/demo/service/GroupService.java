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


    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    public List<Group>group(){
        // 有bug，分组后，学员会被删除，又要重新加学员
        List<Trainee> trainees = traineeRepository.findAll();
        Collections.shuffle(trainees);
        List<Trainer> trainers = trainerRepository.findAll();
        Collections.shuffle(trainers);
        initGroup();

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
            List<Trainer> tmpTrainerList = new ArrayList<>();
            for (int j = 0; j < 2; j+=1) {
                if (totalTrainers > trainers.size()) break;
                tmpTrainerList.add(trainers.get(totalTrainers));
                Trainer trainerToGroup = trainers.get(totalTrainers);
                trainerToGroup.setGrouped(true);
                totalTrainers += 1;
            }


            List<Trainee> tmpTraineeList = new ArrayList<>();
            int tmpAdd = restToAdd > 0 ? groupSize + 1 : groupSize;
            for (int j = 0; j < tmpAdd; j += 1) {
                if (totalTrainees <= trainees.size()) {
                    tmpTraineeList.add(trainees.get(totalTrainees));
                    Trainee traineeToGroup = trainees.get(totalTrainees);
                    traineeToGroup.setGrouped(true);
                    totalTrainees += 1;
                }
            }
            restToAdd -= 1;
            Group group = Group.builder()
                    .trainers(tmpTrainerList)
                    .trainees(tmpTraineeList)
                    .name(i+1 + "组")
                    .build();
            groupRepository.save(group);
        }
        return groupRepository.findAll();

    }

    private void initGroup(){
        groupRepository.deleteAll();
        List<Trainee> trainees = traineeRepository.findAll();
        trainees.forEach(trainee -> {
            trainee.setGrouped(false);
        });
        traineeRepository.saveAll(trainees);
        List<Trainer> trainers = trainerRepository.findAll();
        trainers.forEach(trainer -> {
            trainer.setGrouped(false);
        });
        trainerRepository.saveAll(trainers);
    }

    public void updateGroupName(Long id, GroupDto groupDto) {
        Optional<Group> group = groupRepository.findById(id);
        if (group.isPresent()) {
            group.get().setName(groupDto.getName());
            groupRepository.save(group.get());
        } else {
            throw new SimpleException("group id not exists");
        }
    }
}
