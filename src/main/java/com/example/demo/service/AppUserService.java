package com.example.demo.service;

import com.example.demo.Lecture;
import com.example.demo.dto.UserLecture;
import com.example.demo.entity.AppUser;
import com.example.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public AppUser signUpAppUser(UserLecture userLecture) throws IOException {
        AppUser appUserDB = appUserRepository.getById(userLecture.getLogin());
        List<AppUser> usersSignedForLecture = appUserRepository.findAllByLecturesIdContaining(userLecture.getLectureId());

        if(userLecture.getEmail().equals(appUserDB.getEmail())&&usersSignedForLecture.size()<5){
            appUserDB.getLecturesId().add(userLecture.getLectureId());

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            PrintWriter writer = new PrintWriter(new FileWriter("Powiadomienia",true));
            writer.println("data wysłania: " + formatter.format(date) + " " + "do: " + appUserDB.getEmail() + " " + "Zapisałeś się na kurs");
            writer.close();
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

    public AppUser cancelLecture(UserLecture userLecture) {
        AppUser appUserDB = appUserRepository.getById(userLecture.getLogin());
        appUserDB.getLecturesId().remove(userLecture.getLectureId());
        return saveAppUser(appUserDB);
    }
}
