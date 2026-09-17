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

import com.example.spring_boot_project.model.author;
import com.example.spring_boot_project.services.authorService;


@RestController
@RequestMapping("/authors")
public class authorController {
    private final authorService service;

    public authorController(authorService service) {
        this.service = service;
    }

    @GetMapping
    public List<author> getAuthorDetails() {
        return service.getAuthorDetails();
    }

    @PostMapping
    public author addAuthor(@RequestBody author newAuthor) {
        return service.addAuthor(newAuthor);
    }
    @PutMapping("/{id}")
    public author updateAuthor(@PathVariable Long id, @RequestBody author updatedAuthor) {
        return service.updateAuthor(id, updatedAuthor);
    }
    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        service.deleteAuthor(id);
        return "Author with ID " + id + " has been deleted.";
    }                   
}
