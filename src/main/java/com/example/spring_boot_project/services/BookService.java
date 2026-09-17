package com.example.spring_boot_project.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.spring_boot_project.model.author;
import com.example.spring_boot_project.model.book;
import com.example.spring_boot_project.repository.BookRepository;
import com.example.spring_boot_project.repository.authorRepository;

@Service
public class BookService {
    private final BookRepository repository;
    private final authorRepository authorRepository;

    public BookService(BookRepository repository, authorRepository authorRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
    }

    public List<book> getBookDetails() {
        return repository.findAll();
    }

    public book addBook(book b1) {
        b1.setAuthor(resolveAuthor(b1.getAuthor()));
        return repository.save(b1);
    }

    private author resolveAuthor(author requestedAuthor) {
        if (requestedAuthor == null || requestedAuthor.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A valid author id is required for the book.");
        }
        return authorRepository.findById(requestedAuthor.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Author not found with id: " + requestedAuthor.getId()));
    }

    public book getBookById(Long id) {
        return repository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id: " + id));
    }

    public List<book> getAllBooks() {
        return repository.findAll();
    }

    public book updateBook(Long id, book updatedBook) {
        book existingBook = repository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id: " + id));

        existingBook.setTitle(updatedBook.getTitle());
        if (updatedBook.getAuthor() != null) {
            existingBook.setAuthor(resolveAuthor(updatedBook.getAuthor()));
        }
        existingBook.setPrice(updatedBook.getPrice());
        return repository.save(existingBook);
    }

    public void deleteBook(Long id) {
        repository.deleteById(id);
    }
}
