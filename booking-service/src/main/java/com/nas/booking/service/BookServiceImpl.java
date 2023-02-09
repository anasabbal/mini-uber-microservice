package com.nas.booking.service;


import com.nas.booking.models.Booking;
import com.nas.booking.repository.booking.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookingRepository bookingRepository;
    private ApplicationEventPublisher applicationEventPublisher;


    @Override
    public Booking create(Booking booking) {
        Booking booking1 = bookingRepository.save(booking);
        applicationEventPublisher.publishEvent(null);
        return null;
    }
}
