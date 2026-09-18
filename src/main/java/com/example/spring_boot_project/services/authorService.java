package com.example.spring_boot_project.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.spring_boot_project.model.author;
import com.example.spring_boot_project.repository.authorRepository;

@Service
public class authorService {
	private final authorRepository repository;

	public authorService(authorRepository repository) {
		this.repository = repository;
	}

	public List<author> getAuthorDetails(){
		return repository.findAll();
	}

	public author addAuthor(author newAuthor) {
		return repository.save(newAuthor);
	}

	public author updateAuthor(Long id, author updatedAuthor) {
		author existingAuthor = repository.findById(id).orElseThrow(() ->
				new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found with id: " + id));
		if (updatedAuthor.getName() != null) {
			existingAuthor.setName(updatedAuthor.getName());
		}
		if (updatedAuthor.getCountry() != null) {
			existingAuthor.setCountry(updatedAuthor.getCountry());
		}
		return repository.save(existingAuthor);
	}

	public void deleteAuthor(Long id) {
		if (!repository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found with id: " + id);
		}
		repository.deleteById(id);
	}
}
