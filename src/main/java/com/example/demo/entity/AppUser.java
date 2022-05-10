package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    private String login;
    private String email;
    @ElementCollection
    @CollectionTable(name="listOfLectures")
    private List<Long> lecturesId;
}
