package com.example.spring_boot_project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_project.model.book;
import com.example.spring_boot_project.services.BookService;


@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<book> getBookDetails() {
        return service.getBookDetails();
    }
    
    @PostMapping
    public book addBook(@RequestBody book newBook) {
        return service.addBook(newBook);
    } 
    @PutMapping("/{id}")
    public book updateBook(@PathVariable Long id, @RequestBody book updatedBook) {
        return service.updateBook(id, updatedBook);
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
        return "Book with ID " + id + " has been deleted.";
    }
    
}


