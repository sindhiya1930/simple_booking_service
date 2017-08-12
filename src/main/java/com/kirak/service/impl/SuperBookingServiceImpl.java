package com.kirak.service.impl;

import com.kirak.model.SuperBooking;
import com.kirak.repository.SuperBookingRepository;
import com.kirak.service.SuperBookingService;
import com.kirak.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.kirak.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Kir on 07.08.2017.
 */
@Transactional
@Service
public class SuperBookingServiceImpl implements SuperBookingService {

    private final SuperBookingRepository repository;

    @Autowired
    private SuperBookingServiceImpl(SuperBookingRepository repository){
        this.repository = repository;
    }

    @Override
    public SuperBooking save(SuperBooking superBooking) {
        Assert.notNull(superBooking, "SuperBooking must not be null!");
        return repository.save(superBooking);
    }

    @Override
    public SuperBooking update(SuperBooking superBooking) {
        return checkNotFoundWithId(repository.save(superBooking), superBooking.getId());
    }

    @Override
    public SuperBooking save(SuperBooking booking, int userId) {
        Assert.notNull(booking, "SuperBooking must not be null!");
        return repository.save(booking, userId);
    }

    @Override
    public SuperBooking update(SuperBooking booking, int userId) {
        Assert.notNull(booking, "SuperBooking must not be null!");
        return checkNotFoundWithId(repository.save(booking, userId), booking.getId());
    }

    @Override
    public SuperBooking get(Integer id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public SuperBooking get(Integer id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<SuperBooking> getAllByUserId(int userId) {
        return repository.getAllByUserId(userId);
    }

    public List<SuperBooking> getAllByHotelId(int hotelId){
        return repository.getAllByHotelId(hotelId);
    }

    @Override
    public List<SuperBooking> getAllBetweenCreatedDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Assert.notNull(startDateTime, "startDateTime must not be null");
        Assert.notNull(endDateTime, "endDateTime  must not be null");
        return repository.getAllBetweenCreatedDateTimes(startDateTime, endDateTime);
    }

    @Override
    public List<SuperBooking> getAllByInDate(LocalDate inDate) {
        return repository.getAllByInDate(inDate);
    }

    @Override
    public List<SuperBooking> getAllByOutDate(LocalDate outDate) {
        return repository.getAllByOutDate(outDate);
    }

    @Override
    public List<SuperBooking> getAll() {
        return repository.getAll();
    }


}
