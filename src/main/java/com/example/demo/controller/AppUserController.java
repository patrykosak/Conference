package com.example.demo.controller;

import com.example.demo.dto.UserLecture;
import com.example.demo.entity.AppUser;
import com.example.demo.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/users")
    public ResponseEntity<?> fetchUserList(){
        return appUserService.fetchUserList();
    }

    @GetMapping("/users/lectures/{login}")
    public ResponseEntity<?> fetchUserLectures(@PathVariable("login") String login){
        return appUserService.fetchUserLectures(login);
    }

    @PostMapping("/users/save")
    public ResponseEntity<?> saveAppUser(@RequestBody AppUser appUser){
        return appUserService.saveAppUser(appUser);
    }

    @PostMapping("/users/signup")
    public ResponseEntity<?> signUpAppUser(@RequestBody UserLecture userLecture) {
        return appUserService.signUpAppUser(userLecture);
    }

    @PostMapping("/users/cancel")
    public ResponseEntity<?> cancelLecture(@RequestBody UserLecture userLecture) {
        return appUserService.cancelLecture(userLecture);
    }

    @PutMapping("/users/changeemail/{login}")
    public ResponseEntity<?> changeEmail(@PathVariable("login") String login, @RequestParam String email) {
        return appUserService.changeEmail(login,email);
    }
}
