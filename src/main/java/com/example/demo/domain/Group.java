package com.example.demo.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String name;
//    知道该写成下面的样子，但是环境有问题，报错Error executing DDL，时间不够先用别的方法写了
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    List<Trainee> trainees;
//
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    List<Trainer> trainers;
}
