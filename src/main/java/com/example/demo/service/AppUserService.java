package com.example.demo.service;

import com.example.demo.Lecture;
import com.example.demo.dto.UserLecture;
import com.example.demo.entity.AppUser;
import com.example.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private LectureService lectureService;

    public List<AppUser> fetchUserList() {
        return appUserRepository.findAll();
    }

    public AppUser saveAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public AppUser signUpAppUser(UserLecture userLecture) {
        AppUser appUserDB = appUserRepository.getById(userLecture.getLogin());
        if(userLecture.getEmail().equals(appUserDB.getEmail())){
            appUserDB.getLecturesId().add(userLecture.getLectureId());
        }
        return saveAppUser(appUserDB);
    }

    public List<Lecture> fetchUserLectures(String login) {
        AppUser appUserDB = appUserRepository.getById(login);
        List<Lecture> lectures = new ArrayList<>();
        for(Long lectureId:appUserDB.getLecturesId()) {
            lectures.add(lectureService.fetchLecture(lectureId));
        }
        return lectures;
    }
}
