package com.nas.booking.service;


import com.nas.booking.models.Book;
import com.nas.booking.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;


    @Override
    public Book create(Book book) {
        return null;
    }
}
