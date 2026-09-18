package com.example.spring_boot_project.controller;

import com.example.spring_boot_project.model.author;
import com.example.spring_boot_project.model.book;
import com.example.spring_boot_project.services.BookService;
import com.example.spring_boot_project.services.authorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/books")
public class BookWebController {

    private final BookService bookService;
    private final authorService authorService;

    public BookWebController(BookService bookService, authorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String showBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    @GetMapping("/new")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new book());
        model.addAttribute("authors", authorService.getAuthorDetails());
        return "add-book";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") book newBook,
                           @RequestParam Long authorId) {
        author selectedAuthor = authorService.getAuthorById(authorId);
        newBook.setAuthor(selectedAuthor);
        bookService.addBook(newBook);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("authors", authorService.getAuthorDetails());
        return "edit-book";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id,
                             @ModelAttribute("book") book updatedBook,
                             @RequestParam Long authorId) {
        updatedBook.setAuthor(authorService.getAuthorById(authorId));
        bookService.updateBook(id, updatedBook);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}