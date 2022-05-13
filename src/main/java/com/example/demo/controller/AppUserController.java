package com.example.demo.controller;

import com.example.demo.Lecture;
import com.example.demo.dto.UserLecture;
import com.example.demo.entity.AppUser;
import com.example.demo.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/users")
    public List<AppUser> fetchUserList(){
        return appUserService.fetchUserList();
    }

    @GetMapping("/users/lectures/{login}")
    public List<Lecture> fetchUserLectures(@PathVariable("login") String login){
        return appUserService.fetchUserLectures(login);
    }

    @PostMapping("/users/save")
    public ResponseEntity<?> saveAppUser(@RequestBody AppUser appUser){
        return appUserService.saveAppUser(appUser);
    }

    @PostMapping("/users/signup")
    public AppUser signUpAppUser(@RequestBody UserLecture userLecture) throws IOException {
        return appUserService.signUpAppUser(userLecture);
    }

    @PostMapping("/users/cancel")
    public AppUser cancelLecture(@RequestBody UserLecture userLecture) {
        return appUserService.cancelLecture(userLecture);
    }

    @PutMapping("/users/changeemail/{login}")
    public AppUser changeEmail(@PathVariable("login") String login, @RequestParam String email) {
        return appUserService.changeEmail(login,email);
    }
}
