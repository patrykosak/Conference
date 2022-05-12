package com.example.demo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Lecture {
    private Long lectureId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String thematicPath;
}
