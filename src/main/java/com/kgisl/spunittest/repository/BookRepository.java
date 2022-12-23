package com.kgisl.spunittest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kgisl.spunittest.entity.Book;

public interface BookRepository extends JpaRepository<Book,Long>{
    
}
