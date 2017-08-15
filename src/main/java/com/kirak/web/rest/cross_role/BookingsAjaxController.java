package com.kirak.web.rest.cross_role;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.service.ApartmentService;
import com.kirak.service.BookingService;
import com.kirak.service.SuperBookingService;
import com.kirak.to.booking.BookingTo;
import com.kirak.web.View;
import com.kirak.web.abstr.BookingAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Kir on 15.08.2017.
 */

@RestController
@RequestMapping("/bookings")
public class BookingsAjaxController extends BookingAbstractController {

    @Autowired
    public BookingsAjaxController(BookingService bookingService, SuperBookingService superBookingService, ApartmentService apartmentService) {
        super(bookingService, superBookingService, apartmentService);
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createBooking(@Valid BookingTo bookingTo,
                              @RequestParam("sbId") int superBookingId) {
        super.createBooking(bookingTo, superBookingId);
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateBooking(@Valid BookingTo bookingTo) {
        super.updateBooking(bookingTo);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public BookingTo getBooking(@PathVariable("id") Long id) {
        return super.getBooking(id);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/all_bookings", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookingTo> getAllBookingsBySBId(@RequestParam("sbId") int superBookingId) {
        return super.getAllBookingsBySBId(superBookingId);
    }
}
