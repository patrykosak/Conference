package com.example.demo.service;

import com.example.demo.Lecture;
import com.example.demo.dto.UserLecture;
import com.example.demo.entity.AppUser;
import com.example.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<?> saveAppUser(AppUser appUser) {
        if(appUserRepository.existsById(appUser.getLogin())) {
            AppUser appUserDB = appUserRepository.getById(appUser.getLogin());
            if (appUser.getLogin().equals(appUserDB.getLogin()) && !appUser.getEmail().equals(appUserDB.getEmail())) {
                return new ResponseEntity<>(
                        "Podany login jest już zajęty",
                        HttpStatus.NOT_ACCEPTABLE);
            }
            else if(appUser.getLogin().equals(appUserDB.getLogin()) && appUser.getEmail().equals(appUserDB.getEmail())){
                return new ResponseEntity<>(
                        "Takie konto już istnieje",
                        HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return ResponseEntity.ok(appUserRepository.save(appUser));
    }

    public AppUser signUpAppUser(UserLecture userLecture) throws IOException {
        AppUser appUserDB = appUserRepository.getById(userLecture.getLogin());
        List<AppUser> usersSignedForLecture = appUserRepository.findAllByLecturesIdContaining(userLecture.getLectureId());
        List<Long> lecturesAtThisHour = lectureService.fetchLectureListAtThisHour(lectureService.getLectureStartDate(userLecture.getLectureId()));
        if(userLecture.getEmail().equals(appUserDB.getEmail())&&usersSignedForLecture.size()<5&&!lecturesAtThisHour.stream().anyMatch(l->appUserDB.getLecturesId().contains(l))){
            appUserDB.getLecturesId().add(userLecture.getLectureId());

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            PrintWriter writer = new PrintWriter(new FileWriter("Powiadomienia",true));
            writer.println("data wysłania: " + formatter.format(date) + " " + "do: " + appUserDB.getEmail() + " " + "Zapisałeś się na kurs");
            writer.close();
        }
        return appUserRepository.save(appUserDB);
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
        return appUserRepository.save(appUserDB);
    }

    public AppUser changeEmail(String login, String email) {
        AppUser appUserDB = appUserRepository.getById(login);
        appUserDB.setEmail(email);
        return appUserRepository.save(appUserDB);
    }

}
