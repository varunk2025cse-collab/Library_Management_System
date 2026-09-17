package com.example.spring_boot_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_boot_project.model.book;

public interface BookRepository extends JpaRepository<book, Long> {
}
