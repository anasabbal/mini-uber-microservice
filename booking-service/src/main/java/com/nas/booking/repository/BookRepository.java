package com.nas.booking.repository;

import com.nas.booking.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BookRepository extends CrudRepository<String, Book> {
}
