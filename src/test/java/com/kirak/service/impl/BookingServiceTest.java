package com.kirak.service.impl;

import com.kirak.model.Booking;
import com.kirak.service.AbstractServiceTest;
import com.kirak.service.BookingService;
import com.kirak.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;

import static com.kirak.Profile.DATAJPA;
import static com.kirak.mock.BookingTestData.*;
import static com.kirak.mock.HotelTestData.*;
import static com.kirak.mock.UserTestData.*;
import static java.time.LocalDate.of;

/**
 * Created by Kir on 19.06.2017.
 */
@ActiveProfiles(DATAJPA)
public class BookingServiceTest extends AbstractServiceTest {

    @Autowired
    private BookingService service;

    @Before
    public void setUp() throws Exception {
        service.evictCache();
    }

    @Test
    public void save() throws Exception {
        Booking created = getCreatedBooking();
        service.save(created, USER2_ID, HOTEL2_ID);
        BOOKING_MATCHER.assertCollectionEquals(Arrays.asList(created, BOOKING4, BOOKING3, BOOKING5, BOOKING2, BOOKING1),
                service.getAll());
    }

    @Test
    public void update() throws Exception {
        Booking updated = getUpdatedBooking();
        service.update(updated, USER1_ID, HOTEL1_ID);
        BOOKING_MATCHER.assertEquals(updated, service.get(BOOKING1_ID, USER1_ID, HOTEL1_ID));
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + BOOKING1_ID);
        service.update(BOOKING1, USER3_ID, HOTEL4_ID);
    }

    @Test
    public void get() throws Exception {
        BOOKING_MATCHER.assertEquals(BOOKING3, service.get(BOOKING3_ID, USER3_ID, HOTEL2_ID));
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + BOOKING1_ID);
        service.get(BOOKING1_ID, USER3_ID, HOTEL2_ID);
    }

    @Test
    public void getAllByUserId() throws Exception {
        BOOKING_MATCHER.assertCollectionEquals(Arrays.asList(BOOKING4, BOOKING1), service.getAllByUserId(USER1_ID));
    }

    @Test
    public void getAllByHotelBetweenDates() throws Exception {
        BOOKING_MATCHER.assertCollectionEquals(Collections.singletonList(BOOKING1),
                service.getAllByHotelBetweenDates(HOTEL1_ID, LocalDateTime.of(2016, Month.MAY, 11, 0, 0),
                        LocalDateTime.of(2016, Month.MAY, 14, 0 ,0)));
    }

    @Test
    public void getAll(){
        BOOKING_MATCHER.assertCollectionEquals(BOOKINGS, service.getAll());
    }

}