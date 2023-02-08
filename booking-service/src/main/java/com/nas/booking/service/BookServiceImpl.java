package com.nas.booking.service;


import com.nas.booking.models.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;


    @Override
    public Booking create(Booking booking) {
        return null;
    }
}
