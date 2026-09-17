package com.example.spring_boot_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_boot_project.model.author;

public interface authorRepository extends JpaRepository<author, Long> {
}
