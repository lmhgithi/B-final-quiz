package com.example.demo.controller;


import com.example.demo.domain.Group;
import com.example.demo.dto.GroupDto;
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
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @GetMapping
    public ResponseEntity<List<GroupDto>> getGroups() {
        List<Group> groups = groupService.getGroups();
        List<GroupDto> groupDtoList = new ArrayList<>();
        groups.forEach(domain -> groupDtoList.add(ConvertTool.convert(domain, GroupDto.class)));
        return ResponseEntity.status(HttpStatus.OK).body(groupDtoList);
    }


}