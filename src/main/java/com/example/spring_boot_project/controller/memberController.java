package com.example.spring_boot_project.controller;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_project.model.member;
import com.example.spring_boot_project.services.memberService;


@RestController
@RequestMapping("/members")

public class memberController {
private final memberService service;

    public memberController(memberService service) {
        this.service = service;
    }

    @GetMapping
    public List<member> getMemberDetails() {
        return service.getMemberDetails();
    }

    @PostMapping
    public member addMember(@RequestBody member newMember) {
        return service.addMember(newMember);
    }
    @PutMapping("/{id}")
    public member updateMember(@PathVariable Long id, @RequestBody member updatedMember) {
        return service.updateMember(id, updatedMember);
    }
    @DeleteMapping("/{id}")
    public String deleteMember(@PathVariable Long id) {
        service.deleteMember(id);
        return "Member with ID " + id + " has been deleted.";
    }
}
