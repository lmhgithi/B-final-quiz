package com.example.demo.service;


import com.example.demo.domain.Group;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.TraineeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;


    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }


    public List<Group> getGroups() {
        return groupRepository.findAll();
    }
}
