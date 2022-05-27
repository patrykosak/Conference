package com.example.demo.service;

import com.example.demo.Lecture;
import com.example.demo.dto.UserLecture;
import com.example.demo.entity.AppUser;
import com.example.demo.repository.AppUserRepository;
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

    private int MAX_NUMBER_OF_USERS = 5;
    private AppUserRepository appUserRepository;
    private LectureService lectureService;

    public AppUserService(AppUserRepository appUserRepository, LectureService lectureService) {
        this.appUserRepository = appUserRepository;
        this.lectureService = lectureService;
    }

    public ResponseEntity<?> fetchUserList() {
        return ResponseEntity.ok(appUserRepository.findAll());
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

    public ResponseEntity<?> signUpAppUser(UserLecture userLecture) {
        AppUser appUserDB = appUserRepository.getById(userLecture.getLogin());
        if(canUserSignUpToLecture(userLecture,appUserDB)){
            appUserDB.getLecturesId().add(userLecture.getLectureId());

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            try( PrintWriter writer = new PrintWriter(new FileWriter("Powiadomienia",true))){
                writer.println("data wysłania: " + formatter.format(date) + " " + "do: " + appUserDB.getEmail() + " " + "Zapisałeś się na kurs");
            } catch (IOException ioException) {
                return new ResponseEntity<>(
                        "Błąd z zapisem do pliku",
                        HttpStatus.FORBIDDEN);
            }

        }
        return ResponseEntity.ok(appUserRepository.save(appUserDB));
    }

    public boolean canUserSignUpToLecture(UserLecture userLecture,AppUser appUserDB){
        List<AppUser> usersSignedForLecture = appUserRepository.findAllByLecturesIdContaining(userLecture.getLectureId());
        List<Long> lecturesAtThisHour = lectureService.fetchLectureListAtThisHour(lectureService.getLectureStartDate(userLecture.getLectureId()));
        return userLecture.getEmail().equals(appUserDB.getEmail()) && usersSignedForLecture.size() < MAX_NUMBER_OF_USERS && lecturesAtThisHour.stream().noneMatch(l -> appUserDB.getLecturesId().contains(l));
    }

    public ResponseEntity<?> fetchUserLectures(String login) {
        AppUser appUserDB = appUserRepository.getById(login);
        List<Lecture> lectures = new ArrayList<>();
        for(Long lectureId:appUserDB.getLecturesId()) {
            lectures.add(lectureService.fetchLecture(lectureId));
        }
        return ResponseEntity.ok(lectures);
    }

    public ResponseEntity<?> cancelLecture(UserLecture userLecture) {
        AppUser appUserDB = appUserRepository.getById(userLecture.getLogin());
        appUserDB.getLecturesId().remove(userLecture.getLectureId());
        return ResponseEntity.ok(appUserRepository.save(appUserDB));
    }

    public ResponseEntity<?> changeEmail(String login, String email) {
        AppUser appUserDB = appUserRepository.getById(login);
        appUserDB.setEmail(email);
        return ResponseEntity.ok(appUserRepository.save(appUserDB));
    }

}
