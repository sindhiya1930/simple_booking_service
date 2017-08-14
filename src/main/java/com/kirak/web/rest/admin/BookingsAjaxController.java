package com.kirak.web.rest.admin;

import com.fasterxml.jackson.annotation.JsonView;
import com.kirak.model.Booking;
import com.kirak.service.ApartmentService;
import com.kirak.service.BookingService;
import com.kirak.service.SuperBookingService;
import com.kirak.to.booking.AdminSuperBookingTo;
import com.kirak.to.booking.BookingTo;
import com.kirak.to.booking.ManagerSuperBookingTo;
import com.kirak.web.View;
import com.kirak.web.abstr.BookingAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Kir on 12.08.2017.
 */

@RestController
@RequestMapping(value = "/admin/bookings")
public class BookingsAjaxController extends BookingAbstractController {

    @Autowired
    protected BookingsAjaxController(BookingService bookingService, SuperBookingService superBookingService,
                                     ApartmentService apartmentService) {
        super(bookingService, superBookingService, apartmentService);
    }

    @PostMapping
    public void updateSuperBookingByAdmin(@Valid ManagerSuperBookingTo managerSuperBookingTo) {
            super.updateSuperBooking(managerSuperBookingTo);
    }

    @GetMapping(value = "/{id}")
    @JsonView(View.JsonUI.class)
    public ManagerSuperBookingTo getManagerSuperBookingForAdmin(@PathVariable("id") int id) {
        return super.getManagerSuperBooking(id);
    }


    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUI.class)
    public List<AdminSuperBookingTo> getAllSuperBookingsForAdmin() {
        return super.getAllSuperBookingsForAdmin();
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/between_dates")
    public List<AdminSuperBookingTo> getSuperBookingsBetweenDatesForAdmin(@RequestParam("inDate")LocalDate startDate,
                                                                          @RequestParam("outDate")LocalDate endDate){
        return super.getSuperBookingsBetweenDatesForAdmin(startDate, endDate);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_in_date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AdminSuperBookingTo> getSuperBookingsByInDateForAdmin(@RequestParam("inDate")LocalDate inDate) {
        return super.getSuperBookingsByInDateForAdmin(inDate);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_out_date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AdminSuperBookingTo> getSuperBookingsByOutDateForAdmin(@RequestParam("outDate")LocalDate outDate) {
        return super.getSuperBookingsByOutDateForAdmin(outDate);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_user_id", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AdminSuperBookingTo> getSuperBookingsByUserIdForAdmin(@RequestParam("userId") int userId) {
        return super.getSuperBookingsByUserIdForAdmin(userId);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/by_hotel_id", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AdminSuperBookingTo> getSuperBookingsByHotelIdForAdmin(@RequestParam("hotelId")int hotelId) {
        return super.getSuperBookingsByHotelIdForAdmin(hotelId);
    }




    // ----------------------- Booking methods ----------------------- //

    @Override
    @PostMapping(value = "/update_booking", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateBooking(@Valid BookingTo bookingTo) {
        super.updateBooking(bookingTo);
    }

    @Override
    @JsonView(View.JsonUI.class)
    @PostMapping(value = "/all_bookings", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookingTo> getAllBookingsBySBId(@RequestParam("sbId") int superBookingId) {
        return super.getAllBookingsBySBId(superBookingId);
    }

}