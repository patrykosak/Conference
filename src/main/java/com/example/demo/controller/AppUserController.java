package com.example.demo.controller;

import com.example.demo.entity.AppUser;
import com.example.demo.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/users")
    public List<AppUser> fetchUserList(){
        return appUserService.fetchUserList();
    }

    @PostMapping("/users/save")
    public AppUser saveAuthor(@RequestBody AppUser appUser){
        return appUserService.saveAppUser(appUser);
    }
}
