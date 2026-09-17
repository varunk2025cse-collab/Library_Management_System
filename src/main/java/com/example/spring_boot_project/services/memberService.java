package com.example.spring_boot_project.services;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.spring_boot_project.model.member;
import com.example.spring_boot_project.repository.memberRepository;

@Service
public class memberService {
    private final memberRepository repository;
    public memberService(memberRepository repository) 
    {
        this.repository = repository;
    }   
    public List<member> getMemberDetails()
    {
        return repository.findAll();
    }

    public member addMember(member newMember) {
        return repository.save(newMember);
    }

    public member updateMember(Long id, member updatedMember) {
        member existingMember = repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found with id: " + id));
        existingMember.setName(updatedMember.getName());
        existingMember.setEmail(updatedMember.getEmail());
        existingMember.setPhoneNumber(updatedMember.getPhoneNumber());
        return repository.save(existingMember);
    }

    public void deleteMember(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
