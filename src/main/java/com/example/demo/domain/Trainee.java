package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trainees")
public class Trainee {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String name;
    private String office;
    private String email;
    private String github;
    private boolean isGrouped;

//    @ManyToOne
//    @JoinColumn(name = "groups_id")
//    @JsonIgnore
//    private Group group;
}