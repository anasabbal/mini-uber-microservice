package com.nas.booking.repository.booking;

import com.nas.booking.models.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookingRepository extends CrudRepository<Booking, String> {
}
