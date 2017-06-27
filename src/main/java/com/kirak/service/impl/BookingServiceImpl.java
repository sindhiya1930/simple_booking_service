package com.kirak.service.impl;

import com.kirak.model.Booking;
import com.kirak.repository.BookingRepository;
import com.kirak.service.BookingService;
import com.kirak.util.ValidationUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.kirak.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Kir on 01.06.2017.
 */
@Transactional
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;

    @Autowired
    private BookingServiceImpl(BookingRepository repository){
        this.repository = repository;
    }


    @Override
    public Booking save(Booking booking, int userId, int hotelId) {
        Assert.notNull(booking, "Booking must not be null!");
        return repository.save(booking, userId, hotelId);
    }

    @Override
    public Booking update(Booking booking, int userId, int hotelId) {
        Assert.notNull(booking, "Booking must not be null!");
        return checkNotFoundWithId(repository.save(booking, userId, hotelId), booking.getId());
    }

    @Override
    public Booking get(Long id, int userId, int hotelId) {
        return checkNotFoundWithId(repository.get(id, userId, hotelId), id);
    }

    @Override
    public List<Booking> getAllByUserId(int userId) {
        return repository.getAllByUserId(userId);
    }

    @Override
    public List<Booking> getAllByHotelBetweenDates(int hotelId, LocalDateTime startDate, LocalDateTime endDate) {
        Assert.notNull(startDate, "Incoming LocalDate must not be null!");
        Assert.notNull(endDate, "Incoming LocalDate must not be null!");
        return repository.getAllByHotelBetweenDates(hotelId, startDate, endDate);
    }

    @Override
    public List<Booking> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void evictCache() {

    }


}
