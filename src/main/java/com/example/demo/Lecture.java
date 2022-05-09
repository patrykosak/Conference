package com.example.demo;

import com.example.demo.entity.AppUser;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
public class Lecture {
    private Long lectureId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Set<AppUser> users;
}
